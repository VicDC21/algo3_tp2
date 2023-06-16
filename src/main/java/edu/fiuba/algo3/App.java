package edu.fiuba.algo3;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;


public class App extends Application {

    private static final Logger logger = LoggerFactory.getLogger(App.class);


    @Override
    public void start(Stage stage) {

        VBox layoutInicial = new VBox();
        layoutInicial.setAlignment(Pos.CENTER);

        TextField textField = new TextField("Ingrese su nombre");
        textField.setAlignment(Pos.CENTER);
        textField.setMaxHeight(50);
        textField.setMaxWidth(300);

        Button button = new Button("Empezar a jugar!");
        button.minWidth(50);
        button.setOnAction(e -> {

            double tileHeight = 32;
            double tileWidth = 42;
            double tilesPerRow = 15;
            double tilesPerColumn = 15;

            Juego juego;
            try {
                juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json", textField.getText());
                System.out.println(juego.estadoJuego());
            } catch (InvalidMap ex) {
                throw new RuntimeException(ex);
            }
            
            FlowPane mapGrid = new FlowPane();

            List<Shape> tiles = juego.getTiles(); // map.getTiles(), for each parcela -> parcela.draw(); que devuelve un Rectangle del color apropiado.

            for (Shape tile : tiles) {
                Rectangle rectangle = ((Rectangle) tile);
                rectangle.setWidth(tileWidth);
                rectangle.setHeight(tileHeight);
                rectangle.setOnMouseClicked(event -> handleTileClick(event));

        //        if (rectangle = Tierra ) { 
                    ContextMenu contextMenu = createContextMenu(rectangle);
                    rectangle.setOnContextMenuRequested(event -> contextMenu.show(rectangle, event.getScreenX(), event.getScreenY()));
          //      }

                mapGrid.getChildren().add(rectangle);
            }

           // HBox layoutJuego = new HBox(mapGrid);
            
            Scene mapScene = new Scene(mapGrid, tilesPerRow * tileWidth, tilesPerColumn * tileHeight);
            stage.setScene(mapScene);
            System.out.println(mapGrid.getChildren());

//            mapScene.widthProperty().addListener(new ChangeListener<Number>() {
//                @Override
//                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
//                    mapGrid.getChildren().forEach(r -> ((Rectangle) r).setWidth((Double) newSceneWidth / tilesPerRow));
//                }
//            });
//            mapScene.heightProperty().addListener(new ChangeListener<Number>() {
//                @Override
//                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
//                    mapGrid.getChildren().forEach(r -> ((Rectangle) r).setHeight((Double) newSceneHeight / tilesPerColumn));
//                }
//            });

        });

        Image image = new Image("algoDefense.png");
        ImageView imageView = new ImageView(image);

        layoutInicial.getChildren().add(imageView);
        layoutInicial.getChildren().add(textField);
        layoutInicial.getChildren().add(button);
        layoutInicial.setSpacing(20);

        var sceneInicial = new Scene(layoutInicial, 640, 480);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(sceneInicial);
        stage.show();
    }

    private ContextMenu createContextMenu(Rectangle rectangle) {
        MenuItem opcion1 = new MenuItem("Construir Torre");
        opcion1.setOnAction(event -> {
            System.out.println("Se coloco Torre "); //falta implementacion real
        });

        MenuItem opcion2 = new MenuItem("Cancelar");
        opcion2.setOnAction(event -> {
            System.out.println("Cancela Procedimiento");
        });

        ContextMenu contextMenu = new ContextMenu(opcion1, opcion2);

        return contextMenu;
    }

    private void handleTileClick(MouseEvent event) {
      
    }

    public static void main(String[] args) {
        launch();
    }
}