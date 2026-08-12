# Día 2 — Colaborar de verdad: merge, rebase y conflictos (120 min)

Ayer trabajaste sola/o en tu repo. Hoy vas a trabajar **de a dos sobre los mismos
archivos**, que es donde Git se pone interesante… y donde aparecen los conflictos.

**Spoiler:** hoy vas a romper cosas a propósito. Está buenísimo. Los conflictos no
son un error, son Git avisándote *"acá hay una decisión que tiene que tomar un humano"*.

---

## Parte 0 — Merge vs Rebase (15 min, teoría)

Los dos resuelven el mismo problema: *"mi rama quedó vieja, ¿cómo le meto lo
nuevo que apareció en `main`?"*

### `git merge main` — une las dos historias con un commit nuevo

```
main:     A───B───C───────M     ← M es el "commit de merge"
                   \     /
mi rama:            D───E
```

- ✅ No toca los commits que ya existían. Es seguro.
- ✅ Queda registrado cuándo y cómo se integró cada cosa.
- ❌ La historia se llena de commits de merge y se vuelve difícil de leer.

### `git rebase main` — reaplica tus commits arriba de `main`

Como si hubieras empezado a trabajar recién, con `main` ya actualizado.

```
main:     A───B───C
                   \
mi rama:            D'───E'    ← ojo: D' y E' son commits NUEVOS
```

- ✅ Historia lineal y limpia, se lee como un relato.
- ❌ **Reescribe la historia.** D y E dejan de existir; nacen D' y E'.

> 🔑 **Regla de oro:** nunca hagas `rebase` de una rama que otra persona ya usó.
> Rebase solo sobre commits tuyos que todavía no vio nadie.
> Si tenés dudas, `merge` es siempre la opción segura.

Hoy vas a hacer los dos y comparar el dibujo que queda con:

```bash
git log --graph --oneline --all
```

---

## Parte 1 — Armar el equipo (10 min)

Júntense de a dos, **de la misma carrera** (para trabajar sobre el mismo lenguaje).
Definan quién es **A** y quién es **B**.

Cada uno tiene su propio repo del Día 1. Hoy **se abren el repo mutuamente**:

**Los dos** hacen, en su propio repo de GitHub:

`Settings` → `Collaborators` → `Add people` → usuario de tu compañera/o → `Add`

Y cada uno acepta la invitación que le llega (por mail o en github.com/notifications).

> A partir de ahora, A puede escribir en el repo de B y B en el de A.
> Así funciona un equipo interno de verdad.

---

## Parte 2 — Clonar el repo de tu compañera/o (10 min)

Cada uno clona el repo **del otro**. Como los dos repos se llaman igual, dale un
nombre distinto a la carpeta para no confundirte:

```bash
cd ..                                             # salí de tu repo del día 1
git clone <url-del-repo-de-tu-compañera/o> calculadora-de-<su-nombre>
cd calculadora-de-<su-nombre>
git config user.name "Tu Nombre"
git config user.email "tu-correo@ejemplo.com"
```

⚠️ **Muy importante:** volvé a configurar `user.name` y `user.email`, porque la
configuración es por repositorio. Si no, tus commits pueden quedar a nombre de otra persona.

Verificá dónde estás:

```bash
git remote -v      # ¿de quién es este repo?
git log --oneline  # ¿ves el commit de resta de tu compañera/o?
```

Ahora cada uno tiene **dos carpetas**: la suya y la de su compañera/o.
Tené clarísimo en cuál estás parada/o antes de cada comando.

---

## Parte 3 — Trabajo en paralelo en el repo de A (17 min)

📍 **Los dos** se paran en el **repo de A**
(A en su carpeta del Día 1, B en `calculadora-de-A`).

Cada uno crea **su propia rama**:

```bash
git checkout main
git pull origin main
git checkout -b feature/multiplicacion-<tu-nombre>
```

**Los dos** implementan `multiplicacion(a, b)` debajo del `/* TODO: multiplicacion */`,
descomentan su línea en `main`, y prueban:

```c
int multiplicacion(int a, int b) {
    return a * b;
}
```

```bash
gcc calculadora.c -o calculadora && ./calculadora
git add calculadora.c
git commit -m "Implementa función multiplicacion"
git push origin feature/multiplicacion-<tu-nombre>
```

Los dos abren su Pull Request en GitHub (recordá: contra `main` del repo de A).

👉 **Mergeen primero el PR de A.** Ahora mirá el PR de B: GitHub dice
**"This branch has conflicts that must be resolved"**.

**Eso es exactamente lo que queríamos.** Los dos tocaron las mismas líneas.

---

## Parte 4 — Resolver el conflicto con `merge` (18 min)

📍 **Persona B**, en tu carpeta `calculadora-de-A`:

```bash
git checkout main
git pull origin main                              # traigo lo que mergeó A
git checkout feature/multiplicacion-<B>
git merge main
```

Git te frena:

```
CONFLICT (content): Merge conflict in calculadora.c
Automatic merge failed; fix conflicts and then commit the result.
```

Abrí `calculadora.c`. Vas a ver algo así:

```c
<<<<<<< HEAD
    return a * b;              ← tu versión (la de la rama donde estás)
=======
    int resultado = a * b;     ← la versión que ya está en main (la de A)
    return resultado;
>>>>>>> main
```

**Resolver un conflicto = decidir qué código queda, y borrar las marcas.**

Borrá las tres líneas `<<<<<<<`, `=======` y `>>>>>>>`, y dejá el archivo como
tiene que quedar. A veces te quedás con una versión, a veces con la otra, a veces
con una mezcla de las dos. **Lo decidís vos, no Git.**

```bash
gcc calculadora.c -o calculadora && ./calculadora   # ¿compila y anda?
git status                                          # ¿queda algo en conflicto?
git add calculadora.c                               # así marcás "resuelto"
git commit                                          # sin -m: Git ya propone el mensaje
git push origin feature/multiplicacion-<B>
```

Volvé al PR en GitHub: el conflicto desapareció. **Merge.**

> 😰 ¿Te enredaste? `git merge --abort` deja todo como estaba antes de empezar.
> No rompiste nada. Respirá y volvé a intentar.

---

## ☕ Pausa (5 min)

---

## Parte 5 — El conflicto grande, con `rebase` (30 min)

📍 Ahora **los dos** se paran en el **repo de B**
(B en su carpeta del Día 1, A en `calculadora-de-B`).

```bash
git checkout main
git pull origin main
git checkout -b feature/division-<tu-nombre>
```

**Consigna para los dos:** implementá `division(a, b)` **justo debajo del
comentario `/* TODO: division */`**, contemplando qué pasa cuando `b` vale `0`.

🚫 **No se pongan de acuerdo en cómo hacerlo.** Cada uno lo resuelve como le parece:

- ¿Devolvés `0` y listo?
- ¿Imprimís un mensaje de error antes de devolver?
- ¿Devolvés `-1` como código de error?

Esa diferencia es la que va a chocar, y es la parte interesante.

Los dos: commit y push. **Mergeen primero el PR de B.**

Ahora **persona A** resuelve el conflicto, pero esta vez **con rebase**:

```bash
git checkout main
git pull origin main
git checkout feature/division-<A>
git rebase main
```

Cuando aparezca el conflicto:

```bash
# 1. Editás calculadora.c y dejás la versión final (igual que antes)
# 2. La probás
gcc calculadora.c -o calculadora && ./calculadora
# 3. La marcás como resuelta
git add calculadora.c
# 4. Seguís con el rebase   ⚠️ ACÁ NO SE HACE COMMIT
git rebase --continue
```

> ⚠️ La diferencia con el merge: en rebase **no hacés `git commit`**.
> `git rebase --continue` se encarga. Es el error más común.

> 😰 `git rebase --abort` cancela todo y te devuelve al estado inicial.

Para subir una rama rebaseada, el push normal va a fallar (porque reescribiste
la historia). Se usa:

```bash
git push --force-with-lease origin feature/division-<A>
```

> `--force-with-lease` en lugar de `--force`: si alguien más pusheó a esa rama
> mientras tanto, se niega en vez de pisarle el trabajo. **Usá siempre esta versión.**

Terminá con el merge del PR en GitHub.

### 💬 Conversen (5 min)

¿Cuál de las dos implementaciones de `division` quedó? ¿Por qué esa?
En un equipo real esa discusión pasa en los comentarios del Pull Request,
no en el pasillo.

---

## Parte 6 — Mirar lo que construyeron (10 min)

En cualquiera de los dos repos:

```bash
git log --graph --oneline --all
```

Buscá con el dedo:

- ¿Dónde está el commit de merge de la Parte 4? (el que tiene dos padres)
- En la Parte 5, con rebase, **no hay bifurcación**. ¿Ves la diferencia?
- ¿Aparecen commits con el nombre de tu compañera/o? Ese es el punto de todo esto.

---

## Chuletario de emergencia

| Situación | Comando |
| --- | --- |
| Me perdí, ¿dónde estoy? | `git status` |
| ¿Qué cambié? | `git diff` |
| ¿Cómo viene la historia? | `git log --graph --oneline --all` |
| Quiero descartar mis cambios de un archivo | `git restore <archivo>` |
| Me arrepentí del merge en curso | `git merge --abort` |
| Me arrepentí del rebase en curso | `git rebase --abort` |
| Escribí mal el mensaje del último commit | `git commit --amend` |
| Traer lo último de GitHub | `git pull origin main` |
| Rompí todo y no sé qué hice | `git reflog` (y llamá a la ayudante 🙂) |
