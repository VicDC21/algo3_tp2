/*package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RocosoPane extends StackPane {

    public RocosoPane(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(this.widthProperty());
        rect.heightProperty().bind(this.heightProperty());
        rect.setFill(new ImagePattern(new Image("rocoso.jpg")));
        rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1; -fx-stroke-type: inside;");

        this.getChildren().add(rect);
    }
}
*/