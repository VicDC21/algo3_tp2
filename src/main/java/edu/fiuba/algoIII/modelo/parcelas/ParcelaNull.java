package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.Trampa;

public class ParcelaNull extends Parcela{
    public ParcelaNull()
    {
        super(-1, -1, null);
    }

    public void construirTorre(Torre torre) {}

    public void construirTrampa(Trampa trampa) {}

    public boolean puedeContenerTorre() { return false; }

    public String obtenerImagen() { return "parcelaNula.jpg"; }
}
