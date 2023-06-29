/*package edu.fiuba.algo3.modelo.JavaFX;

import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TierraPane extends StackPane {
    ContextMenu contextMenu = new TierraContextMenu();
    public TierraPane (int width, int height) {

        this.setOnContextMenuRequested(event -> contextMenu.show(this, event.getScreenX(), event.getScreenY()));

        this.setWidth(width);
        this.setHeight(height);

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(this.widthProperty());
        rect.heightProperty().bind(this.heightProperty());
        rect.setFill(new ImagePattern(new Image("tierra.jpg")));
        rect.setStyle("-fx-stroke: black; -fx-stroke-width: 1; -fx-stroke-type: inside;");

        this.getChildren().add(rect);

    }
}
*/