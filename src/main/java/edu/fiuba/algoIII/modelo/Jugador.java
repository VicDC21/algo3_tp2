package edu.fiuba.algoIII.modelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Jugador {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jugador.class.getSimpleName());
    int vida;
    int creditos;
    IntegerProperty vidaProperty;
    IntegerProperty creditosProperty;
    String nombre;
    Constructor constructor;

    public Jugador(String nombre, int cantidadDeVida, int cantidadDeCreditos, Constructor constructor){
        this.nombre = nombre;
        this.creditos = cantidadDeCreditos;
        creditosProperty = new SimpleIntegerProperty(creditos);
        this.vida = cantidadDeVida;
        vidaProperty = new SimpleIntegerProperty(vida);
        this.constructor = constructor;
        constructor.setJugador(this);
    }

    public int mostrarVida() {
        return this.vida;
    }

    public int mostrarCreditos() {
        return this.creditos;
    }

    public void construirTorre(String nombreTorre, int fila, int columna) {
        constructor.construirTorre(nombreTorre, this.creditos, fila, columna);
    }

    public void construirTrampa(/*String nombreTrampa,*/ int fila, int columna) {
        constructor.construirTrampa(/*nombreTrampa,*/ this.creditos, fila, columna);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirCreditos(int creditos) {
        this.creditos += creditos;
        creditosProperty.set(this.creditos);
        LOGGER.info("Jugador recibe " + creditos + " créditos");
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
        vidaProperty.set(vida);
        LOGGER.info("Jugador recibe " + danio + " de danio");
    }

    public void gastarCreditos(int costo) {
        creditos -= costo;
        creditosProperty.set(creditos);
        LOGGER.info("Jugador ahora tiene " + creditos + " créditos");
    }

    public IntegerProperty vidaProperty() {
        return vidaProperty;
    }

    public IntegerProperty creditosProperty() {
        return creditosProperty;
    }
}