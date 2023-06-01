package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
        List<Parcela> parcelas;

        public Mapa() {
                parcelas = new ArrayList<Parcela>();

                //placeholder del mapa propiamente dicho.
                for (int i = 0; i < 5; i++) {
                        Tierra tierra = new Tierra(i, 0, this);
                        parcelas.add(tierra);
                }
                for (int i = 5; i < 10; i++) {
                        Rocoso rocoso = new Rocoso(i, 1, this);
                        parcelas.add(rocoso);
                }

                parcelas.add(new PasarelaSalida(10, 2, this, null));

                for (int i = 11; i < 14; i++) {
                        Pasarela pasarela = new Pasarela(i, 2, this, null);
                        parcelas.add(pasarela);
                }

                for (int i = 10; i < 13; i++) {
                        Pasarela pasarela = (Pasarela) parcelas.get(i);
                        pasarela.setPasarelaSiguiente((Pasarela) parcelas.get(i+1));
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

        public List<Parcela> obtenerParcelasEnArea(int fila, int columna, int radio) {
                List<Parcela> lista = new ArrayList<>();
                for (Parcela p : parcelas) {
                        if (p.enRadioDe(fila, columna, radio)) {
                                lista.add(p);
                        }
                }
                return lista;
        }

        public int cantidadDeEnemigos() {
                int cantidad = 0;
                for (Parcela p : parcelas) {
                        if (p.tieneEnemigos()) {
                                cantidad += p.cantidadDeEnemigos();
                        }
                }
                return cantidad;
        }
}
