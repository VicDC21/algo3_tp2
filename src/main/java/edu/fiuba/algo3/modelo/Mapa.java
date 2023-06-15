package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.*;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa {
    List<Parcela> parcelas = new ArrayList<>();
    PasarelaSalida pasarelaSalida;
    Turno turno = new Turno();
    List<Parcela> parcelasConTorre = new ArrayList<>();

    public void construir(Torre torre, int numeroParcela) {
        parcelas.get(numeroParcela).construirTorre(torre);
    }

    public void construir(Torre torre, int fila, int columna) {
        obtenerParcela(fila, columna).construirTorre(torre);
    }

    public boolean tieneEnemigos() {
        return parcelas.stream().anyMatch(Parcela::tieneEnemigos);
    }

    // Deberiamos decidir como implementar el orden de comportamientos(primero torres despues enemigos)
    // Una forma es fraccionar avanzarTurno a avanzarTorres, etc.
    public void avanzarTurno() {
        turno.avanzarTurno();
        parcelas.forEach(Parcela::avanzarTurno);
    }

    public int creditosGeneradosEnTurno() {
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
                .filter(parcela -> parcela.enRadioDe(fila, columna, 0))         //Cambiar esto.
                .collect(Collectors.toList());

        return listaDeParcelasEncontradas.get(0);
    }

    @Override
    public String toString() {
        return this.parcelas.toString();
    }
    
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

    public void removerMuertos() {
        parcelas.forEach(Parcela::removerMuertos);
    }

    public void actualizarEnemigos() {
        parcelas.forEach(Parcela::actualizarEnemigos);
    }

    public void setJugador(Jugador jugador) {
        PasarelaLlegada pasarelaLlegada = (PasarelaLlegada) parcelas.stream().filter(parcela -> parcela instanceof PasarelaLlegada).findFirst().orElseThrow();
        pasarelaLlegada.setJugador(jugador);
    }

    public void suscribirTurno(SuscriptorTurno suscriptor) {
        turno.suscribir(suscriptor);
    }

    public void desuscribirTurno(SuscriptorTurno suscriptor) {
        turno.desuscribir(suscriptor);
    }

    public void destruirPrimeraTorre() {
        if (!parcelasConTorre.isEmpty()) {
            parcelasConTorre.remove(0).destruirConstuccion();
        }
    }

    public List<Shape> getTiles() {
        List<Shape> shapes = new ArrayList<>();
        for (Parcela p : parcelas) {
            shapes.add(p.dibujarse());
        }
        return shapes;
    }
}
