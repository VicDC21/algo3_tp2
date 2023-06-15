package edu.fiuba.algo3.modelo.defensas;

public class TrampaArenosa extends Trampa {

    int costo = 25;
    private static double modificadorVelocidad = 0.5;

    @Override
    public void aplicarEfecto(){
        this.pasarelaConstruida.modificarVelocidad(modificadorVelocidad);
    }
}
