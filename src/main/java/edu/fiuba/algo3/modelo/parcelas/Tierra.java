package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreNull;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;

public class Tierra extends Parcela {

    Torre torre = new TorreNull();

    public Tierra(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    private boolean tieneTorre() {
        return !(torre.esNull());
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
        this.torre = new TorreNull();
    }

    @Override
    public void recibirDanio(int danio) {}

    @Override
    public int cantidadDeEnemigos() {
        return 0;
    }

}
