package edu.fiuba.algo3.modelo;

import java.util.Random;

public class Arania extends Enemigo {

    public Arania(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    @Override
    public int otorgarCredito() {
        Random random = new Random();
        int creditosMaximos = 11;
        return random.nextInt(creditosMaximos);
    }
}
