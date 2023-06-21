package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PasarelaSalidaPane extends StackPane {
    public PasarelaSalidaPane(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(this.widthProperty());
        rect.heightProperty().bind(this.heightProperty());
        rect.setFill(Color.BLACK);

        this.getChildren().add(rect);
    }
}
