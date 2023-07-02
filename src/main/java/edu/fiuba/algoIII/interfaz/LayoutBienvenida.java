package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutBienvenida extends VBox {
    Stage stage;
    Button button;
    TextField textField;

    public LayoutBienvenida(Stage stage) {
        super();
        this.stage = stage;
        stage.setTitle("AlgoDefense - v.0.0.1");
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

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                iniciarPartida();
            }
        });

        button.setOnMouseClicked(event -> iniciarPartida());

        this.getChildren().addAll(imageView, textField, button);
        this.setSpacing(20);
    }

    private void iniciarPartida() {
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

