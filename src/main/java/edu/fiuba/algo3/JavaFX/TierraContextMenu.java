package edu.fiuba.algo3.JavaFX;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class TierraContextMenu extends ContextMenu {

    public TierraContextMenu() {
        MenuItem opcion1 = new MenuItem("Construir Torre");
        opcion1.setOnAction(event -> {
            System.out.println("Se coloco Torre "); //falta implementacion real
        });

        MenuItem opcion2 = new MenuItem("Cancelar");
        opcion2.setOnAction(event -> {
            System.out.println("Cancela Procedimiento");
        });
        this.getItems().addAll(opcion1, opcion2);
    }

}
