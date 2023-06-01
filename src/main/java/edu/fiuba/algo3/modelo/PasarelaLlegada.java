package edu.fiuba.algo3.modelo;

public class PasarelaLlegada extends Pasarela {

    @Override
    public void avanzarTurno() {
        enemigos.removeAll();
    }

    @Override
    public void recibirEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
        enemigo.causardanio();
    }

    public void realizarDanioJugador(int danio) {
        jugador.recibirDanio(danio);
    }
}
