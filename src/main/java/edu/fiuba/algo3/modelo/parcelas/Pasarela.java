package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.JavaFX.PasarelaPane;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.defensas.TrampaNull;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;
import javafx.scene.layout.Pane;
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
        return new PasarelaPane(width, height);
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
        trampa.avanzarTurno();
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
        this.trampa = trampa;
        trampa.setPasarela(this);
    }

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
        LOGGER.info("Pasarela "+ this.fila+ " " + this.columna +" recibiendo enemigo " + enemigo.getClass().getSimpleName());

    }

    public void causarDanioJugador(int danio) {}

    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) { pasarelaSiguiente.recibirEnemigo(enemigo); }

    public Pasarela obtenerPasarelaSiguiente() { return pasarelaSiguiente; }

    public void destruirPrimeraTorre() {}

    public void destruirConstuccion() { this.trampa = new TrampaNull(); }

    public void modificarVelocidad(double modificadorVelocidad) {
        for (Enemigo e : enemigos) {
            e.modificarVelocidadTierra(modificadorVelocidad);
            LOGGER.info("Reducida la velocidad del enemigo " + e.getClass().getSimpleName());
        }
    }

}
