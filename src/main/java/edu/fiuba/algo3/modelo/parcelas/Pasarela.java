package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.excepciones.ParcelaNoConstruible;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos = new ArrayList<>();
    private List<Enemigo> arribos = new ArrayList<>();
    protected int cantidadDeCreditosGeneradosEnTurno;
    Trampa trampa = null;

    public Pasarela(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    public Pasarela(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa);
        this.pasarelaSiguiente = pasarelaSiguiente;
    }

    @Override
    public boolean tieneEnemigos() {
        return !(enemigos.isEmpty() && arribos.isEmpty());
    }

    @Override
    public void avanzarTurno() {
        if (!tieneEnemigos()) {
            return;
        }
        enemigos.forEach(Enemigo::avanzar);
//        enemigos.removeIf(enemigo -> enemigo.estaEnEstaPasarela(this)); //ESTA REMOVIENDO TODOS LOS ENEMIGOS, NECESITO QUE SOLO REMUEVA LOS QUE SE MOVIERON A LA SIGUIENTE.
    }

    public void removerMuertos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaMuerto()) {enemigo.desuscribirTodo();}
        }
        enemigos.removeIf(Enemigo::estaMuerto);
        arribos.removeIf(Enemigo::estaMuerto);
    }

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        //for (Enemigo e: enemigos) {
        //    System.out.println(e.estaMuerto());
        //}
        return
                enemigos.stream()
                        .filter(Enemigo::estaMuerto)
                        .mapToInt(Enemigo::otorgarCredito)
                        .sum()
                +
                arribos.stream()
                        .filter(Enemigo::estaMuerto)
                        .mapToInt(Enemigo::otorgarCredito)
                        .sum();
    }

    @Override
    public void construirTorre(Torre torre) { throw new ParcelaNoConstruible(); }

    @Override
    public void construirTrampa(Trampa trampa) {
        if (!tieneTrampa()) {
            this.trampa = trampa;
        } else throw new ParcelaNoConstruible();
    }

    private boolean tieneTrampa() { return trampa != null; }

    @Override
    public void recibirDanio(int danio) {

        Stream<Enemigo> enemigosTotales =
                Stream.concat(
                        enemigos.stream()
                                .filter(Objects::nonNull),
                        arribos.stream()
                                .filter(Objects::nonNull)
                );
        enemigosTotales.findAny()
                .orElseThrow()
                .recibirDanio(danio);
    }

    public void setPasarelaSiguiente(Pasarela pasarela) { this.pasarelaSiguiente = pasarela; }

    public int cantidadDeEnemigos() { return enemigos.size(); }

    public void actualizarEnemigos() {
        enemigos = arribos;
        arribos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            e.reestablecerVelocidad();
        }
        System.out.println(enemigos);
    }

    public void recibirEnemigo(Enemigo enemigo) {
//        this.enemigos.add(enemigo);
        this.arribos.add(enemigo);
    }

    public void realizarDanioJugador(int danio) {}

    public void moverEnemigoALaPasarelaSiguiente(Enemigo enemigo) {
        pasarelaSiguiente.recibirEnemigo(enemigo);
    }

    public Pasarela obtenerPasarelaSiguiente() {
        return pasarelaSiguiente;
    }

    public boolean esLaDeLLegada() {
        return false;
    }

    public void destruirPrimeraTorre() {}

    public void modificarVelocidad(double modificadorVelocidad) {
        for (Enemigo e : enemigos) {
            e.modificarVelocidad(modificadorVelocidad);
        }
    }
}
