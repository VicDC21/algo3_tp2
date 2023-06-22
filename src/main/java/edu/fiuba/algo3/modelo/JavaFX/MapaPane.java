package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    private ImageView imagenTorre;

    public MapaPane(List<Pane> tiles) {

        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Pane tile = tiles.get(fila + columna * 15);
                tile.setOnMouseClicked(event -> {
                    if (torreSeleccionada != null) {
                        Torre nuevaTorre = new Torre(torreSeleccionada);
                        ImageView torreImageView = new ImageView(imagenTorre.getImage());
                        tile.getChildren().add(torreImageView);
                   //   ((Parcela) tile).construirTorre(nuevaTorre);   hay que solucionar como construirlo
                        torreSeleccionada = null;
                    }
                });

                this.add(tile, fila, columna);
            }

        }
    }

    public void setTorreSeleccionada(Torre torre ,ImageView torreImagen) {
        imagenTorre = torreImagen;
        torreSeleccionada = torre;
    }
}