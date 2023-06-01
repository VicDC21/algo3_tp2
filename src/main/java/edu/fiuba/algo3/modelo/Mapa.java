package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
        List<Parcela> parcelas;
        protected int cantidadDeCreditosGeneradosEnTurno;

        public Mapa() {
                parcelas = new ArrayList<Parcela>();

                //placeholder del mapa propiamente dicho.
                for (int i = 0; i < 10; i++) {
                        Tierra tierra = new Tierra(i, 0, this);
                        parcelas.add(tierra);
                }
                for (int i = 0; i < 10; i++) {
                        Rocoso rocoso = new Rocoso(i, 1, this);
                        parcelas.add(rocoso);
                }

                parcelas.add(new PasarelaSalida(1, 2, this, null));

                for (int i = 1; i < 10; i++) {
                        Pasarela pasarela = new Pasarela(i, 2, this, null);
                        parcelas.add(pasarela);
                }

                for (int i = 20; i < 29; i++) {
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
                this.cantidadDeCreditosGeneradosEnTurno = 0;
                for (Parcela p : parcelas) {
                        p.avanzarTurno();
                        this.cantidadDeCreditosGeneradosEnTurno += p.devolverCantidadDeCreditosGeneradosEnTurno();
                }
        }

        public int devolverCantidadDeCreditosGeneradosEnTurno() {
                return this.cantidadDeCreditosGeneradosEnTurno;
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
