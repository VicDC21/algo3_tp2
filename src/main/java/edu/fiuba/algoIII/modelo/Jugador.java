package edu.fiuba.algoIII.modelo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jugador {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jugador.class.getSimpleName());
    int vida;
    int creditos;
    String nombre;
    Constructor constructor;

    public Jugador(String nombre, int cantidadDeVida, int cantidadDeCreditos, Constructor constructor){
        this.nombre = nombre;
        this.creditos = cantidadDeCreditos;
        this.vida = cantidadDeVida;
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

    public void construirTrampa(String nombreTrampa, int fila, int columna) {
        constructor.construirTrampa(nombreTrampa, this.creditos, fila, columna);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirCreditos(int creditos) {
        this.creditos += creditos;
        LOGGER.info("Jugador recibe " + creditos + " créditos");
    }

    public void recibirDanio(int danio) {
        this.vida -= danio;
        LOGGER.info("Jugador recibe " + danio + " de danio");
    }

    public void gastarCreditos(int costo) {
        creditos -= costo;
        LOGGER.info("Jugador ahora tiene " + creditos + " créditos");
    }
}