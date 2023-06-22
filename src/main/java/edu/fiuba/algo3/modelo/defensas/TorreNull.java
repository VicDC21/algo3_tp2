package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.Mapa;

public class TorreNull extends Torre{

    public TorreNull() {
        super(0, 0, 0, 0);
    }

    @Override
    public void avanzarTurno(Mapa mapa, int fila, int columna) {}

//    @Override
//    public void dibujarse(StackPane pane) {
//    }
}
