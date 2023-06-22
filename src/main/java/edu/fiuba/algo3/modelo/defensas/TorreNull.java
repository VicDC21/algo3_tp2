package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.Mapa;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class TorreNull extends Torre{

    public TorreNull() {
        super(0, 0, 0, 0);
    }

    @Override
    public boolean puedoConstruirConCreditos(int creditosDisponibles) {return false;}

    @Override
    public boolean esNull() {
        return true;
    }
    public void avanzarTurno(Mapa mapa, int fila, int columna) {}

    @Override
    public void atacar(Mapa mapa, int fila, int columna) {}

    @Override
    public boolean estaOperativa() {
        return turnosParaConstruirse <= 0;
    } {}

    @Override
    public void dibujarse(Pane pane) {}
//    @Override
//    public void dibujarse(StackPane pane) {
//    }
}
