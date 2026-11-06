package escenarioseisejerunoconconcomentarios;

import java.util.Collection; // importa collection // permite trabajar con grupos de datos

//CLASE BASE DEL ÁRBOL BINARIO // define la estructura general del árbol
class VEDArbin<E> { // clase genérica // permite trabajar con cualquier tipo de dato
 protected Nodo<E> raiz; // nodo raíz del árbol // punto de inicio de la estructura

 protected static class Nodo<E> { // clase interna nodo // representa cada elemento del árbol
     E dato; // dato almacenado // valor del nodo
     Nodo<E> izq, der; // hijos izquierdo y derecho // enlaces a otros nodos

     Nodo(E dato) { // constructor del nodo // inicializa el nodo
         this.dato = dato; // asigna el valor // guarda el dato en el nodo
         izq = der = null; // inicializa hijos // no tiene hijos al inicio
     }
 }

 // CONSTRUCTOR: CREA UN ÁRBOL VACÍO // inicializa estructura sin datos
 public VEDArbin() {
     raiz = null; // raíz vacía // árbol sin elementos
 }

 // CONSULTA BÁSICA: VERIFICA SI EL ÁRBOL ESTÁ VACÍO (CONSULTA BOOLEANA) // retorna true o false
 public boolean esVacio() {
     return raiz == null; // compara raíz // si no existe nodo retorna true
 }

 // CONSULTA BÁSICA: RETORNA EL NÚMERO DE ELEMENTOS (CONSULTA DE TAMAÑO) // cuenta nodos
 public int peso() {
     return contar(raiz); // llama método recursivo // inicia conteo desde la raíz
 }

 private int contar(Nodo<E> n) { // método auxiliar // recorre el árbol
     if (n == null) return 0; // caso base // nodo vacío no suma
     return 1 + contar(n.izq) + contar(n.der); // suma nodos // recursividad izquierda y derecha
 }
}

//ÁRBOL BINARIO ORDENADO // estructura con orden
class VEDArbinOrdenado<E extends Comparable<E>> extends VEDArbin<E> { // herencia // permite comparar elementos

 // CONSTRUCTOR: CREA UN ÁRBOL ORDENADO VACÍO // inicializa sin datos
 public VEDArbinOrdenado() {
     super(); // llama constructor padre // crea árbol base
 }

 // CONSTRUCTOR: CREA UN ÁRBOL CON UNA COLECCIÓN DE DATOS // carga inicial de datos
 public VEDArbinOrdenado(Collection<? extends E> coll) {
     super(); // inicializa árbol vacío // base
     addAll(coll); // agrega elementos // inserta todos los datos
 }

 // OPERACIÓN DE INSERCIÓN (NO ES CONSULTA) // modifica la estructura
 public void add(E e) {
     raiz = insertar(raiz, e); // inserta nodo // actualiza raíz
 }

 private Nodo<E> insertar(Nodo<E> n, E e) { // método recursivo // ubica posición correcta
     if (n == null) return new Nodo<>(e); // crea nodo // inserta si está vacío

     if (e.compareTo(n.dato) < 0) // compara menor // decide ir a izquierda
         n.izq = insertar(n.izq, e); // inserta en izquierda // recursivo
     else if (e.compareTo(n.dato) > 0) // compara mayor // decide ir a derecha
         n.der = insertar(n.der, e); // inserta en derecha // recursivo

     return n; // retorna nodo // mantiene estructura
 }

 public void addAll(Collection<? extends E> coll) { // inserta múltiples elementos // recorrido de colección
     for (E e : coll) add(e); // ciclo // agrega uno a uno
 }

 // CONSULTA: RETORNA EL SUBÁRBOL IZQUIERDO (CONSULTA ESTRUCTURAL) // obtiene parte del árbol
 public VEDArbinOrdenado<E> getIzq() {
     VEDArbinOrdenado<E> izqArbol = new VEDArbinOrdenado<>(); // nuevo árbol // contenedor del subárbol
     izqArbol.raiz = (raiz != null) ? raiz.izq : null; // asigna raíz izquierda // verifica null
     return izqArbol; // retorna resultado // subestructura
 }

 // CONSULTA: RETORNA EL SUBÁRBOL DERECHO (CONSULTA ESTRUCTURAL) // obtiene lado derecho
 public VEDArbinOrdenado<E> getDer() {
     VEDArbinOrdenado<E> derArbol = new VEDArbinOrdenado<>(); // nuevo árbol // subárbol derecho
     derArbol.raiz = (raiz != null) ? raiz.der : null; // asigna raíz derecha // validación
     return derArbol; // retorna resultado // subestructura
 }

 // CONSULTA BÁSICA: VERIFICA SI EL ÁRBOL ESTÁ VACÍO (CONSULTA BOOLEANA) // evalúa estado
 public boolean isEmpty() {
     return esVacio(); // reutiliza método // delega en clase base
 }

 // CONSULTA BÁSICA: RETORNA EL TAMAÑO DEL ÁRBOL (CONSULTA DE TAMAÑO) // número de nodos
 public int size() {
     return peso(); // reutiliza método // cuenta nodos
 }

 // BÚSQUEDA: DETERMINA SI UN ELEMENTO EXISTE EN EL ÁRBOL (CONSULTA DE PERTENENCIA) // verifica existencia
 public boolean contains(Object obj) {
     if (esVacio()) return false; // valida vacío // evita recorrido innecesario

     Comparable x = (Comparable) obj; // conversión // permite comparar
     Nodo<E> actual = raiz; // inicia en raíz // punto de partida

     while (actual != null) { // recorrido iterativo // baja por el árbol
         int cmp = x.compareTo(actual.dato); // compara valores // decide dirección

         if (cmp == 0) // iguales // encontrado
             return true; // retorna éxito // termina búsqueda
         else if (cmp < 0) // menor // ir izquierda
             actual = actual.izq; // mueve referencia // sigue búsqueda
         else // mayor // ir derecha
             actual = actual.der; // mueve referencia // sigue búsqueda
     }
     return false; // no encontrado // fin del recorrido
 }

 // DESTRUCTOR LÓGICO: ELIMINA TODOS LOS ELEMENTOS DEL ÁRBOL // limpia estructura
 public void clear() {
     raiz = null; // elimina referencia // árbol queda vacío
 }
}

//CLASE PRINCIPAL DE PRUEBA // punto de ejecución
public class main {
 public static void main(String[] args) {

     VEDArbinOrdenado<Integer> arbol = new VEDArbinOrdenado<>(); // crea instancia // árbol de enteros

     arbol.add(10); // inserta dato // nodo raíz
     arbol.add(5); // inserta dato // lado izquierdo
     arbol.add(15); // inserta dato // lado derecho
     arbol.add(3); // inserta dato // más a la izquierda
     arbol.add(7); // inserta dato // rama izquierda

     System.out.println("¿ESTÁ VACÍO?: " + arbol.isEmpty()); // imprime estado // true o false
     System.out.println("TAMAÑO: " + arbol.size()); // imprime cantidad // total nodos

     System.out.println("BUSCAR 7: " + arbol.contains(7)); // busca valor // debería ser true
     System.out.println("BUSCAR 20: " + arbol.contains(20)); // busca valor // debería ser false

     System.out.println("TAMAÑO SUBÁRBOL IZQUIERDO: " + arbol.getIzq().size()); // tamaño izquierda // subárbol
     System.out.println("TAMAÑO SUBÁRBOL DERECHO: " + arbol.getDer().size()); // tamaño derecha // subárbol
 }
}