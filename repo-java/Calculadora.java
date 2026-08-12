/* Calculadora del taller de Git — Semana de la Ingeniería 2026
 *
 * Compilar y ejecutar:
 *     javac Calculadora.java
 *     java Calculadora
 */

public class Calculadora {

    public static int suma(int a, int b) {
        return a + b;
    }

    /* TODO: resta — Día 1
     * Implementá resta(a, b) justo debajo de este comentario. */

    /* TODO: multiplicacion — Día 2
     * Implementá multiplicacion(a, b) justo debajo de este comentario. */

    /* TODO: division — Día 2
     * Implementá division(a, b) justo debajo de este comentario.
     * Pensá qué tiene que pasar si b vale 0. */

    public static void main(String[] args) {
        System.out.println("suma(2, 3) = " + suma(2, 3) + "   (esperado: 5)");

        /* Cuando implementes una función, descomentá su línea de acá abajo. */
        // System.out.println("resta(5, 3) = " + resta(5, 3) + "   (esperado: 2)");
        // System.out.println("multiplicacion(4, 3) = " + multiplicacion(4, 3) + "   (esperado: 12)");
        // System.out.println("division(10, 2) = " + division(10, 2) + "   (esperado: 5)");
    }
}
