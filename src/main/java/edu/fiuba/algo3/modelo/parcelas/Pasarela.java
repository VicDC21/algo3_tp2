package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.defensas.TrampaNull;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Pasarela extends Parcela {

    private static final Logger LOGGER = LoggerFactory.getLogger(Pasarela.class.getSimpleName());
    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos = new ArrayList<>();
    private List<Enemigo> arribos = new ArrayList<>();
    Trampa trampa = new TrampaNull();

    public Pasarela(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    public Pasarela(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa);
        this.pasarelaSiguiente = pasarelaSiguiente;
    }

    @Override
    public Pane dibujarse(int width, int height) {
        Rectangle rect = new Rectangle(width, height);
        rect.setFill(Color.GRAY);
        StackPane pane = new StackPane();
        pane.getChildren().add(rect);
        return pane;
    }
    @Override
    public boolean tieneEnemigos() {
        for (Enemigo e: enemigos) {
            if (!e.estaMuerto()) {
                return true;
            }
        }
        for (Enemigo e: arribos) {
            if (!e.estaMuerto()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void avanzarTurno() {
        if (!tieneEnemigos()) {
            return;
        }
        enemigos.forEach(Enemigo::avanzar);
    }

    public void removerMuertos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaMuerto()) {enemigo.desuscribirTodo();}
        }
        enemigos.removeIf(Enemigo::estaMuerto);
        arribos.removeIf(Enemigo::estaMuerto);
    }

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        return
                enemigos.stream()
                        .filter(Enemigo::estaMuerto)
                        .mapToInt(Enemigo::otorgarCredito)
                        .sum();
    }

    @Override
    public void construirTorre(Torre torre) { throw new ParcelaNoConstruible(); }

    @Override
    public void construirTrampa(Trampa trampa) {
        if (!tieneTrampa()) {
            this.trampa = trampa;
        } else throw new ParcelaNoConstruible();
    }

    private boolean tieneTrampa() { return !trampa.esNull(); }

    @Override
    public void recibirDanio(int danio) {

        Stream<Enemigo> enemigosTotales =
                Stream.concat(
                        enemigos.stream()
                                .filter(Objects::nonNull),
                        arribos.stream()
                                .filter(Objects::nonNull)
                );
        enemigosTotales.findAny()
                .orElseThrow()
                .recibirDanio(danio);
    }

    public void setPasarelaSiguiente(Pasarela pasarela) { this.pasarelaSiguiente = pasarela; }

    public int cantidadDeEnemigos() { return enemigos.size(); }

    public void actualizarEnemigos() {
        enemigos = arribos;
        arribos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            e.reestablecerVelocidad();
        }
    }

    public void recibirEnemigo(Enemigo enemigo) {
        this.arribos.add(enemigo);
        LOGGER.info("Pasarela recibiendo enemigo " + enemigo.getClass().getSimpleName());

    }

    public void realizarDanioJugador(int danio) {}

    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) {
        pasarelaSiguiente.recibirEnemigo(enemigo);
    }

    public Pasarela obtenerPasarelaSiguiente() {
        return pasarelaSiguiente;
    }

    public boolean esLaDeLLegada() {
        return false;
    }

    public void destruirPrimeraTorre() {}

    public void destruirConstuccion() { this.trampa = new TrampaNull(); }

    public void modificarVelocidad(double modificadorVelocidad) {
        for (Enemigo e : enemigos) {
            e.modificarVelocidadTierra(modificadorVelocidad);
        }
    }

    public void causarDanioAJugador(int danio) {
    }
}
