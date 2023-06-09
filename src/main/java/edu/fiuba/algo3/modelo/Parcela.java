package edu.fiuba.algo3.modelo;

public abstract class Parcela {
    protected int fila;
    protected int columna;
    Mapa mapa;

    public Parcela(int fila, int columna, Mapa mapa)
    {
        this.fila = fila;
        this.columna = columna;
        this.mapa = mapa;
    }
    public abstract boolean tieneEnemigos();
    public abstract void avanzarTurno();

    public abstract void construir(Torre torre);

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        return 0;
    }

    public boolean enRadioDe(int fila, int columna, int radio) {
        return (fila - radio <= this.fila) && (this.fila <= fila + radio) && (columna - radio <= this.columna) && (this.columna <= columna + radio);
    }

    public abstract void recibirDanio(int danio);

    public abstract int cantidadDeEnemigos();

    @Override
    public String toString() { return this.getClass().getSimpleName(); }

    public abstract void reset();

}
