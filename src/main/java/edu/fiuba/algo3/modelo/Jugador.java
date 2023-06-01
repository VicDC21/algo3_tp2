package edu.fiuba.algo3.modelo;

public class Jugador {

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

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void recibirCreditos(int creditos) {
        this.creditos += creditos;
    }

    public void recibirDanio(int danio) { this.vida -= danio; }
}
