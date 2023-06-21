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

        ImageView imageView1 = new ImageView("tower.png");
        imageView1.setFitHeight(tileHeight);
        imageView1.setFitWidth(tileWidth);
        imageView1.setPreserveRatio(true);


        ImageView imageView2 = new ImageView("tower.png");
        imageView2.setFitHeight(tileHeight);
        imageView2.setFitWidth(tileWidth);
        imageView2.setPreserveRatio(true);

        ImageView imageView3 = new ImageView("tower.png");
        imageView3.setFitHeight(tileHeight);
        imageView3.setFitWidth(tileWidth);
        imageView3.setPreserveRatio(true);

        this.getChildren().addAll(imageView1, imageView2, imageView3);
        this.setSpacing(60);
    }
}
