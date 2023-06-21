package edu.fiuba.algo3.modelo.defensas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class TorreNull extends Torre{

    public TorreNull() {
        super(0, 0, 0, 0);
    }

    public boolean esNull() {
        return true;
    }

//    @Override
//    public void dibujarse(StackPane pane) {
//    }
}
