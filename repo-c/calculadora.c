/* Calculadora del taller de Git — Semana de la Ingeniería 2026
 *
 * Compilar y ejecutar:
 *     gcc calculadora.c -o calculadora
 *     ./calculadora
 */

#include <stdio.h>

int suma(int a, int b) {
    return a + b;
}

/* TODO: resta — Día 1
 * Implementá resta(a, b) justo debajo de este comentario. */

/* TODO: multiplicacion — Día 2
 * Implementá multiplicacion(a, b) justo debajo de este comentario. */

/* TODO: division — Día 2
 * Implementá division(a, b) justo debajo de este comentario.
 * Pensá qué tiene que pasar si b vale 0. */

int main(void) {
    printf("suma(2, 3) = %d   (esperado: 5)\n", suma(2, 3));

    /* Cuando implementes una función, descomentá su línea de acá abajo. */
    /* printf("resta(5, 3) = %d   (esperado: 2)\n", resta(5, 3)); */
    /* printf("multiplicacion(4, 3) = %d   (esperado: 12)\n", multiplicacion(4, 3)); */
    /* printf("division(10, 2) = %d   (esperado: 5)\n", division(10, 2)); */

    return 0;
}
