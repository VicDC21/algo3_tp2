package edu.fiuba.algoIII.interfaz;


//import edu.fiuba.algoIII.modelo.Constructor;
import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.Jugador;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
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
    boolean pantallaDerrotaMostrada;
    boolean pantallaVictoriaMostrada;

    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    public LayoutJuego(Stage stage, Juego juego) {
        this.juego = juego;
        this.stage = stage;

        this.jugador = juego.getJugador();
        pantallaDerrotaMostrada = false;
        pantallaVictoriaMostrada = false;

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        int tileWidth = (int) (screenBounds.getWidth() * 0.9) / 15;
        int tileHeight = (int) (screenBounds.getHeight() * 0.9) / 15;

        mapaPane = new MapaPane(juego.getParcelas(), jugador, tileHeight, tileWidth);
        
        barDefensas = new BarPane(tileWidth, tileHeight, mapaPane);
        barDefensas.setAlignment(Pos.CENTER);

        VBox infoJugador = new VBox();
        infoJugador.setAlignment(Pos.CENTER);

        Label vidaLabel = new Label("Vida: " + juego.mostrarVidaDelJugador());
        Label creditosLabel = new Label("Créditos: " + juego.mostrarCreditosDelJugador());
        Label turnoLabel = new Label("Turno: " + juego.mostrarTurno());

        vidaLabel.textProperty().bind(Bindings.createStringBinding(() ->
        "Vida: " + juego.vidaDelJugadorProperty().get(), juego.vidaDelJugadorProperty()));
        creditosLabel.textProperty().bind(Bindings.createStringBinding(() ->
        "Créditos: " + juego.creditosDelJugadorProperty().get(), juego.creditosDelJugadorProperty()));
        turnoLabel.textProperty().bind(Bindings.createStringBinding(() ->
        "Turno: " + juego.turnoProperty().get(), juego.turnoProperty()));

        infoJugador.getChildren().addAll(vidaLabel, creditosLabel, turnoLabel);

        Button avanzarTurno = new Button("Avanzar Turno");
        avanzarTurno.getStyleClass().add("my-button");

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

        jugador.vidaProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.intValue() <= 0 && !pantallaDerrotaMostrada) {
                
                this.mostrarPantallaDerrota();
                pantallaDerrotaMostrada = true;
                
            }
         });

        /*     if (!juego.tieneEnemigos() && !pantallaVictoriaMostrada) {
        /*         this.mostrarPantallaVictoria();
        /* pantallaVictoriaMostrada = true;
        */    }
    private void mostrarPantallaVictoria() {
        Stage stageDerrota = new Stage();
        Scene sceneDerrota = new Scene(new Label("Victoria!"));

        stageDerrota.setScene(sceneDerrota);
        stageDerrota.show();
    }
    private void mostrarPantallaDerrota() {
        
        Stage stageDerrota = new Stage();
        Scene sceneDerrota = new Scene(new Label("¡Derrota!"));

        stageDerrota.setScene(sceneDerrota);
        stageDerrota.show();
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
