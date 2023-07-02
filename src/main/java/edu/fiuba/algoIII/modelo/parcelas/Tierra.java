package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.TorreNull;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tierra extends Parcela {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tierra.class.getSimpleName());
    Torre torre = new TorreNull();

    public Tierra(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }

    @Override
    public void avanzarTurno() {
        super.avanzarTurno();
        torre.avanzarTurno(mapa, fila, columna);
    }

    @Override
    public void construirTorre(Torre torre) {
        this.torre = torre;
        LOGGER.info("Construyendo " + torre.getClass().getSimpleName());
    }

    @Override
    public void construirTrampa(Trampa trampa) { throw new ParcelaNoConstruible(); }

    @Override
    public void destruirConstruccion() {
        LOGGER.info("Destruyendo " + torre.getClass().getSimpleName());
        this.torre = new TorreNull();
    }

    @Override
    public String obtenerImagen() {
        return "tierra.jpg";
    }

}
