package edu.fiuba.algo3.modelo.JavaFX;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutBienvenida extends VBox {

    Stage stage;

    public LayoutBienvenida(Stage stage) {
        super();
        this.stage = stage;
        stage.setTitle("AlgoDefense - v.0.0.1");
        this.setAlignment(Pos.CENTER);

        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);
        textField.setMaxHeight(50);
        textField.setMaxWidth(300);

        String backgroundText = "Ingrese su nombre";
        textField.setText(backgroundText);

        Button button = new Button("Empezar partida!");
        button.minWidth(50);
        button.setOnMouseClicked(new BotonEmpezarPartidaEventHandler(stage, textField));

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        textField.setOnMouseClicked(event -> {
            if (textField.getText().equals(backgroundText)) {
                textField.clear();
            }
        });

        textField.setOnKeyTyped(event -> {
            if (textField.getText().isEmpty()) {
                textField.setText(backgroundText);
            }
        });

        this.getChildren().addAll(imageView, textField, button);
        this.setSpacing(20);
    }
}
