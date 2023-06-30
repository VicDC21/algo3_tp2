package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.excepciones.CreditoInsuficiente;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

import edu.fiuba.algoIII.modelo.Jugador;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import edu.fiuba.algoIII.modelo.parcelas.Rocoso;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    private Jugador jugador;
    private ImageView imagenTorre;

    public MapaPane(List<Parcela> parcelas, Jugador jugador, int tileHeight, int tileWidth) {
        this.jugador = jugador;
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
        rect.setWidth(tileWidth);
        rect.setHeight(tileHeight);

        if(parcela instanceof Tierra){ 
        rect.setFill(new ImagePattern(new Image("tierra.jpg")));
        rect.setStyle("-fx-stroke: black; -fx-stroke-width: 0.5; -fx-stroke-type: inside;");

        }
        else if(parcela instanceof Pasarela){
        rect.setFill(new ImagePattern(new Image("pasarela.jpg")));
        rect.setStyle("-fx-stroke: green; -fx-stroke-width: 1.5; -fx-stroke-type: inside;");
        }
        else if(parcela instanceof Rocoso){
        rect.setFill(new ImagePattern(new Image("rocoso.jpg")));
        rect.setStyle("-fx-stroke: black; -fx-stroke-width: 0.5; -fx-stroke-type: inside;");
        }

        StackPane pane = new StackPane(rect);

        rect.setOnMouseEntered(e -> rect.setEffect(new DropShadow()));
        rect.setOnMouseExited(e -> rect.setEffect(null));

        rect.setOnMouseClicked(event -> {
                    if (torreSeleccionada != null && torreSeleccionada.puedeConstruirseEnParcela(parcela) )
                        try {
                            if (torreSeleccionada.puedoConstruirConCreditos(jugador.mostrarCreditos())) {
                                Torre nuevaTorre = new Torre(torreSeleccionada);
                                ImageView torreImageView = new ImageView(imagenTorre.getImage());
                                torreImageView.setFitHeight(tileHeight * 0.8);
                                torreImageView.setFitWidth(tileWidth * 0.7);
                                pane.getChildren().add(torreImageView);
                                parcela.construirTorre(nuevaTorre);
                                jugador.gastarCreditos(torreSeleccionada.getCosto());
                            }
                        } catch (CreditoInsuficiente ignored) {}
                    torreSeleccionada = null;
                    }
                );
        return pane;
    }
}