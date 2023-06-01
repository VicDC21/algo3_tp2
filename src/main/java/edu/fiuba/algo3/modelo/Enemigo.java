package edu.fiuba.algo3.modelo;

public abstract class Enemigo {
    protected int energia;
    protected int velocidad;
    protected int danio;
    protected String estado;

    protected Pasarela pasarelaActual;

    public Enemigo(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        this.energia = energia;
        this.velocidad = velocidad;
        this.danio = danio;
        this.estado = estado;
        this.pasarelaActual = pasarelaActual;
    }
    public void avanzar() {

    }

    public void causarDanio() {
        pasarelaActual.realizarDanioJugador(danio);
    }

    public void recibirDanio(int danio) {
        if (!this.estaMuerto()) {
            this.energia -= danio;
        }
    }

    public boolean estaMuerto() {
        return this.energia <= 0;
    }

    public abstract int otorgarCredito();

}
