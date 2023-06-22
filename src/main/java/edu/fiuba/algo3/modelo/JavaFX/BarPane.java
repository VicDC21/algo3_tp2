package edu.fiuba.algo3.modelo.JavaFX;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

// Cambiar para que muestre la vida y los creditos.

public class BarPane extends HBox {

    public BarPane(int tileWidth, int tileHeight) {
        this.setBackground(new Background(new BackgroundFill(Color.BLUE,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        ImageView TorreBlanca = new ImageView("tower.png");
        TorreBlanca.setFitHeight(tileHeight);
        TorreBlanca.setFitWidth(tileWidth);
        TorreBlanca.setPreserveRatio(true);
        TorreBlanca.setOnMouseClicked(new DefensaElegidaEventHandler(TorreBlanca));

        ImageView TorrePlateada = new ImageView("tower.png");
        TorrePlateada.setFitHeight(tileHeight);
        TorrePlateada.setFitWidth(tileWidth);
        TorrePlateada.setPreserveRatio(true);
        TorrePlateada.setOnMouseClicked(new DefensaElegidaEventHandler(TorrePlateada));

        ImageView Trampa = new ImageView("tower.png");
        Trampa.setFitHeight(tileHeight);
        Trampa.setFitWidth(tileWidth);
        Trampa.setPreserveRatio(true);
        Trampa.setOnMouseClicked(new DefensaElegidaEventHandler(Trampa));

        this.getChildren().addAll(TorreBlanca, TorrePlateada, Trampa);
        this.setSpacing(60);
    }
}
