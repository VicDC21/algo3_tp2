package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.Mapa;

public class TorreNull extends Torre {

    public TorreNull() {
        super(0, 0, 0, 0, 0);
    }

    @Override
    public boolean puedoConstruirConCreditos(int creditosDisponibles) {return false;}
    @Override
    public void avanzarTurno(Mapa mapa, int fila, int columna) {}
    @Override
    public boolean estaOperativa() {
        return false;
    }
}
