package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
        List<Parcela> parcelas;

        public Mapa() {
                parcelas = new ArrayList<>();

                //placeholder del mapa propiamente dicho.
                for (int i = 0; i < 10; i++) {
                        Tierra tierra = new Tierra(i, 0, this);
                        parcelas.add(tierra);
                }
                for (int i = 0; i < 10; i++) {
                        Rocoso rocoso = new Rocoso(i, 1, this);
                        parcelas.add(rocoso);
                }

                parcelas.add(new PasarelaSalida(0, 2, this, null));

                for (int i = 1; i < 9; i++) {
                        Pasarela pasarela = new Pasarela(i, 2, this, null);
                        parcelas.add(pasarela);
                }

                parcelas.add(new PasarelaLlegada(9, 2, this));

                for (int i = 20; i < 29; i++) {
                        Pasarela pasarela = (Pasarela) parcelas.get(i);
                        pasarela.setPasarelaSiguiente((Pasarela) parcelas.get(i+1));
                }
        }

        public void construir(Torre torre, int numeroParcela) {
                parcelas.get(numeroParcela).construir(torre);
        }

        public boolean tieneEnemigos() {
                return parcelas.stream().anyMatch(Parcela::tieneEnemigos);
        }

        public void avanzarTurno() {
                parcelas.forEach(Parcela::avanzarTurno);
        }

        public int devolverCantidadDeCreditosGeneradosEnTurno() {
                return parcelas.stream()
                        .mapToInt(Parcela::devolverCantidadDeCreditosGeneradosEnTurno)
                        .sum();
        }

        public List<Parcela> obtenerParcelasEnArea(int fila, int columna, int radio) {
                return parcelas.stream()
                        .filter(parcela -> parcela.enRadioDe(fila, columna, radio))
                        .collect(Collectors.toList());
        }

        public int cantidadDeEnemigos() {       //revisar si puedo devolver long.
                return (int) parcelas.stream()
                        .filter(Parcela::tieneEnemigos)
                        .count();
        }

        public void agregarParcelas(List<Parcela> lista) {
                parcelas = lista;
//                parcelas.addAll(lista);
        }

        public Parcela obtenerParcela(int fila, int columna) {
                List<Parcela> listaDeParcelasEncontradas = this.parcelas.stream()
                        .filter(parcela -> parcela.enRadioDe(fila, columna, 0))
                        .collect(Collectors.toList());

                return listaDeParcelasEncontradas.get(0);
        }
        @Override
        public String toString() { return this.parcelas.toString(); }

        public void cargarEnemigos(ArrayList<List<Enemigo>> enemigosParseados) {
                PasarelaSalida salida = obtenerPasarelaSalida();
                salida.cargarEnemigos(enemigosParseados);
        }

        private PasarelaSalida obtenerPasarelaSalida() {
                return (PasarelaSalida) parcelas.stream()
                        .filter(parcela -> parcela instanceof PasarelaSalida)
                        .findFirst()
                        .orElseThrow();
        }
}