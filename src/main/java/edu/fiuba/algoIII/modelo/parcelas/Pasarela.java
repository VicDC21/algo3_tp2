package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.defensas.TrampaNull;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;

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

    @Override
    public boolean puedeContenerTrampa() {
        return true;
    }

    public void setPasarelaSiguiente(Pasarela pasarela) { this.pasarelaSiguiente = pasarela; }

    public void causarDanioJugador(int danio) {}

    public Pasarela obtenerPasarelaSiguiente() { return pasarelaSiguiente; }

    public void destruirConstruccion() { this.trampa = new TrampaNull(); }

    @Override
    public boolean puedeContenerTorre() {
        return false;
    }

    @Override
    public String obtenerImagen() {
        return "pasarela.png";
    }

    public void modificarVelocidad(double modificadorVelocidad) {
        for (Enemigo e : enemigos) {
            e.modificarVelocidadTierra(modificadorVelocidad);
            LOGGER.info("Reducida la velocidad del enemigo " + e.getClass().getSimpleName());
        }
    }

    public Trampa getTrampa() {
        return trampa;
    }
}
