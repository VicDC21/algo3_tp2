package edu.fiuba.algoIII.interfaz;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LayoutFinal extends VBox {
    Stage stage;

    public LayoutFinal(Stage stage, String nombreJugador, boolean esVictorioso) {
        super();
        this.stage = stage;
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/styles.css");

        ImageView imageView;
        Label frase;

        if (esVictorioso) {
            Image image = new Image("pantallaVictoria.jpg");
            imageView = new ImageView(image);
            frase = new Label("Felicidades, " + nombreJugador);
        } else {
            Image image = new Image("pantallaDerrota.jpg");
            imageView = new ImageView(image);
            frase = new Label("Has perdido, " + nombreJugador);
        }

        this.getChildren().addAll(imageView, frase);
    }
}


