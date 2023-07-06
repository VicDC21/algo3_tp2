package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.defensas.Trampa;

public class PasarelaNull extends Pasarela{

    public PasarelaNull() {
        super(-1, -1, null);
    }

    @Override
    public void avanzarTurno() {}

    @Override
    public boolean puedeContenerTrampa() {
        return false;
    }

    @Override
    public void construirTrampa(Trampa trampa) {}

    @Override
    public void setPasarelaSiguiente(Pasarela pasarela) {}

    @Override
    public Pasarela obtenerPasarelaSiguiente() { return this; }

    @Override
    public void destruirConstruccion() {}

    @Override
    public String obtenerImagen() {
        return "parcelaNull.jpg";
    }

    @Override
    public void modificarVelocidad(double modificadorVelocidad) {}
}
