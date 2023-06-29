package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.defensas.TrampaNull;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    Trampa trampa = new TrampaNull();

    public Pasarela(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    public Pasarela(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa);
        this.pasarelaSiguiente = pasarelaSiguiente;
    }

    @Override
    public void avanzarTurno() {
        if (!tieneEnemigos()) {
            return;
        }
        trampa.avanzarTurno();
        enemigos.forEach(Enemigo::avanzar);
    }

    @Override
    public void construirTorre(Torre torre) { throw new ParcelaNoConstruible(); }

    @Override
    public void construirTrampa(Trampa trampa) {
        this.trampa = trampa;
        trampa.setPasarela(this);
    }

    public void setPasarelaSiguiente(Pasarela pasarela) { this.pasarelaSiguiente = pasarela; }

    public void causarDanioJugador(int danio) {}

    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) { pasarelaSiguiente.recibirEnemigo(enemigo); }

    public Pasarela obtenerPasarelaSiguiente() { return pasarelaSiguiente; }

    public void destruirConstruccion() { this.trampa = new TrampaNull(); }

    public void modificarVelocidad(double modificadorVelocidad) {
        for (Enemigo e : enemigos) {
            e.modificarVelocidadTierra(modificadorVelocidad);
            LOGGER.info("Reducida la velocidad del enemigo " + e.getClass().getSimpleName());
        }
    }

}
