package escenarioseisejeruno;

import java.util.Collection;

//CLASE BASE DEL ÁRBOL BINARIO
class VEDArbin<E> {
 protected Nodo<E> raiz;

 protected static class Nodo<E> {
     E dato;
     Nodo<E> izq, der;

     Nodo(E dato) {
         this.dato = dato;
         izq = der = null;
     }
 }

 // CONSTRUCTOR: CREA UN ÁRBOL VACÍO
 public VEDArbin() {
     raiz = null;
 }

 // CONSULTA BÁSICA: VERIFICA SI EL ÁRBOL ESTÁ VACÍO (CONSULTA BOOLEANA)
 public boolean esVacio() {
     return raiz == null;
 }

 // CONSULTA BÁSICA: RETORNA EL NÚMERO DE ELEMENTOS (CONSULTA DE TAMAÑO)
 public int peso() {
     return contar(raiz);
 }

 private int contar(Nodo<E> n) {
     if (n == null) return 0;
     return 1 + contar(n.izq) + contar(n.der);
 }
}

//ÁRBOL BINARIO ORDENADO
class VEDArbinOrdenado<E extends Comparable<E>> extends VEDArbin<E> {

 // CONSTRUCTOR: CREA UN ÁRBOL ORDENADO VACÍO
 public VEDArbinOrdenado() {
     super();
 }

 // CONSTRUCTOR: CREA UN ÁRBOL CON UNA COLECCIÓN DE DATOS
 public VEDArbinOrdenado(Collection<? extends E> coll) {
     super();
     addAll(coll);
 }

 // OPERACIÓN DE INSERCIÓN (NO ES CONSULTA)
 public void add(E e) {
     raiz = insertar(raiz, e);
 }

 private Nodo<E> insertar(Nodo<E> n, E e) {
     if (n == null) return new Nodo<>(e);

     if (e.compareTo(n.dato) < 0)
         n.izq = insertar(n.izq, e);
     else if (e.compareTo(n.dato) > 0)
         n.der = insertar(n.der, e);

     return n;
 }

 public void addAll(Collection<? extends E> coll) {
     for (E e : coll) add(e);
 }

 // CONSULTA: RETORNA EL SUBÁRBOL IZQUIERDO (CONSULTA ESTRUCTURAL)
 public VEDArbinOrdenado<E> getIzq() {
     VEDArbinOrdenado<E> izqArbol = new VEDArbinOrdenado<>();
     izqArbol.raiz = (raiz != null) ? raiz.izq : null;
     return izqArbol;
 }

 // CONSULTA: RETORNA EL SUBÁRBOL DERECHO (CONSULTA ESTRUCTURAL)
 public VEDArbinOrdenado<E> getDer() {
     VEDArbinOrdenado<E> derArbol = new VEDArbinOrdenado<>();
     derArbol.raiz = (raiz != null) ? raiz.der : null;
     return derArbol;
 }

 // CONSULTA BÁSICA: VERIFICA SI EL ÁRBOL ESTÁ VACÍO (CONSULTA BOOLEANA)
 public boolean isEmpty() {
     return esVacio();
 }

 // CONSULTA BÁSICA: RETORNA EL TAMAÑO DEL ÁRBOL (CONSULTA DE TAMAÑO)
 public int size() {
     return peso();
 }

 // BÚSQUEDA: DETERMINA SI UN ELEMENTO EXISTE EN EL ÁRBOL (CONSULTA DE PERTENENCIA)
 public boolean contains(Object obj) {
     if (esVacio()) return false;

     Comparable x = (Comparable) obj;
     Nodo<E> actual = raiz;

     while (actual != null) {
         int cmp = x.compareTo(actual.dato);

         if (cmp == 0)
             return true;
         else if (cmp < 0)
             actual = actual.izq;
         else
             actual = actual.der;
     }
     return false;
 }

 // DESTRUCTOR LÓGICO: ELIMINA TODOS LOS ELEMENTOS DEL ÁRBOL
 public void clear() {
     raiz = null;
 }
}

//CLASE PRINCIPAL DE PRUEBA
public class main {
 public static void main(String[] args) {

     VEDArbinOrdenado<Integer> arbol = new VEDArbinOrdenado<>();

     arbol.add(10);
     arbol.add(5);
     arbol.add(15);
     arbol.add(3);
     arbol.add(7);

     System.out.println("¿ESTÁ VACÍO?: " + arbol.isEmpty());
     System.out.println("TAMAÑO: " + arbol.size());

     System.out.println("BUSCAR 7: " + arbol.contains(7));
     System.out.println("BUSCAR 20: " + arbol.contains(20));

     System.out.println("TAMAÑO SUBÁRBOL IZQUIERDO: " + arbol.getIzq().size());
     System.out.println("TAMAÑO SUBÁRBOL DERECHO: " + arbol.getDer().size());
 }
}
