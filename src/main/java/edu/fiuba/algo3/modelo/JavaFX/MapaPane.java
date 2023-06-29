package edu.fiuba.algo3.modelo.JavaFX;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.Rocoso;
import edu.fiuba.algo3.modelo.parcelas.Tierra;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    private Jugador jugador;
    private ImageView imagenTorre;
    private List<Parcela> listaParcelas = new ArrayList<>();
    private List<Pane> listaPane = new ArrayList<>();
    private int recHeight;
    private int recWidth;
    private StackPane[][] gridPanes;


    public MapaPane(List<Parcela> parcelas, int tileHeight, int tileWidth) {
        listaParcelas = parcelas;
        recHeight = tileHeight;
        recWidth = tileWidth;

        gridPanes = new StackPane[15][15];

        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = parcelas.get(fila + columna * 15);
                StackPane pane = this.crearVisual(parcela, recHeight,recWidth);
                gridPanes[fila][columna] = pane;  
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
                        torreImageView.setFitHeight(recHeight);
                        torreImageView.setFitWidth(recWidth);
                        pane.getChildren().add(torreImageView);
                        parcela.construirTorre(nuevaTorre);
                        torreSeleccionada = null;
                    }
                });
        
        return pane;
    }

    public void actuaizarVisualEnemigos() {
        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = listaParcelas.get(fila + columna * 15);
                
                if(parcela.tieneEnemigos()) {
                    ImageView enemyImageView = new ImageView(new Image("Tower.png"));
                    StackPane pane = gridPanes[fila][columna];
                    enemyImageView.setFitHeight(recHeight/2);
                    enemyImageView.setFitWidth(recWidth/2);
                    pane.getChildren().addAll(enemyImageView); 
                }
                else if(!parcela.tieneEnemigos()){
                    StackPane pane = gridPanes[fila][columna];
                }
           }
        }
    }
}

