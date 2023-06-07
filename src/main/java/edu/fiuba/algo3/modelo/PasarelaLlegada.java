package edu.fiuba.algo3.modelo;

public class PasarelaLlegada extends Pasarela {

    public PasarelaLlegada(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);

    }
    public PasarelaLlegada(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa, pasarelaSiguiente);
    }

    @Override
    public void avanzarTurno() {
        enemigos.clear();
    }

    @Override
    public void recibirEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
//        enemigo.causardanio();
    }

    public void realizarDanioJugador(int danio) {
//        jugador.recibirDanio(danio);
    }
}
