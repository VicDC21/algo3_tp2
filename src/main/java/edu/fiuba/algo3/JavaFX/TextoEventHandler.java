package edu.fiuba.algo3.JavaFX;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TextoEventHandler implements EventHandler<KeyEvent> {
    private Button botonEnviar;

    public TextoEventHandler(Button botonEnviar) {
        this.botonEnviar = botonEnviar;
    }

    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Event.fireEvent(this.botonEnviar, new ActionEvent());
        }

    }
}
