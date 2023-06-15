package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;

import java.util.ArrayList;
import java.util.List;

public class PasarelaSalida extends Pasarela {

    private ArrayList<List<Enemigo>> enemigosPorTurno = new ArrayList<>();

    public PasarelaSalida(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

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
        if (!enemigosPorTurno.isEmpty()) {
            for (Enemigo e : enemigosPorTurno.get(0)) {
                e.suscribirTodo();
                agregarEnemigo(e);
            }
            enemigosPorTurno.remove(0);
        }
        super.avanzarTurno();
    }

    @Override
    public boolean tieneEnemigos() {
        if (enemigos.isEmpty()) {
            return (!enemigosPorTurno.isEmpty());
        }
        return true;
    }
    public void cargarEnemigos(ArrayList<List<Enemigo>> enemigosParseados) {
        enemigosPorTurno = enemigosParseados;
        enemigosPorTurno.forEach(list -> list.forEach(enemigo -> enemigo.setPasarelaSalida(this)));
    }
}
