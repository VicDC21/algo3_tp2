package edu.fiuba.algo3.modelo;

public class Torre {
    int costo;
    int turnosParaConstruirse;
    int danio;
    int rango;

    public Torre(int costo, int turnosParaConstruirse, int rango, int danio) {
        this.costo = costo;
        this.turnosParaConstruirse = turnosParaConstruirse;
        this.rango = rango;
        this.danio = danio;
    }

    Torre(Torre torre) {        //shallow copy pero los atributos son inmutables.
        this.costo = torre.getCosto();
        this.turnosParaConstruirse = torre.getTurnosParaConstruirse();
        this.rango = torre.getRango();
        this.danio = torre.getDanio();
    }

    private int getRango() {
        return this.rango;
    }

    private int getCosto() {
        return this.costo;
    }

    private int getDanio() {
        return this.danio;
    }

    private int getTurnosParaConstruirse() {
        return this.turnosParaConstruirse;
    }

    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return creditosDisponibles >= this.costo;
    }

    public void avanzarTurno() {
        if(!estaOperativa()) {
            turnosParaConstruirse--;
        } else {
            atacar();
        }
    }

    private void atacar() {
    }

    public boolean estaOperativa() {
        return turnosParaConstruirse <= 0;
    }
}
