package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos;

    public Pasarela(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    
    protected Pasarela(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa);
        this.enemigos = new ArrayList<>();
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
        enemigos.removeIf(Enemigo::estaMuerto);
        for (Enemigo e : enemigos) {
            e.avanzar();
        }
    }

    @Override
    public void construir(Torre torre) {}

    @Override
    public void recibirDanio(int danio) {
        enemigos.get(0).recibirDanio(danio);
    }

    public void setPasarelaSiguiente(Pasarela pasarela) {
        this.pasarelaSiguiente = pasarela;
    }

    public int cantidadDeEnemigos() {
        return enemigos.size();
    }
}
