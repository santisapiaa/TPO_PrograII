package modelo;

import java.util.*;

import interfaces.IBarrio;
import interfaces.IGrafo;

public class Grafo implements IGrafo {
    private final Map<IBarrio, Nodo> nodos;
    private final Map<IBarrio, List<Arista>> adyacencias;
    private final Map<IBarrio, Integer> distancias;

    public Grafo() {
        nodos = new HashMap<>();
        adyacencias = new HashMap<>();
        distancias = new HashMap<>();
    }

    public void agregarBarrio(IBarrio barrio) {
        nodos.put(barrio, new Nodo(barrio));
        adyacencias.put(barrio, new ArrayList<>());
    }

    public void agregarArista(IBarrio origen, IBarrio destino, int peso) {
        adyacencias.get(origen).add(new Arista(destino, peso));
    }

    public void dijkstra(IBarrio inicio) {
        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparingInt(Nodo::getDistancia));
        Nodo nodoInicio = nodos.get(inicio);
        nodoInicio.setDistancia(0);
        cola.add(nodoInicio);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            if (actual.isVisitado()) continue;
            actual.setVisitado(true);

            for (Arista arista : adyacencias.get(actual.getBarrio())) {
                Nodo vecino = nodos.get(arista.destino);
                int nuevaDistancia = actual.getDistancia() + arista.peso;
                if (nuevaDistancia < vecino.getDistancia()) {
                    vecino.setDistancia(nuevaDistancia);
                    cola.add(vecino);
                }
            }
        }

        for (IBarrio barrio : nodos.keySet()) {
            distancias.put(barrio, nodos.get(barrio).getDistancia());
        }
    }

    public int obtenerDistancia(IBarrio destino) {
        return distancias.getOrDefault(destino, Integer.MAX_VALUE);
    }

    public void mostrarCaminosDesde(IBarrio inicio) {
        for (IBarrio barrio : nodos.keySet()) {
            int distancia = obtenerDistancia(barrio);
            System.out.println("Desde " + inicio.getNombre() + " hasta " + barrio.getNombre() + ": " +
                (distancia == Integer.MAX_VALUE ? "No alcanzable" : distancia + " minutos"));
        }
    }

    public void mostrarMatrizAdyacencia() {
        List<IBarrio> listaBarrios = new ArrayList<>(nodos.keySet());
        listaBarrios.sort(Comparator.comparing(IBarrio::getNombre));
        int ancho = 15; // ancho fijo por columna

        // Encabezado
        System.out.printf("%-" + ancho + "s", "");
        for (IBarrio destino : listaBarrios) {
            System.out.printf("%-" + ancho + "s", destino.getNombre());
        }
        System.out.println();

        // Filas
        for (IBarrio origen : listaBarrios) {
            System.out.printf("%-" + ancho + "s", origen.getNombre());
            for (IBarrio destino : listaBarrios) {
                int peso = obtenerPeso(origen, destino);
                String valor = (peso == Integer.MAX_VALUE) ? "0" : String.valueOf(peso);
                System.out.printf("%-" + ancho + "s", valor);
            }
            System.out.println();
        }
    }

    private int obtenerPeso(IBarrio origen, IBarrio destino) {
        for (Arista arista : adyacencias.get(origen)) {
            if (arista.destino.equals(destino)) {
                return arista.peso;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static class Arista {
        IBarrio destino;
        int peso;

        Arista(IBarrio destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }
}