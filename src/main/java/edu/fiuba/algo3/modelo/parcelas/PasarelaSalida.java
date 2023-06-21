package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.JavaFX.PasarelaSalidaPane;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import javafx.scene.layout.Pane;


import java.util.ArrayList;
import java.util.List;

public class PasarelaSalida extends Pasarela {

    private ArrayList<List<Enemigo>> enemigosPorTurno = new ArrayList<>();

    public PasarelaSalida(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public Pane dibujarse(int width, int height) {
        return new PasarelaSalidaPane(width, height);
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
    public void construirTrampa(Trampa trampa) {}
}
