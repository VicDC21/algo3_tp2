package edu.fiuba.algo3.modelo.JavaFX;

import edu.fiuba.algo3.modelo.Juego;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class LayoutJuego extends BorderPane {
    Stage stage;
    Juego juego;
    MapaPane mapa;
    BarPane bar;
    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    private static final int tileHeight = 60;
    private static final int tileWidth = 80;
    public LayoutJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.stage = stage;

        mapa = new MapaPane(juego.getTiles(tileWidth, tileHeight));
        bar = new BarPane(tileWidth, tileHeight);
        bar.setAlignment(Pos.CENTER);
    }
    public void show() {
        this.setCenter(mapa);
        this.setBottom(bar);
        stage.setScene(new Scene(this));
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
