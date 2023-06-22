package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.SuscriptorTurno;

public class TrampaArenosa extends Trampa {

    int costo = 25;
    int vidaUtil = 3;
    private static double modificadorVelocidad = 0.5;

    @Override
    public void avanzarTurno(){
        this.turnosVigente++;
        if (this.vidaUtil < this.turnosVigente) {
            this.pasarelaConstruida.destruirConstuccion();
        } else {
            this.aplicarEfecto();
        }
    }

    @Override
    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return (costo <= creditosDisponibles);
    }

    @Override
    public void aplicarEfecto(){
        this.pasarelaConstruida.modificarVelocidad(modificadorVelocidad);
    }

}
