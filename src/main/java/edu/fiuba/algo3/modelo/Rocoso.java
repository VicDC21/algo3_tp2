package edu.fiuba.algo3.modelo;

public class Rocoso extends Parcela {

    protected Rocoso(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public boolean puedeAlojarTorre() {
        return false;
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
    }

    @Override
    public void construir(Torre torre) {
    }
}
