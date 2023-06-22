package edu.fiuba.algo3.modelo.enemigos;

// En sus primeros 5 moviemientos tiene 1 de velocidad, luego en los proximos 5 su velocidad es 2,
// a partir del movimiento 11 su velocidad queda definitivamente en 3.

// Si el numero de turno en que llega a la meta es impar, entonces causa 5 puntos de adnio, sino solo 2.

// El topo no camina por la superficie, va enterrado y ninguna torre lo puede atacar.

import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import edu.fiuba.algo3.modelo.SuscriptorTurno;

public class Topo extends Enemigo implements SuscriptorTurno {
    private int creditos = 1;
    private int nroMovimiento = 0;

    public Topo(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    public Topo() {
        super(1, 1, 1, "Vivo", null);
    }

    @Override
    public void avanzar() {
        nroMovimiento++;
        if (nroMovimiento == 5) {
            this.velocidad = 2;
        }
        if (nroMovimiento == 10) {
            this.velocidad = 3;
        }

        for (int i = 0; i < velocidad; i++) {
            pasarelaActual.moverEnemigoALaPasarelaSiguiente(this);
            pasarelaActual = pasarelaActual.obtenerPasarelaSiguiente();
            if (pasarelaActual.esLaDeLLegada()) {
                causarDanio();
                break;
            }
        }
    }

    @Override
    public void notificar(int turno) {
        if (turno % 2 == 1) { // si es impar
            this.danio = 5;
        } else {
            this.danio = 2;
        }
    }

    @Override
    public int otorgarCredito() {
        return this.creditos;
    }

    @Override
    public void suscribirTodo() {
        pasarelaActual.suscribirTurno(this);
    }

    @Override
    public void desuscribirTodo() {
        pasarelaActual.desuscribirTurno(this);
    }
}
