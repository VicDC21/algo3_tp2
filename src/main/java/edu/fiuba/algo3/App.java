package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.JavaFX.LayoutBienvenida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        LayoutBienvenida layout = new LayoutBienvenida(stage);
        Scene sceneBienvenida = new Scene(layout,640, 480);
        stage.setScene(sceneBienvenida);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}