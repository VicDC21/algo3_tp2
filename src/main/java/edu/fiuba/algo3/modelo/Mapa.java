package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
        List<Parcela> parcelas;

        public Mapa() {
                parcelas = new ArrayList<Parcela>();

                //placeholder del mapa propiamente dicho.
                for (int i = 0; i < 5; i++) {
                        Tierra tierra = new Tierra(i);
                        parcelas.add(tierra);
                }
                for (int i = 5; i < 9; i++) {
                        Rocoso rocoso = new Rocoso(i);
                        parcelas.add(rocoso);
                }
        }

        public void construir(Torre torre, int numeroParcela) {
                Parcela parcela = parcelas.get(numeroParcela);
                if (parcela.puedeAlojarTorre()) {
                        parcela.construir(torre);
                } else throw new ParcelaNoConstruible();
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
