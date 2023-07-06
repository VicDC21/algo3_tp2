package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.parcelas.Parcela;

public class TrampaNull extends Trampa{

    public TrampaNull() {
        super(0);
    }

    @Override
    public void avanzarTurno() {}

    @Override
    public void aplicarEfecto() {}

    public boolean puedeConstruirseEnParcela(Parcela parcela){
        return false;
    }

}
