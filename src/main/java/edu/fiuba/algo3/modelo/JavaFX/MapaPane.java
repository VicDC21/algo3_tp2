package edu.fiuba.algo3.modelo.JavaFX;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.Rocoso;
import edu.fiuba.algo3.modelo.parcelas.Tierra;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    private Jugador jugador;
    private ImageView imagenTorre;

    public MapaPane(List<Parcela> parcelas, int tileHeight, int tileWidth) {

        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = parcelas.get(fila + columna * 15);
                StackPane pane = this.crearVisual(parcela, tileHeight,tileWidth);

                this.add(pane, fila, columna);
            }
        }
    }

    public void setTorreSeleccionada(Torre torre ,ImageView torreImagen) {
        imagenTorre = torreImagen;
        torreSeleccionada = torre;
    }

    private StackPane crearVisual(Parcela parcela, int tileHeight, int tileWidth){
        Rectangle rect = new Rectangle();
        rect.setWidth(tileWidth);;
        rect.setHeight(tileHeight);;

        if(parcela instanceof Tierra){ 
        rect.setFill(new ImagePattern(new Image("tierra.jpg")));
        }
        else if(parcela instanceof Pasarela){
        rect.setFill(new ImagePattern(new Image("pasarela.jpg")));
        }
        else if(parcela instanceof Rocoso){
        rect.setFill(new ImagePattern(new Image("rocoso.jpg")));
        }

        StackPane pane = new StackPane(rect);

        rect.setOnMouseClicked(event -> {
                    if (torreSeleccionada != null && torreSeleccionada.puedeConstruirseEnParcela(parcela) ){
                        Torre nuevaTorre = new Torre(torreSeleccionada);
                        ImageView torreImageView = new ImageView(imagenTorre.getImage());
                        torreImageView.setFitHeight(tileHeight * 0.8);
                        torreImageView.setFitWidth(tileWidth * 0.7);
                        pane.getChildren().add(torreImageView);
                        parcela.construirTorre(nuevaTorre);
                        torreSeleccionada = null;
                    }
                });
        return pane;
    }
}