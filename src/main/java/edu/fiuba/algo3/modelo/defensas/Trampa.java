package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.parcelas.Pasarela;

// Debe conocer a la parcela parada para poder reducirle la velocidad a los enemigos.
public class Trampa {
    int costo;
    protected Pasarela pasarelaConstruida;
    protected int vidaUtil;
    protected int turnosVigente = 0;

    public void avanzarTurno(int turno){
        this.turnosVigente++;
        if (this.vidaUtil < this.turnosVigente) {
            this.pasarelaConstruida.destruirConstuccion();
        } else {
            this.aplicarEfecto();
        }
    }

    public void aplicarEfecto() {}

    public boolean esNull() {return false;}

    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return (costo <= creditosDisponibles);
    }

    public int getCosto() { return costo; }
}
