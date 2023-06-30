package edu.fiuba.algoIII.interfaz;


//import edu.fiuba.algoIII.modelo.Constructor;
import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.Jugador;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
//import edu.fiuba.algoIII.modelo.defensas.Torre;

public class LayoutJuego extends BorderPane {
    Stage stage;
    Juego juego;
    Jugador jugador;
    //Constructor constructor;
    MapaPane mapaPane;
    BarPane barDefensas;
    //Torre torreSeleccionada;

    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    public LayoutJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.stage = stage;
        this.jugador = juego.getJugador();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        int tileWidth = (int) (screenBounds.getWidth() * 0.9) / 15;
        int tileHeight = (int) (screenBounds.getHeight() * 0.9) / 15;

        mapaPane = new MapaPane(juego.getParcelas(), jugador, tileHeight, tileWidth);
        
        barDefensas = new BarPane(tileWidth, tileHeight, mapaPane);
        barDefensas.setAlignment(Pos.CENTER);

        VBox infoJugador = new VBox();
        infoJugador.setAlignment(Pos.CENTER);

        Label vidaLabel = new Label("Vida: " + juego.mostrarVidaDelJugador());  //fijo , hay que aplicarle Change
        Label creditosLabel = new Label("Créditos: " + juego.mostrarCreditosDelJugador()); // fijo hay que aplicarle Change

        infoJugador.getChildren().addAll(vidaLabel, creditosLabel);

        Button avanzarTurno = new Button("Avanzar Turno");
        
        avanzarTurno.setOnAction(event -> {
            juego.avanzarTurno();
            mapaPane.actualizarVisualEnemigos();
        });

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);

        ColumnConstraints columna1 = new ColumnConstraints();
        columna1.setPercentWidth(33.33);
        ColumnConstraints columna2 = new ColumnConstraints();
        columna2.setPercentWidth(33.33);
        ColumnConstraints columna3 = new ColumnConstraints();
        columna3.setPercentWidth(33.33);

        root.getColumnConstraints().addAll(columna1,columna2,columna3);

        root.add(barDefensas,0,0);
        root.add(infoJugador,1,0);
        root.add(avanzarTurno,2,0);
        GridPane.setHalignment(avanzarTurno, HPos.RIGHT);
        GridPane.setMargin(avanzarTurno, new Insets(0, 10, 0, 0));
        
        this.setCenter(mapaPane);
        this.setBottom(root);

        stage.setScene(new Scene(this));
        stage.setResizable(true);
        centerOnScreen(stage);
    }
    public void show() {
        stage.show();
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
