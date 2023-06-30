package edu.fiuba.algoIII.modelo.enemigos;

import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Arania extends Enemigo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Arania.class.getSimpleName());

    public Arania(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    public Arania() {
        super(2, 2, 2, "Vivo", null);
    }

    @Override
    public int otorgarCredito() {
        Random random = new Random();
        int creditosMaximos = 11;
        int rand = random.nextInt(creditosMaximos);
        LOGGER.info("Arania otorgando " + rand + " créditos");
        return rand;
    }

    @Override
    public void avanzar() {
        Pasarela pasarelaAMover = (Pasarela) parcelaActual;
        for (int i = 0; i < velocidadActual; i++) {
            pasarelaAMover = pasarelaAMover.obtenerPasarelaSiguiente();
        }
        pasarelaAMover.recibirEnemigo(this);
        parcelaActual = pasarelaAMover;
        causarDanio();
    }

    public void causarDanio() {
        ((Pasarela) parcelaActual).causarDanioJugador(danio);
    }

    @Override
    public void suscribirTodo() {}

    @Override
    public void desuscribirTodo() {}

    @Override
    public String obtenerImagen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerImagen'");
    }
}
