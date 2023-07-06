package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Torre extends Defensa{
    int turnosParaConstruirse;
    int danio;
    int rango;
    int tipo;
    IntegerProperty tipoProperty = new SimpleIntegerProperty(tipo);

    public Torre(int costo, int turnosParaConstruirse, int rango, int danio, int tipo) {
        super(costo);
        this.turnosParaConstruirse = turnosParaConstruirse;
        this.rango = rango;
        this.danio = danio;
        this.tipo = tipo;
        tipoProperty.set(tipo);
    }

    public Torre(Torre torre) {        //shallow copy pero los atributos son inmutables.
        super(torre.getCosto());
        this.turnosParaConstruirse = torre.getTurnosParaConstruirse();
        this.rango = torre.getRango();
        this.danio = torre.getDanio();
        this.tipo = torre.getTipo();
        tipoProperty.set(tipo);
    }

    public int getTipo() {
        return tipo;
    }

    protected int getRango() {
        return this.rango;
    }

    public int getCosto() {
        return this.costo;
    }

    protected int getDanio() {
        return this.danio;
    }

    protected int getTurnosParaConstruirse() {
        return this.turnosParaConstruirse;
    }

    public boolean puedeConstruirseEnParcela(Parcela parcela){
        return parcela.puedeContenerTorre();
    }

    public void avanzarTurno(Mapa mapa, int fila, int columna) {
        if (estaOperativa()) {
            atacar(mapa, fila, columna);
            return;
        } else if (turnosParaConstruirse == 1) {
            LOGGER.info("Torre terminó de construirse");
        } else {
            LOGGER.info("Torre a " + turnosParaConstruirse + " turnos de terminar su construcción");
        }
        turnosParaConstruirse--;
    }

    // No debe considerar Topos.
    public void atacar(Mapa mapa, int fila, int columna) {
        mapa.obtenerParcelasEnArea(fila, columna, rango)
                .stream()
                .filter(Parcela::tieneEnemigos)
                .findAny()
                .ifPresent(parcela -> parcela.recibirDanio(danio));
        LOGGER.info("Torre atacando con danio " + danio + " y rango " + rango);
    }

    public boolean estaOperativa() {
        return turnosParaConstruirse <= 0;
    }
}
