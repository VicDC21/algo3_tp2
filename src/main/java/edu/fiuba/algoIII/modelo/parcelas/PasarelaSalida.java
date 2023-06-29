package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;


import java.util.ArrayList;
import java.util.List;

public class PasarelaSalida extends Pasarela {

    private ArrayList<List<Enemigo>> enemigosPorTurno = new ArrayList<>();

    public PasarelaSalida(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
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

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }
}
