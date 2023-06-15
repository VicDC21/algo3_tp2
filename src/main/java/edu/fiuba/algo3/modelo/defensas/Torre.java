package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class Torre{
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

    public Torre(Torre torre) {        //shallow copy pero los atributos son inmutables.
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

    public void avanzarTurno(Mapa mapa, int fila, int columna) {
        if (estaOperativa()) {
            atacar(mapa, fila, columna);
        } else {
            turnosParaConstruirse--;
        }
    }

    // No debe considerar Topos.
    public void atacar(Mapa mapa, int fila, int columna) {
        mapa.obtenerParcelasEnArea(fila, columna, rango)
                .stream()
                .filter(Parcela::tieneEnemigos)
                .findAny()
                .ifPresent(parcela -> parcela.recibirDanio(danio));
    }

    public boolean estaOperativa() {
        return turnosParaConstruirse <= 0;
    }
}
