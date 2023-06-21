package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class MapaPane extends GridPane {
    public MapaPane(List<Pane> tiles) {

        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Pane tile = tiles.get(fila + columna * 15);
                this.add(tile, fila, columna);
            }

        }
    }
}
