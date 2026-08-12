# Día 1 — Tu primer flujo completo con Git (60 min)

**Al final de esta hora vas a tener:** tu propio repositorio en GitHub, con la
función `resta` implementada por vos, integrada a `main` a través de un Pull Request.

Si en algún momento te perdés, **levantá la mano**. Nadie se queda atrás.

---

## Bloque 0 — Que la máquina esté lista (10 min)

### ¿Tenés Git?

```bash
git --version
```

Si te dice un número (`git version 2.x.x`), listo. Si dice "command not found",
avisá y lo instalamos.

### Que GitHub te reconozca

Cuando quieras subir código, GitHub te va a pedir que demuestres quién sos.
**Tu contraseña de GitHub no sirve para esto** (la sacaron en 2021). Hay dos formas:

**Opción A — con `gh` (la más fácil, si está instalado):**

```bash
gh auth login
```

Elegí: `GitHub.com` → `HTTPS` → `Y` → `Login with a web browser`.
Copiá el código que te muestra, apretá Enter, pegalo en el navegador.

**Opción B — con un token (si no tenés `gh`):**

1. Entrá a https://github.com/settings/tokens → `Generate new token (classic)`
2. Nombre: `taller-git`. Expiración: 7 días. Marcá la casilla **`repo`**.
3. `Generate token` y **copiá el token** (empieza con `ghp_...`).
   ⚠️ Es la única vez que te lo muestran. Pegalo en un archivo de texto por ahora.
4. Para que no te lo pida cada vez:

```bash
git config --global credential.helper store
```

Cuando Git te pida usuario y contraseña: usuario = tu usuario de GitHub,
contraseña = **el token**, no tu contraseña.

> 🔒 El token es una contraseña. No lo subas a ningún repo ni lo pegues en el chat.

---

## Bloque 1 — Fork (5 min)

Entrá al repo del taller en GitHub y apretá el botón **Fork**, arriba a la derecha.
→ `Create fork`.

Eso te crea **una copia tuya** del repositorio, en tu cuenta.

> Fijate que arriba ahora dice `tu-usuario/calculadora-java` y abajo, chiquito,
> *"forked from ..."*. **A partir de acá trabajás siempre sobre tu fork.**

---

## Bloque 2 — Clone y configuración (7 min)

**Clone** = bajar el repositorio de internet a tu computadora.

En **tu fork** (no el original), apretá el botón verde `Code` y copiá la URL HTTPS.

```bash
git clone <url-de-tu-fork>
cd calculadora-java
```

Ahora decile a Git quién sos, para que tus commits queden a tu nombre:

```bash
git config user.name "Nombre Apellido"
git config user.email "tu-correo@ejemplo.com"
```

Mirá dónde estás parada/o:

```bash
git status      # "On branch main, nothing to commit, working tree clean"
git log         # el historial: por ahora un solo commit
```

> Usá el mismo correo que tenés en GitHub, así los commits te aparecen asociados
> a tu perfil.

---

## Bloque 3 — Crear una rama (6 min)

En un equipo real **nunca se trabaja directo sobre `main`**. Cada cambio va en su
propia rama, y `main` siempre queda en un estado que funciona.

```bash
git checkout -b feature/resta
```

- `checkout` = "moveme a"
- `-b` = "creando la rama"

Verificá:

```bash
git branch      # el * te marca en qué rama estás
```

---

## Bloque 4 — Implementar `resta` y hacer commit (12 min)

Abrí `Calculadora.java` y, **justo debajo** del comentario `/* TODO: resta */`, escribí:

```java
public static int resta(int a, int b) {
    return a - b;
}
```

Y en `main`, **descomentá** la línea de la resta (sacale las `//` del principio):

```java
System.out.println("resta(5, 3) = " + resta(5, 3) + "   (esperado: 2)");
```

Probá que compile y ande:

```bash
javac Calculadora.java
java Calculadora
```

Ahora guardá el cambio en la historia del proyecto:

```bash
git status                       # ¿qué archivos cambiaron?
git diff                         # ¿exactamente qué líneas cambiaron?
git add Calculadora.java            # preparo el cambio
git commit -m "Implementa función resta"
git log --oneline                # ahí está tu commit
```

> **`add` y después `commit`, ¿por qué dos pasos?** `add` es armar el paquete
> (elegís qué cambios entran); `commit` es sellarlo y guardarlo en la historia.

> Un buen mensaje de commit dice **qué hace el cambio**: `"Implementa función resta"`,
> no `"cambios"`, `"asd"` ni `"final_final_v2"`.

---

## Bloque 5 — Push y Pull Request (8 min)

**Push** = subir tu rama a GitHub.

```bash
git push origin feature/resta
```

Andá a tu fork en GitHub y refrescá: aparece un cartel amarillo con
**Compare & pull request**. Apretalo.

Un **Pull Request** es pedir que tus cambios se integren a `main`. Es donde el
equipo revisa y discute el código antes de que entre.

1. Verificá que diga `base: main` ← `compare: feature/resta`, **de tu propio fork**.
2. Título y descripción de qué hiciste.
3. `Create pull request`.
4. Antes de mergear, entrá a la pestaña **Files changed**. Eso es exactamente lo
   que ve quien revisa tu código en un trabajo.

---

## Bloque 6 — Merge y cierre del círculo (8 min)

En el PR: `Merge pull request` → `Confirm merge`.

¡Tu código está en `main`! Pero **tu computadora todavía no se enteró**:

```bash
git checkout main
git log --oneline     # tu commit de resta NO está acá
git pull origin main  # traigo lo que pasó en GitHub
git log --oneline     # ahora sí
```

Ya podés borrar la rama, que cumplió su función:

```bash
git branch -d feature/resta
```

---

## ¿Terminaste antes? Desafíos extra

1. Implementá `potencia(base, exponente)` en una rama nueva, con su propio PR.
2. Probá `git log --graph --oneline --all`. ¿Qué te parece que dibuja?
3. Hacé un cambio, no lo commitees, y ejecutá `git restore Calculadora.java`.
   ¿Qué pasó? ¿Cuándo te puede servir eso?

---

## Chuletario

| Situación | Comando |
| --- | --- |
| Me perdí, ¿dónde estoy? | `git status` |
| ¿Qué cambié? | `git diff` |
| ¿Qué pasó antes? | `git log --oneline` |
| ¿En qué rama estoy? | `git branch` |
| Quiero descartar mis cambios | `git restore <archivo>` |
