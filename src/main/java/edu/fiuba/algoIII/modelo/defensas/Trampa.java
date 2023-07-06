package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.parcelas.Pasarela;

// Debe conocer a la parcela parada para poder reducirle la velocidad a los enemigos.
public abstract class Trampa extends Defensa{
    protected Pasarela pasarelaConstruida;
    protected int vidaUtil;
    protected int turnosVigente = 0;

    public Trampa(int costo) {
        super(costo);
    }

    public void avanzarTurno(){
        this.turnosVigente++;
        if (this.vidaUtil < this.turnosVigente) {
            this.pasarelaConstruida.destruirConstruccion();
        } else {
            this.aplicarEfecto();
        }
    }

    public abstract void aplicarEfecto();

    public void setPasarela(Pasarela pasarela) {
        pasarelaConstruida = pasarela;
    }
}
