package edu.fiuba.algo3.modelo.JavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.fiuba.algo3.modelo.Juego;

public class Vista extends Application {

    Juego juego;

    public Vista(){
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("AlgoTorres - v.0.0.1");

        TextField texto = new TextField();
        texto.setPromptText("Ingrese su nombre");

        Label etiqueta = new Label();
        etiqueta.setText(texto.getText());

        Button botonEnviar = new Button();
        botonEnviar.setText("Enviar");

        Button botonLimpiar = new Button();
        botonLimpiar.setText("Limpiar cuadro texto");

        HBox contenedorHorizontal = new HBox(new Node[]{botonEnviar, botonLimpiar});
        contenedorHorizontal.setSpacing(10.0);
        VBox contenedorPrincipal = new VBox(new Node[]{texto, contenedorHorizontal, etiqueta});
        contenedorPrincipal.setSpacing(10.0);
        contenedorPrincipal.setPadding(new Insets(20.0));
        BotonLimpiarEventHandler botonLimpiarEventHandler = new BotonLimpiarEventHandler(texto);
        botonLimpiar.setOnAction(botonLimpiarEventHandler);
        BotonEnviarEventHandler botonEnviarEventHandler = new BotonEnviarEventHandler(texto, etiqueta);
        botonEnviar.setOnAction(botonEnviarEventHandler);
        TextoEventHandler textoEventHandler = new TextoEventHandler(botonEnviar);
        texto.setOnKeyPressed(textoEventHandler);
        Scene scene = new Scene(contenedorPrincipal, 300.0, 250.0);
        stage.setScene(scene);
        stage.show();
    }
}
