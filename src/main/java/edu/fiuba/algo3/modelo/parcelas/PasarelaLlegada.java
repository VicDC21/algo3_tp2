package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.JavaFX.PasarelaLlegadaPane;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;
import javafx.scene.layout.Pane;

public class PasarelaLlegada extends Pasarela {

    private Jugador jugador;

    public PasarelaLlegada(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public Pane dibujarse(int width, int height) {
        return new PasarelaLlegadaPane(width, height);
    }

    @Override
    public void avanzarTurno() { enemigos.clear(); }

    public void causarDanioJugador(int danio) { jugador.recibirDanio(danio); }

    @Override
    public Pasarela obtenerPasarelaSiguiente() { return this; }

    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public void destruirPrimeraTorre() { mapa.destruirPrimeraTorre(); }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }
}