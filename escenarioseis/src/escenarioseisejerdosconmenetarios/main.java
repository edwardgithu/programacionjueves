package escenarioseisejerdosconmenetarios;

import java.util.Scanner; // importa scanner para entrada por teclado

class ArbolBinarioOrdenado<E extends Comparable<E>> { // clase generica de arbol binario ordenado

    class Nodo { // clase interna nodo
        E dato; // valor del nodo
        Nodo izq, der; // referencias a hijos izquierdo y derecho

        Nodo(E dato) { // constructor del nodo
            this.dato = dato; // asigna el dato
            izq = der = null; // inicializa hijos en null
        }
    }

    private Nodo raiz; // raiz del arbol

    // ===== INSERTAR =====
    public boolean insertar(E x) { // metodo publico insertar
        if (raiz == null) { // si el arbol esta vacio
            raiz = new Nodo(x); // crea la raiz
            return true; // insercion exitosa
        } else {
            return insertarRec(raiz, x); // llama metodo recursivo
        }
    }

    private boolean insertarRec(Nodo nodo, E x) { // metodo recursivo insertar
        if (x.compareTo(nodo.dato) < 0) { // si es menor va a la izquierda
            if (nodo.izq == null) { // si no hay hijo izquierdo
                nodo.izq = new Nodo(x); // inserta ahi
                return true;
            } else {
                return insertarRec(nodo.izq, x); // sigue buscando
            }
        } else if (x.compareTo(nodo.dato) > 0) { // si es mayor va a la derecha
            if (nodo.der == null) { // si no hay hijo derecho
                nodo.der = new Nodo(x); // inserta ahi
                return true;
            } else {
                return insertarRec(nodo.der, x); // sigue buscando
            }
        } else {
            return false; // no permite valores repetidos
        }
    }

    // ===== BUSCAR =====
    public boolean contiene(E x) { // metodo publico buscar
        return contieneRec(raiz, x); // llama metodo recursivo
    }

    private boolean contieneRec(Nodo nodo, E x) { // metodo recursivo buscar
        if (nodo == null) return false; // si llega a null no existe

        if (x.compareTo(nodo.dato) < 0) // si es menor
            return contieneRec(nodo.izq, x); // busca izquierda
        else if (x.compareTo(nodo.dato) > 0) // si es mayor
            return contieneRec(nodo.der, x); // busca derecha
        else
            return true; // encontrado
    }

    // ===== MAXIMO =====
    private E max(Nodo nodo) { // metodo para obtener el mayor
        if (nodo.der == null) // si no hay mas a la derecha
            return nodo.dato; // retorna el mayor
        else
            return max(nodo.der); // sigue a la derecha
    }

    // ===== ELIMINAR =====
    public boolean eliminar(E x) { // metodo publico eliminar
        if (contiene(x)) { // verifica si existe
            raiz = eliminarRec(raiz, x); // elimina recursivamente
            return true;
        }
        return false; // no existe
    }

    private Nodo eliminarRec(Nodo nodo, E x) { // metodo recursivo eliminar
        if (nodo == null) return null; // caso base

        if (x.compareTo(nodo.dato) < 0) { // si es menor
            nodo.izq = eliminarRec(nodo.izq, x); // va a la izquierda
        } else if (x.compareTo(nodo.dato) > 0) { // si es mayor
            nodo.der = eliminarRec(nodo.der, x); // va a la derecha
        } else { // si encuentra el nodo
            // caso 1: hoja
            if (nodo.izq == null && nodo.der == null) {
                return null; // elimina directamente
            }
            // caso 2: un hijo
            else if (nodo.izq == null) {
                return nodo.der; // reemplaza por hijo derecho
            } else if (nodo.der == null) {
                return nodo.izq; // reemplaza por hijo izquierdo
            }
            // caso 3: dos hijos
            else {
                E maxIzq = max(nodo.izq); // obtiene mayor del subarbol izquierdo
                nodo.dato = maxIzq; // reemplaza dato
                nodo.izq = eliminarRec(nodo.izq, maxIzq); // elimina el duplicado
            }
        }
        return nodo; // retorna nodo actualizado
    }

    // ===== RECORRIDO INORDEN =====
    public void inorden() { // metodo publico inorden
        inordenRec(raiz); // llama recursivo
        System.out.println(); // salto de linea
    }

    private void inordenRec(Nodo nodo) { // metodo recursivo inorden
        if (nodo != null) { // si el nodo no es null
            inordenRec(nodo.izq); // recorre izquierda
            System.out.print(nodo.dato + " "); // imprime dato
            inordenRec(nodo.der); // recorre derecha
        }
    }
}

// ===== CLASE PRINCIPAL CON MENU =====
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // objeto scanner
        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>(); // crea arbol

        int opcion; // variable opcion

        do {
            System.out.println("\n--- MENU ARBOL BINARIO ---"); // titulo
            System.out.println("1. Insertar"); // opcion insertar
            System.out.println("2. Eliminar"); // opcion eliminar
            System.out.println("3. Mostrar (Inorden)"); // opcion mostrar
            System.out.println("4. Salir"); // opcion salir
            System.out.print("Seleccione: "); // pide opcion
            opcion = sc.nextInt(); // lee opcion

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor: "); // pide valor
                    int valorInsertar = sc.nextInt(); // lee valor
                    if (arbol.insertar(valorInsertar)) // inserta
                        System.out.println("Insertado correctamente");
                    else
                        System.out.println("El valor ya existe");
                    break;

                case 2:
                    System.out.print("Ingrese valor a eliminar: "); // pide valor
                    int valorEliminar = sc.nextInt(); // lee valor
                    if (arbol.eliminar(valorEliminar)) // elimina
                        System.out.println("Eliminado correctamente");
                    else
                        System.out.println("No existe en el árbol");
                    break;

                case 3:
                    System.out.println("Recorrido Inorden:"); // muestra recorrido
                    arbol.inorden(); // imprime arbol
                    break;

                case 4:
                    System.out.println("Saliendo..."); // mensaje salida
                    break;

                default:
                    System.out.println("Opción inválida"); // opcion incorrecta
            }

        } while (opcion != 4); // repite hasta salir

        sc.close(); // cierra scanner
    }
}