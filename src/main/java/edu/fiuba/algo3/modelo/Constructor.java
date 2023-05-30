package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Constructor {
    Map<String, Torre> tiposDeTorre;
    Mapa mapa;
    public Constructor(Mapa mapa) {
        this.mapa = mapa;
        this.tiposDeTorre = new HashMap<>();
        tiposDeTorre.put("torreBlanca", new Torre(10,1,3,1));
        tiposDeTorre.put("torrePlateada", new Torre(20,2,5,2));
    }

    public void construir(String tipoDeTorre, int creditosDisponibles, int numeroParcela) {
        Torre torre = new Torre(tiposDeTorre.get(tipoDeTorre));
        if (torre.puedoConstruirConCreditos(creditosDisponibles)) {
            mapa.construir(torre, numeroParcela);
        } else throw new CreditoInsuficiente();
    }
}
