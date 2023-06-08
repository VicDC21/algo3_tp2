package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos = new ArrayList<>();

    protected int cantidadDeCreditosGeneradosEnTurno;

    public Pasarela(int fila, int columna, Mapa mapa) {
        super(fila, columna, mapa);
    }
    public Pasarela(int fila, int columna, Mapa mapa, Pasarela pasarelaSiguiente) {
        super(fila, columna, mapa);
        this.pasarelaSiguiente = pasarelaSiguiente;
    }

    @Override
    public boolean tieneEnemigos() {
        return !enemigos.isEmpty();
    }

    @Override
    public void avanzarTurno() {
        this.cantidadDeCreditosGeneradosEnTurno = 0;
        if (!tieneEnemigos()) {
            return;
        }
        enemigos.stream()
                .filter(Enemigo::estaMuerto)
                .forEach(enemigo -> cantidadDeCreditosGeneradosEnTurno += enemigo.otorgarCredito());
        enemigos.removeIf(Enemigo::estaMuerto);
        enemigos.forEach(Enemigo::avanzar);
        enemigos.removeIf(enemigo -> enemigo.estaEnEstaPasarela(this)); //ESTA REMOVIENDO TODOS LOS ENEMIGOS, NECESITO QUE SOLO REMUEVA LOS QUE SE MOVIERON A LA SIGUIENTE.
    }

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        return this.cantidadDeCreditosGeneradosEnTurno;
    }

    @Override
    public void construir(Torre torre) {
        throw new ParcelaNoConstruible();
    }

    @Override
    public void recibirDanio(int danio) {
        enemigos.get(0).recibirDanio(danio);
    }

    public void setPasarelaSiguiente(Pasarela pasarela) {
        this.pasarelaSiguiente = pasarela;
    }

    public int cantidadDeEnemigos() {
        return enemigos.size();
    }

    public void recibirEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
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
}
