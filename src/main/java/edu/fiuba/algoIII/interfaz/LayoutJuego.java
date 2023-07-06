package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.Jugador;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class LayoutJuego extends BorderPane {
    Stage stage;
    Juego juego;
    String nombreJugador;
    Jugador jugador;
    MapaPane mapaPane;
    BarPane barDefensas;
    boolean pantallaDerrotaMostrada;
    boolean pantallaVictoriaMostrada;
    MediaPlayer mediaPlayer;
    MediaPlayer backgroundMusic;

    private static final float CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
    private static final float CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
    public LayoutJuego(Stage stage, Juego juego, String nombreJugador) {
        this.juego = juego;
        this.stage = stage;
        this.nombreJugador = nombreJugador;

        this.jugador = juego.getJugador();
        pantallaDerrotaMostrada = false;
        pantallaVictoriaMostrada = false;

        reproducirMusicaConRepeticion("/musicaFondo.mp3");
        stage.getIcons().add(new Image("arania.png"));

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        
        int tileWidth = (int) (screenBounds.getWidth() * 0.9) / 15;
        int tileHeight = (int) (screenBounds.getHeight() * 0.9) / 15;

        mapaPane = new MapaPane(juego.getParcelas(), jugador, tileHeight, tileWidth);
        
        barDefensas = new BarPane(tileWidth, tileHeight, mapaPane);
        barDefensas.setAlignment(Pos.CENTER);
        barDefensas.setBackground(new Background(new BackgroundFill(Color.GRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        VBox infoJugador = new VBox();
        infoJugador.setAlignment(Pos.CENTER);
        infoJugador.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

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
            reproducirSonido("/click.mp3");
            int vidaAnterior = juego.mostrarVidaDelJugador();
            int cantidadTorresAnterior = juego.cantidadDeTorres();
            juego.avanzarTurno();
            mapaPane.actualizarVisualEnemigos();
            if (vidaAnterior > juego.mostrarVidaDelJugador()) {
                reproducirSonido("/jugadorDaniado.mp3");
            }
            if (cantidadTorresAnterior > juego.cantidadDeTorres()) {
                reproducirSonido("/torreDestruida.mp3");
            }
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

        juego.estadoProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.intValue() < 0) {
                backgroundMusic.stop();
                mostrarPantallaDerrota();
            }
            else if (newValue.intValue() > 0) {
                backgroundMusic.stop();
                mostrarPantallaVictoria();
            }
         });
    }

    private void reproducirSonido(String path){
        URL resourceUrl = getClass().getResource(path);
        if (resourceUrl != null) {
                String rutaMedia = resourceUrl.toString();
                Media media = new Media(rutaMedia);
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(0.25);
                mediaPlayer.play();
        }
    }

    private void reproducirMusicaConRepeticion(String path){
        URL resourceUrl = getClass().getResource(path);
        if (resourceUrl != null) {
            String rutaMedia = resourceUrl.toString();
            backgroundMusic = new MediaPlayer(new Media(rutaMedia));
            backgroundMusic.setVolume(0.25);
            backgroundMusic.setOnEndOfMedia(() -> backgroundMusic.seek(Duration.ZERO));
            backgroundMusic.play();
        }
    }

    private void mostrarPantallaVictoria() {
        Scene sceneVictoria = new Scene(new Label("¡Victoria!"));
        stage.setScene(sceneVictoria);
        stage.show();
        reproducirSonido("/victoria.mp3");
    }

    private void mostrarPantallaDerrota() {
        Scene sceneDerrota = new Scene(new Label("¡Derrota!"));
        stage.setScene(sceneDerrota);
        stage.show();
        reproducirSonido("/derrota.mp3");
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
