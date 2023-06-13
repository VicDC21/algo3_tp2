package edu.fiuba.algo3.modelo;

public class Tierra extends Parcela {

    Torre torre = null;

    public Tierra(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    private boolean tieneTorre() {
        return torre != null;
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
        if (tieneTorre()) {
            torre.avanzarTurno(mapa, fila, columna);
        }
    }

    @Override
    public void construirTorre(Torre torre) {
        if (!tieneTorre()) {
            this.torre = torre;
        } else throw new ParcelaNoConstruible();
    }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }

    @Override
    public void destruirConstuccion() {
        this.torre = null;
    }

    @Override
    public void recibirDanio(int danio) {}

    @Override
    public int cantidadDeEnemigos() {
        return 0;
    }

    @Override
    public void reset() {}
}
