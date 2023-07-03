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
import javafx.stage.Stage;

public class LayoutBienvenida extends VBox {
    Stage stage;
    Button button;
    TextField textField;

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
        validarNombreUsuario();

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

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

    public void validarNombreUsuario(){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 6) {
                button.setDisable(false);
            } else {
                button.setDisable(true);
            }
        });
    }
}

