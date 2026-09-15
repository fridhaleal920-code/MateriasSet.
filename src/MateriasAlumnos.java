import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MateriasAlumnos {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Map<String, Set<String>> alumnos = new HashMap<>();

        System.out.println("=== MATERIAS INSCRITAS POR ALUMNOS ===");

        System.out.print("Ingresa el nombre del primer alumno: ");
        String nombre = teclado.nextLine();

        alumnos.put(nombre, new HashSet<>());

        System.out.print("Ingresa el nombre del segundo alumno: ");
        nombre = teclado.nextLine();

        alumnos.put(nombre, new HashSet<>());

        String respuesta;

        do {
            System.out.print("¿Hay otro alumno? (si/no): ");
            respuesta = teclado.nextLine();

            if (respuesta.equalsIgnoreCase("si")) {
                System.out.print("Ingresa el nombre del alumno: ");
                nombre = teclado.nextLine();

                alumnos.put(nombre, new HashSet<>());
            }

        } while (respuesta.equalsIgnoreCase("si"));

        for (String alumno : alumnos.keySet()) {

            System.out.print("\n¿Cuántas materias tiene " + alumno + "? ");
            int cantidad = teclado.nextInt();
            teclado.nextLine();

            Set<String> materias = alumnos.get(alumno);

            for (int i = 0; i < cantidad; i++) {
                System.out.print("Ingresa la materia " + (i + 1) + ": ");
                String materia = teclado.nextLine();

                materias.add(materia);
            }
        }

        System.out.println("\n=== MATERIAS INSCRITAS ===");

        for (String alumno : alumnos.keySet()) {
            System.out.println(alumno + " = " + alumnos.get(alumno));
        }

        Set<String> todasLasMaterias = new HashSet<>();

        for (Set<String> materias : alumnos.values()) {
            todasLasMaterias.addAll(materias);
        }

        Set<String> materiasEnComun = new HashSet<>();

        boolean primerAlumno = true;

        for (Set<String> materias : alumnos.values()) {

            if (primerAlumno) {
                materiasEnComun.addAll(materias);
                primerAlumno = false;
            } else {
                materiasEnComun.retainAll(materias);
            }
        }

        System.out.println("\n=== RESULTADOS ===");

        System.out.println("Materias en común entre todos: " + materiasEnComun);

        System.out.println("Todas las materias: " + todasLasMaterias);

        for (String alumno : alumnos.keySet()) {

            Set<String> materiasExclusivas = new HashSet<>(alumnos.get(alumno));

            for (String otroAlumno : alumnos.keySet()) {

                if (!alumno.equals(otroAlumno)) {
                    materiasExclusivas.removeAll(alumnos.get(otroAlumno));
                }
            }

            System.out.println(
                    "Materias que solo tiene " + alumno + ": "
                            + materiasExclusivas
            );
        }

        System.out.println("\n=== PRUEBAS ===");

        System.out.print("Ingresa una materia para comprobar quién la tiene: ");
        String materiaBuscar = teclado.nextLine();

        boolean encontrada = false;

        for (String alumno : alumnos.keySet()) {

            Set<String> materias = alumnos.get(alumno);

            if (materias.contains(materiaBuscar)) {
                System.out.println(alumno + " tiene " + materiaBuscar);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("Ningún alumno tiene " + materiaBuscar);
        }

        System.out.println("\nCantidad de alumnos: " + alumnos.size());
        System.out.println("Cantidad de materias diferentes: " + todasLasMaterias.size());
        System.out.println("Cantidad de materias en común: " + materiasEnComun.size());

        teclado.close();
    }
}