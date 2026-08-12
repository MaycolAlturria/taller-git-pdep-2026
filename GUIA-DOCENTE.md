# Guía docente — Taller de Git, Semana de la Ingeniería 2026

Este archivo es **solo para vos**. No va a los repos de los alumnos.

- **Público:** estudiantes de Programador Universitario (C) e Ingeniería en
  Sistemas de Información (Java). **Nunca usaron Git.**
- **Convocatoria:** ~80 invitados, asistencia real esperada bastante menor.
- **Formato:** presentación + Día 1 (60 min) + Día 2 (120 min).

---

## Antes del taller

### 1. Subir los dos repos base a GitHub

Desde este directorio, para cada carpeta:

```bash
cd repo-c
git init
git add .
git commit -m "Repo base del taller: calculadora con suma implementada"
gh repo create calculadora-c --public --source=. --push
```

(Idem con `repo-java` → `calculadora-java`.)

Si preferís sin `gh`: creá los repos vacíos desde la web y después
`git remote add origin <url>` + `git push -u origin main`.

⚠️ **Creá los repos en tu cuenta personal, no en una organización.** Los forks
desde organizaciones a veces tienen restricciones que te van a arruinar el Día 1.

### 2. Probar el flujo completo vos misma

Hacé el Día 1 entero desde una cuenta de GitHub distinta (o pedile a alguien del
Club de Programación). Es media hora que te ahorra el desastre en vivo.

### 3. Mandar el prework (opcional pero recomendado)

Aunque el bloque de instalación está previsto en el Día 1, mandá 2 días antes:

> Para el taller traé tu notebook con **Git** instalado (https://git-scm.com/downloads)
> y una **cuenta de GitHub** creada. Si sos de Programador, tené `gcc`;
> si sos de Sistemas, el **JDK**. Con eso alcanza.

Cada persona que llegue con esto hecho es un problema menos en el Bloque 0.

### 4. Llevar impreso / proyectado

- La URL de los dos repos, **grande, en un slide fijo** durante todo el taller.
- El chuletario de comandos del final de cada consigna.

---

## Día 1 — Cronograma (60 min)

| Min | Bloque | Qué hacés vos |
| --- | --- | --- |
| 0–10 | Instalación y auth con GitHub | El bloque de mayor riesgo. Circulá. |
| 10–15 | Fork | Mostralo en pantalla, es un botón. |
| 15–22 | Clone + `git config` | Acá aparecen los problemas de auth que quedaron. |
| 22–28 | Crear rama | Explicá *por qué* no se trabaja en `main`. |
| 28–40 | Implementar `resta` + commit | El código es trivial a propósito. |
| 40–48 | Push + abrir PR | Mostrá **Files changed** en pantalla. |
| 48–56 | Merge + `git pull` | El momento "ahá": GitHub y mi compu son cosas distintas. |
| 56–60 | Cierre | Repaso del ciclo completo en un diagrama. |

### El PR va contra el propio fork

Cada alumno mergea **su propio PR en su propio fork**. No contra tu repo original.
Con 20+ personas, revisar PRs en vivo es un cuello de botella que te come el taller.

### Puntos donde se traba la gente (Día 1)

| Síntoma | Causa | Solución |
| --- | --- | --- |
| `Authentication failed` al pushear | Puso su contraseña de GitHub | Token, no contraseña. Ver Bloque 0. |
| `fatal: not a git repository` | No hizo `cd` al repo | `cd calculadora-c` |
| Clonó el repo original en vez del fork | No miró la URL | `git remote -v`, y clonar de nuevo |
| No le aparece el botón del PR | No hizo push, o pushó a `main` | `git branch` + `git push origin <rama>` |
| El commit sale a nombre de otra persona | Git config global de otro | `git config user.email` dentro del repo |
| `Please tell me who you are` | No hizo `git config` | Bloque 2 |

### Si alguien queda muy atrás

Que se junte con quien tenga al lado y miren una sola pantalla. Es mejor que
completen el ciclo de a dos que que abandonen a la mitad solos.

---

## Día 2 — Cronograma (120 min)

| Min | Bloque | Notas |
| --- | --- | --- |
| 0–15 | Teoría merge vs rebase | Diagramas en pizarra o slide. |
| 15–25 | Parejas + agregar colaborador | **Parejas de la misma carrera.** |
| 25–35 | Clone cruzado | Insistí con el `git config` en el repo nuevo. |
| 35–52 | Repo de A: los dos hacen `multiplicacion` | Se genera el primer conflicto. |
| 52–70 | Resolver conflicto con `merge` | B resuelve. Bloque clave. |
| 70–75 | ☕ Pausa | |
| 75–105 | Repo de B: `division` + resolver con `rebase` | A resuelve. Bloque clave. |
| 105–115 | `git log --graph` + comparación | El cierre conceptual. |
| 115–120 | Cierre | |

### El modelo de colaboración

Cada pareja usa **los dos repos**, y cada persona pasa por los dos roles:

```
Repo de A  →  ejercicio de multiplicacion  →  A es dueño y mergea primero
                                              B resuelve el conflicto (merge)

Repo de B  →  ejercicio de division       →  B es dueño y mergea primero
                                              A resuelve el conflicto (rebase)
```

Así los dos experimentan resolver un conflicto y los dos escriben en el repo de otro.

Cada uno tiene **dos carpetas** en su compu. La confusión de "en qué carpeta estoy"
es el error #1 del Día 2: recordales `pwd` y `git remote -v` todo el tiempo.

### Cómo garantizar el conflicto

El conflicto está diseñado en el archivo base: los `TODO` están pegados y la
línea de impresión de cada función está en el mismo bloque de `main`. Aunque los
dos escriban código idéntico en la función, **la línea que descomentan en `main`
está en la misma zona**, así que Git no puede resolverlo solo.

Si aun así a alguna pareja le mergea limpio: pedile a uno de los dos que además
agregue un comentario arriba de la función. Choca seguro.

Para el conflicto de `division`, la consigna es explícitamente **no ponerse de
acuerdo**. Si se coordinan, no hay conflicto y se pierde el ejercicio. Decilo en voz alta.

### Puntos donde se traba la gente (Día 2)

| Síntoma | Causa | Solución |
| --- | --- | --- |
| `Permission denied` al pushear al repo del otro | No aceptó la invitación de colaborador | github.com/notifications |
| Editó el archivo pero quedan `<<<<<<<` | No borró las marcas | Mostrá en pantalla cómo queda resuelto |
| En rebase hace `git commit` | Instinto del merge | `git rebase --continue`, sin commit |
| `Updates were rejected` post-rebase | Reescribió la historia | `git push --force-with-lease` |
| Está en `detached HEAD` | Se perdió en el rebase | `git rebase --abort` |
| Pánico general | Es normal | `git reflog`, o borrar la carpeta y clonar de nuevo |

> **La salida de emergencia siempre existe:** borrar la carpeta local y volver a
> clonar. El trabajo pusheado no se pierde. Decíselo al principio del Día 2, baja
> muchísimo la ansiedad.

---

## Ideas de cierre

Preguntas para cerrar el Día 2, mejor que un resumen tuyo:

- ¿Por qué `main` tiene que estar siempre funcionando?
- ¿Cuándo usarías merge y cuándo rebase?
- ¿Qué información te da el historial que no te da el código?
- ¿Qué pasaría si trabajaran los dos directo sobre `main`, sin ramas?

Y algo para llevarse: que apliquen esto en el próximo TP grupal de la facultad.
Es el mejor uso posible de lo que aprendieron.
