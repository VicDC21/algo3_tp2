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
        for (int i = 0; i < velocidad; i++) {
            pasarelaActual.moverEnemigoALaPasarelaSiguiente(this);
            pasarelaActual = pasarelaActual.obtenerPasarelaSiguiente();
        }
        if (pasarelaActual.esLaDeLLegada()) {
            causarDanio();
        }
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

    public void setPasarelaSalida(PasarelaSalida pasarelaSalida) {
        pasarelaActual = pasarelaSalida;
    }

    public boolean estaEnEstaPasarela(Pasarela pasarela) {
        return pasarelaActual == pasarela;
    }

    public abstract void suscribirTodo();

    public abstract void desuscribirTodo();
}
