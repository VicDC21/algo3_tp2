package edu.fiuba.algo3.modelo;

public class PasarelaLlegada extends Pasarela {

    private Jugador jugador;

    public PasarelaLlegada(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);

    }

    @Override
    public void avanzarTurno() {
        enemigos.clear();
    }

    @Override
    public boolean esLaDeLLegada() {
        return true;
    }
    @Override
    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) {}

    public void realizarDanioJugador(int danio) {
        jugador.recibirDanio(danio);
    }
}