package interfaces;

public interface INodo {
    IBarrio getBarrio();
    void setBarrio(IBarrio barrio);
    int getDistancia();
    void setDistancia(int distancia);
    boolean isVisitado();
    void setVisitado(boolean visitado);
}