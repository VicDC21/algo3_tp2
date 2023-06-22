package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.JavaFX.TierraPane;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreNull;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tierra extends Parcela {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tierra.class.getSimpleName());
    Torre torre = new TorreNull();
    public Tierra(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    @Override
    public Pane dibujarse(int width, int height) {
        Pane pane = new TierraPane(width, height);
        torre.dibujarse(pane);
        return pane;
    }
    private boolean tieneTorre() {
        return !(torre.esNull());
    }

    @Override
    public boolean tieneEnemigos() {
        return false;
    }

    @Override
    public void avanzarTurno() {
        torre.avanzarTurno(mapa, fila, columna);
    }

    @Override
    public void construirTorre(Torre torre) {
        if (!tieneTorre()) {
            this.torre = torre;
        } else throw new ParcelaNoConstruible();
        LOGGER.info("Construyendo " + torre.getClass().getSimpleName());
    }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }

    @Override
    public void destruirConstuccion() {
        this.torre = new TorreNull();
    }

    @Override
    public void recibirDanio(int danio) {}

    @Override
    public int cantidadDeEnemigos() {
        return 0;
    }

}
