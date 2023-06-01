package edu.fiuba.algo3.modelo;

public abstract class Enemigo {
    protected int energia;
    protected int velocidad;
    protected int danio;
    protected String estado;

    protected Pasarela pasarelaActual;

    public void avanzar() {

    }

    public void causarDanio() {

    }

    public void recibirDanio() {

    }

    public boolean estaMuerto() {
        return this.estado.equals("Muerto");
    }

    public abstract int otorgarCredito();

}
