package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);

        TextField textField = new TextField("Ingrese su nombre");
        textField.setAlignment(Pos.CENTER);
        textField.setMaxHeight(50);
        textField.setMaxWidth(300);

        Button button = new Button("Ingresar");
        button.minWidth(50);
        button.setOnAction(e -> {
            System.out.println(textField.getText());
            try {
                Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json", textField.getText());
                System.out.println(juego.estadoJuego());
            } catch (InvalidMap ex) {
                throw new RuntimeException(ex);
            }
        });

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        layout.getChildren().add(imageView);
        layout.getChildren().add(textField);
        layout.getChildren().add(button);
        layout.setSpacing(20);


        var scene = new Scene(layout, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}