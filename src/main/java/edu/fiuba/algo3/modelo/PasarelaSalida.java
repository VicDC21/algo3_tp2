package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class PasarelaSalida extends Pasarela {

    private List<List> enemigosPorTurno = new ArrayList<List>();

    public PasarelaSalida(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa, pasarelaSiguiente);
        Hormiga hormiga = new Hormiga(1, 1, 1, "asd", this);
        agregarEnemigo(hormiga);
    }
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    @Override
    public void avanzarTurno() {
        enemigos.removeIf(Enemigo::estaMuerto);
        for (Enemigo e : enemigos) {
            e.avanzar();
        }
        if (!enemigosPorTurno.isEmpty()) {
            for (Enemigo e : (enemigosPorTurno.remove(0))) {
                agregarEnemigo(e);
            }
        }
    }

    @Override
    public boolean tieneEnemigos() {
        if (enemigos.isEmpty()) {
            return (!enemigosPorTurno.isEmpty());
        }
        return true;
    }

}
