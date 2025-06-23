package modelo;

import java.util.Objects;

import interfaces.IBarrio;
import interfaces.INodo;

public class Nodo implements INodo {
    private IBarrio barrio;
    private int distancia;
    private boolean visitado;

    public Nodo(IBarrio barrio) {
        this.barrio = barrio;
        this.distancia = Integer.MAX_VALUE;
        this.visitado = false;
    }

    public IBarrio getBarrio() { return barrio; }
    public void setBarrio(IBarrio barrio) { this.barrio = barrio; }

    public int getDistancia() { return distancia; }
    public void setDistancia(int distancia) { this.distancia = distancia; }

    public boolean isVisitado() { return visitado; }
    public void setVisitado(boolean visitado) { this.visitado = visitado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(barrio, nodo.barrio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barrio);
    }
}