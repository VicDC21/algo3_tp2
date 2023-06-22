package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.excepciones.CreditoInsuficiente;

import java.util.HashMap;
import java.util.Map;

public class Constructor {
//    Map<String, Runnable> tiposDeTorre;
    Map<String, Torre> tiposDeTorre;
    Map<String, Class> tiposDeTrampa;
    Mapa mapa;
    Jugador jugador;

    public Constructor(Mapa mapa) {
        this.mapa = mapa;
        this.tiposDeTorre = new HashMap<>();
        tiposDeTorre.put("torreBlanca", new Torre(10,1,3,1));
        tiposDeTorre.put("torrePlateada", new Torre(20,2,5,2));
//        tiposDeTorre.put("torrePlateada", () -> new Torre(20, 2, 5, 2));
//        tiposDeTorre.put("torreBlanca", () -> new Torre(10,1,3,1));
        this.tiposDeTrampa = new HashMap<>();
        tiposDeTrampa.put("TrampaArenosa", TrampaArenosa.class);
    }

    protected void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void construirTorre(String nombreTorre, int creditosDisponibles, int numeroParcela) {
        Torre torre = new Torre(tiposDeTorre.get(nombreTorre));
//        Torre torre = tiposDeTorre.get(tipoDeTorre).run();
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construirTorre(torre, numeroParcela);
            jugador.gastarCreditos(torre.getCosto());
        } else throw new CreditoInsuficiente();
    }

    public void construirTorre(String nombreTorre, int creditosDisponibles, int fila, int columna) {
        Torre torre = new Torre(tiposDeTorre.get(nombreTorre));
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construirTorre(torre, fila, columna);
            jugador.gastarCreditos(torre.getCosto());
        } else throw new CreditoInsuficiente();
    }

    public void construirTrampa(String nombreTrampa, int creditosDisponibles, int fila, int columna) {
        Trampa trampa = new TrampaArenosa();
        if (trampa.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construirTrampa(trampa, fila, columna);
            jugador.gastarCreditos(trampa.getCosto());
        } else throw new CreditoInsuficiente();
    }

}
