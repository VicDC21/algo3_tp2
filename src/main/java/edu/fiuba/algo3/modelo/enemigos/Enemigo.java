package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaSalida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Enemigo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Enemigo.class.getSimpleName());
    protected int energia;
    protected int velocidad;
    protected int velocidadActual;
    protected int danio;
    protected String estado;
    protected Pasarela pasarelaActual;

    public Enemigo(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        this.energia = energia;
        this.velocidad = velocidad;
        this.velocidadActual = velocidad;
        this.danio = danio;
        this.estado = estado;
        this.pasarelaActual = pasarelaActual;
    }

    public void avanzar() {
        for (int i = 0; i < velocidad; i++) {
            pasarelaActual.moverEnemigoALaPasarelaSiguiente(this);
            pasarelaActual = pasarelaActual.obtenerPasarelaSiguiente();
        }
        causarDanio();
    }

    public void causarDanio() {
        pasarelaActual.realizarDanioJugador(danio);
    }

    public void recibirDanio(int danio) {
        if (!this.estaMuerto()) {
            this.energia -= danio;
        }
        LOGGER.info(this.getClass().getSimpleName() + " recibe daño: " + danio);
    }

    public boolean estaMuerto() {
        return this.energia <= 0;
    }

    public abstract int otorgarCredito();

    public void setPasarelaSalida(PasarelaSalida pasarelaSalida) {
        pasarelaActual = pasarelaSalida;
    }

    public abstract void suscribirTodo();

    public abstract void desuscribirTodo();

    public void reestablecerVelocidad() {
        this.velocidadActual = this.velocidad;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void modificarVelocidadTierra(double modificadorVelocidad) {
        this.velocidadActual = (int)(this.velocidad * modificadorVelocidad); // se redondea para abajo
    }
}
