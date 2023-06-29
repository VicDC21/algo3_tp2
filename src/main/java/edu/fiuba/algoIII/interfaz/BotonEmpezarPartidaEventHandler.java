package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.Juego;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BotonEmpezarPartidaEventHandler implements EventHandler<MouseEvent> {

    Stage stage;
    TextField textField;
    public BotonEmpezarPartidaEventHandler(Stage stage, TextField textField) {
        this.stage = stage;
        this.textField = textField;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Juego juego;
        try {
            juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json", textField.getText());
            LayoutJuego layout = new LayoutJuego(stage, juego);   
            layout.show();       
        } catch (InvalidMap e) {
            throw new RuntimeException(e);
        }
    }
}
