package edu.fiuba.algoIII.modelo;

import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.defensas.TrampaArenosa;
import edu.fiuba.algoIII.modelo.excepciones.CreditoInsuficiente;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constructor {
//    Map<String, Runnable> tiposDeTorre;
    private static final Logger LOGGER = LoggerFactory.getLogger(Jugador.class.getSimpleName());
    Map<String, Torre> tiposDeTorre;
    //Map<String, Class> tiposDeTrampa;
    Mapa mapa;
    Jugador jugador;

    public Constructor(Mapa mapa) {
        this.mapa = mapa;
        this.tiposDeTorre = new HashMap<>();
        tiposDeTorre.put("torreBlanca", new Torre(10,1,3,1));
        tiposDeTorre.put("torrePlateada", new Torre(20,2,5,2));
        /*this.tiposDeTrampa = new HashMap<>();
        tiposDeTrampa.put("TrampaArenosa", TrampaArenosa.class);*/
    }

    protected void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void construirTorre(String nombreTorre, int creditosDisponibles, int fila, int columna) {
        Torre torre = new Torre(tiposDeTorre.get(nombreTorre));
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construirTorre(torre, fila, columna);
            jugador.gastarCreditos(torre.getCosto());
        } else {
            LOGGER.info("Créditos insuficientes para construir torre");
            throw new CreditoInsuficiente();
        }
    }

    public void construirTrampa(/*String nombreTrampa,*/ int creditosDisponibles, int fila, int columna) {
        Trampa trampa = new TrampaArenosa();
        if (trampa.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construirTrampa(trampa, fila, columna);
            jugador.gastarCreditos(trampa.getCosto());
        } else {
            LOGGER.info("Créditos insuficientes para construir trampa");
            throw new CreditoInsuficiente();
        }
    }

}
