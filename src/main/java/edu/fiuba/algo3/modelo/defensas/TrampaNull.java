package edu.fiuba.algo3.modelo.defensas;

public class TrampaNull extends Trampa{

    @Override
    public void avanzarTurno(int turno) {}

    @Override
    public void aplicarEfecto() {}

    @Override
    public boolean esNull() {return true;}
}
