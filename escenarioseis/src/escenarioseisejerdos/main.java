package escenarioseisejerdos;

import java.util.Scanner;

class ArbolBinarioOrdenado<E extends Comparable<E>> {

    class Nodo {
        E dato;
        Nodo izq, der;

        Nodo(E dato) {
            this.dato = dato;
            izq = der = null;
        }
    }

    private Nodo raiz;

    // ===== INSERTAR =====
    public boolean insertar(E x) {
        if (raiz == null) {
            raiz = new Nodo(x);
            return true;
        } else {
            return insertarRec(raiz, x);
        }
    }

    private boolean insertarRec(Nodo nodo, E x) {
        if (x.compareTo(nodo.dato) < 0) {
            if (nodo.izq == null) {
                nodo.izq = new Nodo(x);
                return true;
            } else {
                return insertarRec(nodo.izq, x);
            }
        } else if (x.compareTo(nodo.dato) > 0) {
            if (nodo.der == null) {
                nodo.der = new Nodo(x);
                return true;
            } else {
                return insertarRec(nodo.der, x);
            }
        } else {
            return false; // no repetidos
        }
    }

    // ===== BUSCAR =====
    public boolean contiene(E x) {
        return contieneRec(raiz, x);
    }

    private boolean contieneRec(Nodo nodo, E x) {
        if (nodo == null) return false;

        if (x.compareTo(nodo.dato) < 0)
            return contieneRec(nodo.izq, x);
        else if (x.compareTo(nodo.dato) > 0)
            return contieneRec(nodo.der, x);
        else
            return true;
    }

    // ===== MAXIMO =====
    private E max(Nodo nodo) {
        if (nodo.der == null)
            return nodo.dato;
        else
            return max(nodo.der);
    }

    // ===== ELIMINAR =====
    public boolean eliminar(E x) {
        if (contiene(x)) {
            raiz = eliminarRec(raiz, x);
            return true;
        }
        return false;
    }

    private Nodo eliminarRec(Nodo nodo, E x) {
        if (nodo == null) return null;

        if (x.compareTo(nodo.dato) < 0) {
            nodo.izq = eliminarRec(nodo.izq, x);
        } else if (x.compareTo(nodo.dato) > 0) {
            nodo.der = eliminarRec(nodo.der, x);
        } else {
            // caso 1: hoja
            if (nodo.izq == null && nodo.der == null) {
                return null;
            }
            // caso 2: un hijo
            else if (nodo.izq == null) {
                return nodo.der;
            } else if (nodo.der == null) {
                return nodo.izq;
            }
            // caso 3: dos hijos
            else {
                E maxIzq = max(nodo.izq);
                nodo.dato = maxIzq;
                nodo.izq = eliminarRec(nodo.izq, maxIzq);
            }
        }
        return nodo;
    }

    // ===== RECORRIDO INORDEN =====
    public void inorden() {
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.izq);
            System.out.print(nodo.dato + " ");
            inordenRec(nodo.der);
        }
    }
}

// ===== CLASE PRINCIPAL CON MENU =====
public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinarioOrdenado<Integer> arbol = new ArbolBinarioOrdenado<>();

        int opcion;

        do {
            System.out.println("\n--- MENU ARBOL BINARIO ---");
            System.out.println("1. Insertar");
            System.out.println("2. Eliminar");
            System.out.println("3. Mostrar (Inorden)");
            System.out.println("4. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor: ");
                    int valorInsertar = sc.nextInt();
                    if (arbol.insertar(valorInsertar))
                        System.out.println("Insertado correctamente");
                    else
                        System.out.println("El valor ya existe");
                    break;

                case 2:
                    System.out.print("Ingrese valor a eliminar: ");
                    int valorEliminar = sc.nextInt();
                    if (arbol.eliminar(valorEliminar))
                        System.out.println("Eliminado correctamente");
                    else
                        System.out.println("No existe en el árbol");
                    break;

                case 3:
                    System.out.println("Recorrido Inorden:");
                    arbol.inorden();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 4);

        sc.close();
    }
}
