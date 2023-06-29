package edu.fiuba.algo3;

import edu.fiuba.algo3.JavaFX.LayoutBienvenida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage){
        LayoutBienvenida layout = new LayoutBienvenida(stage);
        stage.setScene(new Scene(layout,640, 480));
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}