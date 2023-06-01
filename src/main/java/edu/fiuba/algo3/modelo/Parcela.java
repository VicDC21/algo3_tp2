package edu.fiuba.algo3.modelo;

public abstract class Parcela {
    protected int fila;
    protected int columna;
    protected Parcela(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
    }
    public abstract boolean puedeAlojarTorre();
    public abstract boolean tieneEnemigos();
    public abstract void avanzarTurno();

    public abstract void construir(Torre torre);

}
