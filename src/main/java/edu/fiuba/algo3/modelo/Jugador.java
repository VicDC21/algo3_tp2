package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
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
    }
    public int mostrarVida() {
        return this.vida;
    }

    public int mostrarCreditos() {
        return this.creditos;
    }

    public void construir(String construible, int numeroParcela) {
        constructor.construir(construible, this.creditos, numeroParcela);
    }
    public void construir(String construible, int fila, int columna) {
        constructor.construir(construible, this.creditos, fila, columna);
    }
    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirCreditos(int creditos) {
        this.creditos += creditos;
        LOGGER.info("Jugador recibe " + creditos + " créditos");
    }

    public void recibirDanio(int danio) { this.vida -= danio; }
}