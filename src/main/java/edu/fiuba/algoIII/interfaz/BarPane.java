package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.defensas.Torre;
//import edu.fiuba.algoIII.modelo.defensas.TrampaArenosa;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;


public class BarPane extends HBox {

    public BarPane(int tileWidth, int tileHeight, MapaPane mapaPane) {

        ImageView TorreBlancaImagen = new ImageView("torreBlanca.png");
        Torre torreBlanca = new Torre(10,1,3,1, 1);
        TorreBlancaImagen.setFitHeight(tileHeight);
        TorreBlancaImagen.setFitWidth(tileWidth);
        TorreBlancaImagen.setPreserveRatio(true);
        TorreBlancaImagen.setOnMouseClicked(event -> mapaPane.setTorreSeleccionada(torreBlanca));

        ImageView TorrePlateadaImagen = new ImageView("torrePlateada.png");
        Torre torrePlateada = new Torre(20, 2, 5, 2, 2);
        TorrePlateadaImagen.setFitHeight(tileHeight);
        TorrePlateadaImagen.setFitWidth(tileWidth);
        TorrePlateadaImagen.setPreserveRatio(true);
        TorrePlateadaImagen.setOnMouseClicked(event -> mapaPane.setTorreSeleccionada(torrePlateada));

        ImageView TrampaImagen = new ImageView("trampaArenosa.png");
        //TrampaArenosa trampa = new TrampaArenosa();
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
            imageView.setOnMouseEntered(e -> imageView.setEffect(new DropShadow()));
            imageView.setOnMouseExited(e -> imageView.setEffect(null));
        }

        this.getChildren().addAll(TorreBlancaImagen, TorrePlateadaImagen, TrampaImagen);
        this.setSpacing(80);
    }
}
