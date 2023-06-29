package edu.fiuba.algo3.JavaFX;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;


// Cambiar para que muestre la vida y los creditos.

public class BarPane extends HBox {

    public BarPane(int tileWidth, int tileHeight, MapaPane mapaPane) {

        ImageView TorreBlancaImagen = new ImageView("torreBlanca.png");
        Torre torreBlanca = new Torre(10,1,3,1);
        TorreBlancaImagen.setFitHeight(tileHeight);
        TorreBlancaImagen.setFitWidth(tileWidth);
        TorreBlancaImagen.setPreserveRatio(true);
        TorreBlancaImagen.setOnMouseClicked(event -> {
            mapaPane.setTorreSeleccionada(torreBlanca, TorreBlancaImagen);
        });

        ImageView TorrePlateadaImagen = new ImageView("torrePlateada.png");
        Torre torrePlateada = new Torre(20, 2, 5, 2);
        TorrePlateadaImagen.setFitHeight(tileHeight);
        TorrePlateadaImagen.setFitWidth(tileWidth);
        TorrePlateadaImagen.setPreserveRatio(true);
        TorrePlateadaImagen.setOnMouseClicked(event -> {
            mapaPane.setTorreSeleccionada(torrePlateada, TorrePlateadaImagen);
        });

        ImageView TrampaImagen = new ImageView("trampa.png");
        TrampaArenosa trampa = new TrampaArenosa();
        TrampaImagen.setFitHeight(tileHeight);
        TrampaImagen.setFitWidth(tileWidth);
        TrampaImagen.setPreserveRatio(true);
      //  TrampaImagen.setOnMouseClicked(event -> {
      //      mapaPane.setTorreSeleccionada(trampa, TrampaImagen);
     //   });

        List<ImageView> lista = new ArrayList<>();
        lista.add(TorreBlancaImagen);
        lista.add(TorrePlateadaImagen);
        lista.add(TrampaImagen);

        for (ImageView imageView: lista) {
            imageView.setOnMouseEntered(e -> {
                imageView.setEffect(new DropShadow());
            });

            imageView.setOnMouseExited(e -> {
                imageView.setEffect(null);
            });
        }

        this.getChildren().addAll(TorreBlancaImagen, TorrePlateadaImagen, TrampaImagen);
        this.setSpacing(80);
    }
}
