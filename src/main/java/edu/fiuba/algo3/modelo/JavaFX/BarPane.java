package edu.fiuba.algo3.modelo.JavaFX;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


// Cambiar para que muestre la vida y los creditos.

public class BarPane extends HBox {

    public BarPane(int tileWidth, int tileHeight, MapaPane mapaPane) {

        ImageView TorreBlancaImagen = new ImageView("tower.png");
        Torre torreBlanca = new Torre(10,1,3,1);
        TorreBlancaImagen.setFitHeight(tileHeight);
        TorreBlancaImagen.setFitWidth(tileWidth);
        TorreBlancaImagen.setPreserveRatio(true);
        TorreBlancaImagen.setOnMouseClicked(event -> {
            mapaPane.setTorreSeleccionada(torreBlanca, TorreBlancaImagen);
        });

        ImageView TorrePlateadaImagen = new ImageView("tower.png");
        Torre torrePlateada = new Torre(20, 2, 5, 2);
        TorrePlateadaImagen.setFitHeight(tileHeight);
        TorrePlateadaImagen.setFitWidth(tileWidth);
        TorrePlateadaImagen.setPreserveRatio(true);
        TorrePlateadaImagen.setOnMouseClicked(event -> {
            mapaPane.setTorreSeleccionada(torrePlateada, TorrePlateadaImagen);
        });

        ImageView TrampaImagen = new ImageView("tower.png");
        TrampaArenosa trampa = new TrampaArenosa();
        TrampaImagen.setFitHeight(tileHeight);
        TrampaImagen.setFitWidth(tileWidth);
        TrampaImagen.setPreserveRatio(true);
      //  TrampaImagen.setOnMouseClicked(event -> {
      //      mapaPane.setTorreSeleccionada(trampa, TrampaImagen);
     //   });

        this.getChildren().addAll(TorreBlancaImagen, TorrePlateadaImagen, TrampaImagen);
        this.setSpacing(80);
    }
}
