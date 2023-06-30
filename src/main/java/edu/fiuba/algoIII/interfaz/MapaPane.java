package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.excepciones.CreditoInsuficiente;
import edu.fiuba.algoIII.modelo.Jugador;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import edu.fiuba.algoIII.modelo.parcelas.Rocoso;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.ArrayList;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    private Jugador jugador;
    private ImageView imagenTorre;
    private List<Parcela> listaParcelas = new ArrayList<>();
    private int recHeight;
    private int recWidth;
    private StackPane[][] gridPanes;
    private StackPane[][] gridPanesBasico;

    public MapaPane(List<Parcela> parcelas, Jugador jugador, int tileHeight, int tileWidth) {
        listaParcelas = parcelas;
        recHeight = tileHeight;
        recWidth = tileWidth;
        gridPanes = new StackPane[15][15];
        gridPanesBasico = new StackPane[15][15];

        this.jugador = jugador;
        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = parcelas.get(fila + columna * 15);
                StackPane pane = this.crearVisual(parcela, recHeight,recWidth);
                gridPanes[fila][columna] = pane;
                gridPanesBasico[fila][columna] = pane;
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
                                torreImageView.setFitHeight(recHeight);
                                torreImageView.setFitWidth(recWidth);
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

    public void actualizarVisualEnemigos() {
        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = listaParcelas.get(fila + columna * 15);
                StackPane pane = gridPanes[fila][columna];
                pane.getChildren().removeIf(node -> node instanceof GridPane);

                if (parcela.tieneEnemigos()) {
                    List<Enemigo> enemigosParcela = parcela.devolverEnemigos();

                    GridPane enemyGrid = new GridPane();
                    enemyGrid.setAlignment(Pos.CENTER);

                    for (Enemigo enemigo : enemigosParcela) {
                        ImageView enemyImageView = new ImageView("Tower.png");
                        enemyImageView.getStyleClass().add("enemigo");
                        enemyImageView.setFitHeight(recHeight / 2);
                        enemyImageView.setFitWidth(recWidth / 2);

                        int gridRow = enemigosParcela.indexOf(enemigo) / 2;
                        int gridCol = enemigosParcela.indexOf(enemigo) % 2;
                        enemyGrid.add(enemyImageView, gridCol, gridRow);
                    }

                    pane.getChildren().add(enemyGrid);
                }
            }
        }
    }
}