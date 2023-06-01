package edu.fiuba.algo3.modelo;

public class Hormiga extends Enemigo {

    public Hormiga(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    @Override
    public int otorgarCredito() {
        return 0;
    }
}
