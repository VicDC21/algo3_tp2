package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Defensa {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Trampa.class.getSimpleName());
    protected int costo;

    public Defensa(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return costo;
    }

    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return (this.costo <= creditosDisponibles);
    }

    public abstract boolean puedeConstruirseEnParcela(Parcela parcela);
}
