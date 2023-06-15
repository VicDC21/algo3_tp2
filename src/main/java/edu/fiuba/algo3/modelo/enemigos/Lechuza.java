package edu.fiuba.algo3.modelo.enemigos;

// Tiene 5 de velocidad, 5 de energia
// No causa danio al jugador, sino destruye la primera torre construida.
// Vuela. Puede moverse por cualquier tipo de parcela, va en L y
// dps con menos de 50% de vida va en recta.

import edu.fiuba.algo3.modelo.parcelas.Pasarela;

public class Lechuza extends Enemigo {
    private int creditos = 0;

    public Lechuza(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    public Lechuza() {
        super(5, 5, 0, "Vivo", null);
    }

    @Override
    public void causarDanio() {
        pasarelaActual.destruirPrimeraTorre();
    }

    @Override
    public int otorgarCredito() {
        return 0;
    }

    @Override
    public void suscribirTodo() {}

    @Override
    public void desuscribirTodo() {}

}
