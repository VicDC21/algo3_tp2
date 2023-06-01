package edu.fiuba.algo3.modelo;

public class PasarelaSalida extends Pasarela {

    PasarelaSalida(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    protected PasarelaSalida(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa, pasarelaSiguiente);
        Hormiga hormiga = new Hormiga(1, 1, 1, "asd", this);
        agregarEnemigo(hormiga);
    }
    public void agregarEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }
}
