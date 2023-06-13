package edu.fiuba.algo3.modelo;

// Debe conocer a la parcela parada para poder reducirle la velocidad a los enemigos.
public abstract class Trampa {
    private Pasarela pasarelaConstruida;
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

    public abstract void aplicarEfecto();
}
