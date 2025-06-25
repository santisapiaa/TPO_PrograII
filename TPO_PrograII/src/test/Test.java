package test;

import interfaces.IGrafo;
import modelo.Barrio;
import modelo.Grafo;
import interfaces.IBarrio;


public class Test {
    public static void main(String[] args) {
        IGrafo ciudad = new Grafo();

        IBarrio centro = new Barrio("Centro");
        IBarrio norte = new Barrio("Barrio Norte");
        IBarrio sur = new Barrio("Barrio Sur");
        IBarrio este = new Barrio("Barrio Este");
        IBarrio oeste = new Barrio("Barrio Oeste");

        ciudad.agregarBarrio(centro);
        ciudad.agregarBarrio(norte);
        ciudad.agregarBarrio(sur);
        ciudad.agregarBarrio(este);
        ciudad.agregarBarrio(oeste);

        ciudad.agregarArista(centro, norte, 5);
        ciudad.agregarArista(centro, sur, 7);
        ciudad.agregarArista(norte, este, 3);
        ciudad.agregarArista(sur, oeste, 4);
        ciudad.agregarArista(este, oeste, 2);
        ciudad.agregarArista(oeste, centro, 6);

        ciudad.dijkstra(centro);
        ciudad.mostrarCaminosDesde(centro);

        System.out.println("\nMatriz de Adyacencia:");
        ciudad.mostrarMatrizAdyacencia();
    }
}
