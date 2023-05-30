package edu.fiuba.algo3.modelo;

public abstract class Parcela {
    int id;
    public abstract boolean puedeAlojarTorre();
    public abstract boolean tieneEnemigos();
    public abstract void avanzarTurno();

    public abstract void construir(Torre torre);

}
