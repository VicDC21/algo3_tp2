package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;

public class Rocoso extends Parcela {

    public Rocoso(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public void construirTorre(Torre torre) {
        throw new ParcelaNoConstruible();
    }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }

    @Override
    public String obtenerImagen() {
        return "rocoso.jpg";
    }

}
