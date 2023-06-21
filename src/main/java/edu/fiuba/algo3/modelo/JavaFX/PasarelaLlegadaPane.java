package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PasarelaLlegadaPane extends StackPane {
    public PasarelaLlegadaPane(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(this.widthProperty());
        rect.heightProperty().bind(this.heightProperty());
        rect.setFill(Color.GOLD);

        this.getChildren().add(rect);
    }
}
