package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PasarelaLlegada extends Pasarela {

    private Jugador jugador;

    public PasarelaLlegada(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public Pane dibujarse(int width, int height) {
        Rectangle rect = new Rectangle(width, height);
        rect.setFill(Color.GOLD);
        StackPane pane = new StackPane();
        pane.getChildren().add(rect);
        return pane;
    }

    @Override
    public void avanzarTurno() { enemigos.clear(); }

    @Override
    public boolean esLaDeLLegada() { return true; }

    @Override
    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) {}

    public void realizarDanioJugador(int danio) { jugador.recibirDanio(danio); }

    @Override
    public Pasarela obtenerPasarelaSiguiente() { return this; }

    public void setJugador(Jugador jugador) { this.jugador = jugador; }

    public void destruirPrimeraTorre() { mapa.destruirPrimeraTorre(); }

    @Override
    public void construirTrampa(Trampa trampa) {}
}