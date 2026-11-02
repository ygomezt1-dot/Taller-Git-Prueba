package grafo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Programa de consola para practicar las operaciones basicas de un grafo no dirigido.
 * Cada vertice guarda el conjunto de vertices a los que esta conectado.
 */
public class GrafoOperaciones {

    private static final Map<String, Set<String>> grafo = new LinkedHashMap<>();

    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            int opcion;

            do {
                mostrarMenu();
                opcion = leerEntero(teclado, "Seleccione una opcion: ");

                switch (opcion) {
                    case 1:
                        agregarVertice(teclado);
                        break;
                    case 2:
                        agregarArista(teclado);
                        break;
                    case 3:
                        buscarVertice(teclado);
                        break;
                    case 4:
                        eliminarArista(teclado);
                        break;
                    case 5:
                        eliminarVertice(teclado);
                        break;
                    case 6:
                        mostrarGrafo();
                        break;
                    case 7:
                        cargarEjemplo();
                        break;
                    case 0:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                }
                System.out.println();
            } while (opcion != 0);
        }
    }

    private static void mostrarMenu() {
        System.out.println("==================================");
        System.out.println("       OPERACIONES DE UN GRAFO");
        System.out.println("==================================");
        System.out.println("1. Agregar vertice");
        System.out.println("2. Agregar conexion (arista)");
        System.out.println("3. Buscar vertice");
        System.out.println("4. Eliminar conexion (arista)");
        System.out.println("5. Eliminar vertice");
        System.out.println("6. Mostrar grafo");
        System.out.println("7. Cargar ejemplo de ciudades");
        System.out.println("0. Salir");
    }

    private static int leerEntero(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = teclado.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static String leerNombre(Scanner teclado, String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim();
    }

    private static void agregarVertice(Scanner teclado) {
        String nombre = leerNombre(teclado, "Nombre del vertice: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
        } else if (grafo.containsKey(nombre)) {
            System.out.println("Ese vertice ya existe.");
        } else {
            grafo.put(nombre, new LinkedHashSet<>());
            System.out.println("Vertice agregado: " + nombre);
        }
    }

    private static void agregarArista(Scanner teclado) {
        String origen = leerNombre(teclado, "Vertice de origen: ");
        String destino = leerNombre(teclado, "Vertice de destino: ");

        if (origen.equals(destino)) {
            System.out.println("No se puede crear una conexion de un vertice consigo mismo.");
            return;
        }
        if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            System.out.println("Ambos vertices deben existir. Agreguelos primero.");
            return;
        }
        if (grafo.get(origen).contains(destino)) {
            System.out.println("La conexion ya existe.");
            return;
        }

        grafo.get(origen).add(destino);
        grafo.get(destino).add(origen);
        System.out.println("Conexion agregada entre " + origen + " y " + destino + ".");
    }

    private static void buscarVertice(Scanner teclado) {
        String nombre = leerNombre(teclado, "Nombre del vertice que desea buscar: ");
        if (grafo.containsKey(nombre)) {
            System.out.println("El vertice " + nombre + " existe.");
            System.out.println("Conexiones: " + grafo.get(nombre));
        } else {
            System.out.println("El vertice no existe en el grafo.");
        }
    }

    private static void eliminarArista(Scanner teclado) {
        String origen = leerNombre(teclado, "Vertice de origen: ");
        String destino = leerNombre(teclado, "Vertice de destino: ");

        if (!grafo.containsKey(origen) || !grafo.containsKey(destino)) {
            System.out.println("No se puede eliminar: uno o ambos vertices no existen.");
            return;
        }
        if (!grafo.get(origen).contains(destino)) {
            System.out.println("La conexion no existe.");
            return;
        }

        grafo.get(origen).remove(destino);
        grafo.get(destino).remove(origen);
        System.out.println("Conexion eliminada entre " + origen + " y " + destino + ".");
    }

    private static void eliminarVertice(Scanner teclado) {
        String nombre = leerNombre(teclado, "Nombre del vertice que desea eliminar: ");
        if (!grafo.containsKey(nombre)) {
            System.out.println("El vertice no existe.");
            return;
        }

        for (String vecino : new ArrayList<>(grafo.get(nombre))) {
            grafo.get(vecino).remove(nombre);
        }
        grafo.remove(nombre);
        System.out.println("Vertice eliminado: " + nombre + " y sus conexiones.");
    }

    private static void mostrarGrafo() {
        if (grafo.isEmpty()) {
            System.out.println("El grafo esta vacio.");
            return;
        }

        System.out.println("Grafo (vertice -> conexiones):");
        for (Map.Entry<String, Set<String>> entrada : grafo.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }

    private static void cargarEjemplo() {
        grafo.clear();
        agregarVerticeSiNoExiste("Bogota");
        agregarVerticeSiNoExiste("Ibague");
        agregarVerticeSiNoExiste("Medellin");
        agregarVerticeSiNoExiste("Armenia");
        agregarAristaSiNoExiste("Bogota", "Ibague");
        agregarAristaSiNoExiste("Bogota", "Medellin");
        agregarAristaSiNoExiste("Ibague", "Armenia");
        System.out.println("Se cargo el ejemplo de ciudades y conexiones.");
    }

    private static void agregarVerticeSiNoExiste(String nombre) {
        grafo.putIfAbsent(nombre, new LinkedHashSet<>());
    }

    private static void agregarAristaSiNoExiste(String origen, String destino) {
        grafo.get(origen).add(destino);
        grafo.get(destino).add(origen);
    }
}
