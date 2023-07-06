package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.parcelas.Parcela;

public class TrampaArenosa extends Trampa {
    int vidaUtil = 3;
    final double modificadorVelocidad = 0.5;

    public TrampaArenosa() {
        super(25);
    }

    @Override
    public void avanzarTurno(){
        this.turnosVigente++;
        if (this.vidaUtil < this.turnosVigente) {
            this.pasarelaConstruida.destruirConstruccion();
            LOGGER.info("Trampa ha sido destruida");
        } else {
            this.aplicarEfecto();
        }
    }

    public boolean puedeConstruirseEnParcela(Parcela parcela) {
        return parcela.puedeContenerTrampa();
    }

    @Override
    public void aplicarEfecto(){
        this.pasarelaConstruida.modificarVelocidad(modificadorVelocidad);
        LOGGER.info("El efecto de la trampa arenosa ha sido activado. Velocidad reducida en " + modificadorVelocidad);
    }

}
