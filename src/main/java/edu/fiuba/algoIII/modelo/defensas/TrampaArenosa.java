package edu.fiuba.algoIII.modelo.defensas;

public class TrampaArenosa extends Trampa {

    int costo = 25;
    int vidaUtil = 3;
    final double modificadorVelocidad = 0.5;

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

    @Override
    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return (costo <= creditosDisponibles);
    }

    @Override
    public void aplicarEfecto(){
        this.pasarelaConstruida.modificarVelocidad(modificadorVelocidad);
        LOGGER.info("El efecto de la trampa arenosa ha sido activado. Velocidad reducida en " + modificadorVelocidad);
    }

}
