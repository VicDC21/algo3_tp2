package edu.fiuba.algo3.modelo.JavaFX;

import edu.fiuba.algo3.modelo.Juego;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class LayoutJuego extends FlowPane {

    Stage stage;
    Juego juego;

    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    public LayoutJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.stage = stage;
    }

    public void show() {

        int tileHeight = 64;
        int tileWidth = 84;
        int tilesPerRow = 15;
        int tilesPerColumn = 15;

        List<Pane> tiles = juego.getTiles(tileWidth, tileHeight); // map.getTiles(), for each parcela -> parcela.draw(); que devuelve un Rectangle del color apropiado.

        for (Pane tile : tiles) {
            //        if (tile = Tierra ) {
            ContextMenu contextMenu = new TileContextMenu();
            tile.setOnContextMenuRequested(event -> contextMenu.show(tile, event.getScreenX(), event.getScreenY()));
            //      }

            this.getChildren().add(tile);
        }

        Scene mapScene = new Scene(this, tilesPerRow * tileWidth, tilesPerColumn * tileHeight);
        stage.setScene(mapScene);
        centerOnScreen(stage);
    }


    private void centerOnScreen(Stage stage) {

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth())
                * CENTER_ON_SCREEN_X_FRACTION;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight())
                * CENTER_ON_SCREEN_Y_FRACTION;
        stage.setX(centerX);
        stage.setY(centerY);
    }
}
