package interfaces;

public interface IGrafo {
    void agregarBarrio(IBarrio barrio);
    void agregarArista(IBarrio origen, IBarrio destino, int peso);
    void dijkstra(IBarrio inicio);
    int obtenerDistancia(IBarrio destino);
    void mostrarCaminosDesde(IBarrio inicio);
    void mostrarMatrizAdyacencia();
}