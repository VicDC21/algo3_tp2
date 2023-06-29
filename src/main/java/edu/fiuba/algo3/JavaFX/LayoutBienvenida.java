package edu.fiuba.algo3.JavaFX;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

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

    public String leerNombre() {
        Scanner in = new Scanner(System.in);
        String name = "";
        while (name.length() < 6) {
            System.out.println("Ingrese un nombre de al menos 6 caracteres: ");
            name = in.nextLine();
        }
        in.close();
        return name;
    }

}
