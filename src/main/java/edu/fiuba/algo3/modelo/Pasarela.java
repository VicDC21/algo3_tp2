package edu.fiuba.algo3.modelo;

public class Pasarela extends Parcela {

    protected Pasarela(int fila, int columna) {
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
