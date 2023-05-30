package edu.fiuba.algo3.modelo;

public class Rocoso extends Parcela {

    public Rocoso(int id) {
        super(id);
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
