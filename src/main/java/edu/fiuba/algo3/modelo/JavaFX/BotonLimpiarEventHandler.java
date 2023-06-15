package edu.fiuba.algo3.modelo.JavaFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class BotonLimpiarEventHandler implements EventHandler<ActionEvent> {
    private TextField textField;

    public BotonLimpiarEventHandler(TextField textField) {
        this.textField = textField;
    }

    public void handle(ActionEvent actionEvent) {
        this.textField.setText("");
        this.textField.requestFocus();
    }
}