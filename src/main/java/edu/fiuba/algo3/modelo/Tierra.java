package edu.fiuba.algo3.modelo;

public class Tierra extends Parcela {

    Torre torre;
    public Tierra (int id) {
        this.id = id;
    }
    @Override
    public boolean puedeAlojarTorre() {
        return torre == null;
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
        if (torre != null) {
            torre.avanzarTurno();
        }
    }

    @Override
    public void construir(Torre torre) {
        this.torre = torre;
    }
}
