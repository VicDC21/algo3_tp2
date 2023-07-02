package edu.fiuba.algoIII;

import edu.fiuba.algoIII.interfaz.LayoutBienvenida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        LayoutBienvenida layout = new LayoutBienvenida(stage);
        layout.getStylesheets().add("/styles.css");
        stage.setScene(new Scene(layout,640, 600));
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}