package edu.fiuba.algo3.modelo;

import java.util.List;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos;
    
    protected Pasarela(int fila, int columna, Pasarela pasarelaSiguiente) {
        super(fila, columna);
        this.pasarelaSiguiente = pasarelaSiguiente;
    }

    @Override
    public boolean puedeAlojarTorre() {
        return false;
    }

    @Override
    public boolean tieneEnemigos() {
        return !enemigos.isEmpty();
    }

    @Override
    public void avanzarTurno() {

    }

    @Override
    public void construir(Torre torre) {

    }
}
