package edu.fiuba.algo3.modelo;

public class Tierra extends Parcela {

    Torre torre;

    public Tierra(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }


    @Override
    public boolean puedeAlojarTorre() {
        return torre == null;
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
        if (torre != null) {
            if (torre.estaOperativa()) {
                torre.atacar(mapa, fila, columna);
            } else {
                torre.avanzarTurno();
            }
        }
    }

    @Override
    public void construir(Torre torre) {
        this.torre = torre;
    }

    @Override
    public void recibirDanio(int danio) {}

    @Override
    public void recibirEnemigo()

    @Override
    public int cantidadDeEnemigos() {
        return 0;
    }
}
