package edu.fiuba.algo3.modelo.JavaFX;

import edu.fiuba.algo3.modelo.Juego;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import edu.fiuba.algo3.modelo.defensas.Torre;

public class LayoutJuego extends BorderPane {
    Stage stage;
    Juego juego;
    MapaPane mapa;
    BarPane barDefensas;
    Torre torreSeleccionada;

    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    public LayoutJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.stage = stage;
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        int tileWidth = (int) screenBounds.getWidth() / 15;
        int tileHeight = (int) screenBounds.getHeight() / 15;

        mapa = new MapaPane(juego.getTiles(tileWidth, tileHeight));
        barDefensas = new BarPane(tileWidth, tileHeight, mapa);
        barDefensas.setAlignment(Pos.CENTER);
    }
    public void show() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.setCenter(mapa);
        this.setBottom(barDefensas);
        stage.setScene(new Scene(this));
        stage.setResizable(true);
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
