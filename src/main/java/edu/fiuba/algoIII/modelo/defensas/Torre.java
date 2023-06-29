package edu.fiuba.algoIII.modelo.defensas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Torre {
    private static final Logger LOGGER = LoggerFactory.getLogger(Torre.class.getSimpleName());
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

    public Torre() {
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

    public boolean puedoConstruirConCreditos(int creditosDisponibles) {
        return creditosDisponibles >= this.costo;
    }

    public boolean puedeConstruirseEnParcela(Parcela parcela){
        return (parcela instanceof Tierra);
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
                .filter(Parcela::tieneEnemigosFueraDeTierra)
                .findAny()
                .ifPresent(parcela -> parcela.recibirDanio(danio));
        LOGGER.info("Torre atacando con danio " + danio + " y rango " + rango);

//        mapa.atacarParcelaEnRango(fila, columna, rango, danio);
    }

    public boolean estaOperativa() {
        return turnosParaConstruirse <= 0;
    }

    public void dibujarse(Pane pane) {
        ImageView view = new ImageView("tower.png");
        view.fitWidthProperty().bind(pane.widthProperty());
        view.fitHeightProperty().bind(pane.heightProperty());
        view.setPreserveRatio(true);
        pane.getChildren().add(view);
    }
}
