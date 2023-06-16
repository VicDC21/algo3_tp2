package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.excepciones.CreditoInsuficiente;

import java.util.HashMap;
import java.util.Map;

public class Constructor {
//    Map<String, Runnable> tiposDeTorre;
    Map<String, Torre> tiposDeTorre;
    Mapa mapa;
    public Constructor(Mapa mapa) {
        this.mapa = mapa;
        this.tiposDeTorre = new HashMap<>();
        tiposDeTorre.put("torreBlanca", new Torre(10,1,3,1));
        tiposDeTorre.put("torrePlateada", new Torre(20,2,5,2));
//        tiposDeTorre.put("torrePlateada", () -> new Torre(20, 2, 5, 2));
//        tiposDeTorre.put("torreBlanca", () -> new Torre(10,1,3,1));
    }
    public void construir(String tipoDeTorre, int creditosDisponibles, int numeroParcela) {
        Torre torre = new Torre(tiposDeTorre.get(tipoDeTorre));
//        Torre torre = tiposDeTorre.get(tipoDeTorre).run();
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construir(torre, numeroParcela);
        } else throw new CreditoInsuficiente();
    }

    public void construir(String tipoDeTorre, int creditosDisponibles, int fila, int columna) {
        Torre torre = new Torre(tiposDeTorre.get(tipoDeTorre));
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construir(torre, fila, columna);
        } else throw new CreditoInsuficiente();
    }

}
