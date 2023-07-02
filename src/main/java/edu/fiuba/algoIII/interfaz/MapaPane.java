package edu.fiuba.algoIII.interfaz;

import edu.fiuba.algoIII.modelo.defensas.TorreNull;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.excepciones.CreditoInsuficiente;
import edu.fiuba.algoIII.modelo.Jugador;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class MapaPane extends GridPane {
    private Torre torreSeleccionada;
    final private Jugador jugador;
    private ImageView imagenTorre;
    final private List<Parcela> listaParcelas;
    final private int recHeight;
    final private int recWidth;
    final private StackPane[][] gridPanes;
    final StackPane[][] gridPanesBasico;

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
                StackPane pane = this.crearVisual(parcela, recHeight, recWidth);
                gridPanes[fila][columna] = pane;
                gridPanesBasico[fila][columna] = pane;
                this.add(pane, fila, columna);
            }
        }
    }

    public void setTorreSeleccionada(Torre torre, ImageView torreImagen) {
        imagenTorre = torreImagen;
        torreSeleccionada = torre;
    }

    public void sombrearElementoEnActivo(Node node) {
        node.setOnMouseEntered(e -> node.setEffect(new DropShadow()));
        node.setOnMouseExited(e -> node.setEffect(null));
    }
    public void llenarTiles(Rectangle rect, Parcela parcela) {
        rect.setFill(new ImagePattern(new Image(parcela.obtenerImagen())));
        rect.setStyle("-fx-stroke: black; -fx-stroke-width: 0.5; -fx-stroke-type: inside;");
    }

    public void posicionarTorre(Parcela parcela, StackPane pane) {
        if (torreSeleccionada != null && torreSeleccionada.puedeConstruirseEnParcela(parcela))
            try {
                if (torreSeleccionada.puedoConstruirConCreditos(jugador.mostrarCreditos())) {
                    Torre nuevaTorre = new Torre(torreSeleccionada);
                    ImageView torreImageView = new ImageView("tower.png");
                    torreImageView.setFitHeight(recHeight);
                    torreImageView.setFitWidth(recWidth);
                    pane.getChildren().add(torreImageView);
                    parcela.construirTorre(nuevaTorre);
                    jugador.gastarCreditos(torreSeleccionada.getCosto());
                }
            } catch (CreditoInsuficiente ignored) {
            }
        torreSeleccionada = null;
    }
    private StackPane crearVisual(Parcela parcela, int tileHeight, int tileWidth){
        Rectangle rect = new Rectangle();

        rect.setWidth(tileWidth);
        rect.setHeight(tileHeight);
        llenarTiles(rect, parcela);
        sombrearElementoEnActivo(rect);

        StackPane pane = new StackPane(rect);
        rect.setOnMouseClicked(event -> posicionarTorre(parcela, pane));
        return pane;
    }

    public void actualizarVisualEnemigos() {
        for (int fila = 0; fila < 15; fila++) {
            for (int columna = 0; columna < 15; columna++) {
                Parcela parcela = listaParcelas.get(fila + columna * 15);
                StackPane pane = gridPanes[fila][columna];
                pane.getChildren().removeIf(node -> node instanceof GridPane);
                sombrearElementoEnActivo(pane);
                if (parcela.tieneEnemigos()) {
                    List<Enemigo> enemigosParcela = parcela.devolverEnemigos();

                    GridPane enemyGrid = new GridPane();
                    enemyGrid.setAlignment(Pos.CENTER);

                    for (Enemigo enemigo : enemigosParcela) {
                        ImageView enemyImageView = new ImageView(enemigo.obtenerImagen());
                        enemyImageView.getStyleClass().add("enemigo");
                        enemyImageView.setFitHeight(recHeight / 2.0);
                        enemyImageView.setFitWidth(recWidth / 2.0);
                        sombrearElementoEnActivo(enemyImageView);
                        int gridRow = enemigosParcela.indexOf(enemigo) / 2;
                        int gridCol = enemigosParcela.indexOf(enemigo) % 2;
                        enemyGrid.add(enemyImageView, gridCol, gridRow);
                    }
                    pane.getChildren().add(enemyGrid);
                }
                if (parcela instanceof Tierra) {
                    actualizarVisualTorres((Tierra) parcela, pane);
                }
            }
        }
    }

    public void actualizarVisualTorres(Tierra tierra, StackPane pane) {
        pane.getChildren().removeIf(node -> node instanceof ImageView);
        if (tierra.getTorre() instanceof TorreNull)
            return;
        ImageView torre;
        if (!tierra.getTorre().estaOperativa()) {
            torre = new ImageView("tower.png");
        } else {
            if (tierra.getTorre().getTipo() == 1) {
                torre = new ImageView( "torreBlanca.png");
            } else {
                torre = new ImageView("torrePlateada.png");
            }
        }
        torre.setFitHeight(recHeight);
        torre.setFitWidth(recWidth);
        pane.getChildren().add(torre);
    }
}