package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
        List<Parcela> parcelas;

        public Mapa() {
                parcelas = new ArrayList<Parcela>();

                //placeholder
                for (int i = 0; i < 5; i++) {
                        Tierra tierra = new Tierra(i);
                        parcelas.add(tierra);
                }
        }

        public void construir(Torre torre, int numeroParcela) {
                Parcela parcela = parcelas.get(numeroParcela);
                if (parcela.puedeAlojarTorre()) {
                        parcela.construir(torre);
                }
        }

        public boolean tieneEnemigos() {
                return false;
        }

        public void avanzarTurno() {
                for (Parcela p : parcelas) {
                        p.avanzarTurno();
                }
        }
}
