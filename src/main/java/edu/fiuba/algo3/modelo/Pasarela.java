package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pasarela extends Parcela {

    protected Pasarela pasarelaSiguiente;
    protected List<Enemigo> enemigos = new ArrayList<Enemigo>();

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
        if (!enemigos.isEmpty()) {
            for (Enemigo enemigo : enemigos) {
                if (enemigo.estaMuerto()) {
                    cantidadDeCreditosGeneradosEnTurno += enemigo.otorgarCredito();
                }
            }
            enemigos.removeIf(Enemigo::estaMuerto);
            for (Enemigo e : enemigos) {
                e.avanzar();
            }
        }
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

   /*  public boolean puedeRecibirEnemigo(Enemigo enemigo){
        return true;
    }*/
}
