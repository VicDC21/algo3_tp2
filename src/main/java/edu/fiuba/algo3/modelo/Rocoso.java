package edu.fiuba.algo3.modelo;

public class Rocoso extends Parcela {

    public Rocoso(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
    }

    @Override
    public void construirTorre(Torre torre) {
        throw new ParcelaNoConstruible();
    }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }

    @Override
    public void recibirDanio(int danio) {

    }

    @Override
    public int cantidadDeEnemigos() {
        return 0;
    }

    @Override
    public void reset() {}
}
