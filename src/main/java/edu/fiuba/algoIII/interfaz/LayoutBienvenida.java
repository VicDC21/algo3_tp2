package edu.fiuba.algoIII.interfaz;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        textField.setPromptText("Ingrese su nombre");
        textField.setFocusTraversable(false);

        Button button = new Button("Empezar partida!");
        button.minWidth(50);
        button.setOnMouseClicked(new BotonEmpezarPartidaEventHandler(stage, textField));

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        this.getChildren().addAll(imageView, textField, button);
        this.setSpacing(20);
    }
}
