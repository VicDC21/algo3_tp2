package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.Jugador;
import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;
public class PasarelaLlegada extends Pasarela {

    private Jugador jugador;

    public PasarelaLlegada(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public boolean puedeContenerTrampa() {
        return false;
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

    @Override
    public String obtenerImagen() {
        return "pasarelaLlegada.png";
    }
}