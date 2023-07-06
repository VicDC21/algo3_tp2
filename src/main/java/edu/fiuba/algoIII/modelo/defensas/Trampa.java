package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Debe conocer a la parcela parada para poder reducirle la velocidad a los enemigos.
public abstract class Trampa {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Trampa.class.getSimpleName());
    int costo;
    protected Pasarela pasarelaConstruida;
    protected int vidaUtil;
    protected int turnosVigente = 0;

    public void avanzarTurno(){
        this.turnosVigente++;
        if (this.vidaUtil < this.turnosVigente) {
            this.pasarelaConstruida.destruirConstruccion();
        } else {
            this.aplicarEfecto();
        }
    }

    public abstract void aplicarEfecto();

    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return (costo <= creditosDisponibles);
    }

    public int getCosto() { return costo; }

    public void setPasarela(Pasarela pasarela) {
        pasarelaConstruida = pasarela;
    }
}
