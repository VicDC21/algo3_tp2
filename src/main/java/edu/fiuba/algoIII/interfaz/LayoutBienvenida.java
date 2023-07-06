package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class LayoutBienvenida extends VBox {
    Stage stage;
    Button button;
    TextField textField;
    MediaPlayer mediaPlayer;

    public LayoutBienvenida(Stage stage) {
        super();
        this.stage = stage;
        stage.getIcons().add(new Image("arania.png"));
        stage.setTitle("AlgoDefense - v.0.1.0");
        this.setAlignment(Pos.CENTER);

        textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        textField.setMaxHeight(50);
        textField.setMaxWidth(300);
        textField.setPromptText("Ingrese su nombre");
        textField.setFocusTraversable(false);

        button = new Button("Empezar partida");
        button.getStyleClass().add("my-button");
        button.minWidth(50);
        button.setDisable(true);

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        URL resourceUrl = getClass().getResource("/musicaInicio.mp3");
        if (resourceUrl != null) {
            String rutaMedia = resourceUrl.toString();
            mediaPlayer = new MediaPlayer(new Media(rutaMedia));
            mediaPlayer.setVolume(0.25);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            mediaPlayer.play();
        }

        textField.textProperty().addListener((observable, oldValue, newValue) -> button.setDisable(newValue.length() < 6));
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !button.isDisabled()) {
                iniciarPartida();
            }
        });

        button.setOnMouseClicked(event -> iniciarPartida());

        VBox.setMargin(button, new Insets(0, 0, 30, 0));
        this.getChildren().addAll(imageView, textField, button);
        this.setSpacing(20);
    }

    private void iniciarPartida() {
        mediaPlayer.stop();
        Juego juego;
        try {
            juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosV2.json", textField.getText());
            LayoutJuego layout = new LayoutJuego(stage, juego);
            layout.getStylesheets().add("/styles.css");
            layout.show();
        } catch (InvalidMap e) {
            throw new RuntimeException(e);
        }
    }
}

