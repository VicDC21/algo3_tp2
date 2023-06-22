package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.Mapa;
import javafx.scene.layout.Pane;

public class TorreNull extends Torre{

    public TorreNull() {
        super(0, 0, 0, 0);
    }

    @Override
    public boolean puedoConstruirConCreditos(int creditosDisponibles) {return false;}
    @Override
    public void avanzarTurno(Mapa mapa, int fila, int columna) {}

    @Override
    public void dibujarse(Pane pane) {}
//    @Override
//    public void dibujarse(StackPane pane) {
//    }
}
