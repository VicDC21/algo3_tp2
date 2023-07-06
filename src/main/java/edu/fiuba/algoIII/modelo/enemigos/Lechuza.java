package edu.fiuba.algoIII.modelo.enemigos;

// Tiene 5 de velocidad, 5 de energia
// No causa danio al jugador, sino destruye la primera torre construida.
// Vuela. Puede moverse por cualquier tipo de parcela, va en L y
// dps con menos de 50% de vida va en recta.

import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.ParcelaNull;

public class Lechuza extends Enemigo {
    final int creditos = 0;

    final int energiaMaxima = 5;

    public Lechuza(int energia, int velocidad, int danio, String estado, Parcela parcelaActual) {
        super(energia, velocidad, danio, estado, parcelaActual);
    }

    public Lechuza() {
        super(5, 5, 0, "Vivo", new ParcelaNull());
    }

    @Override
    public void avanzar() {
        Parcela proximaParcela = this.parcelaActual;
        int i = 0;
        if (energia > (energiaMaxima / 2.0f)) {
            while (i < this.velocidad && proximaParcela != proximaParcela.proximaParcelaComoCatetoHastaLlegadaDesdeAca()) {
                proximaParcela = proximaParcela.proximaParcelaComoCatetoHastaLlegadaDesdeAca();
                i++;
            }
        } else {
            while (i < this.velocidad && proximaParcela != proximaParcela.proximaParcelaEnLineaRectaHastaLlegadaDesdeAca()) {
                proximaParcela = proximaParcela.proximaParcelaEnLineaRectaHastaLlegadaDesdeAca();
                i++;
            }
        }
        proximaParcela.recibirEnemigo(this);
        this.parcelaActual = proximaParcela;
        destruirTorre();
    }

    public void destruirTorre() {
        parcelaActual.destruirPrimeraTorre();
    }

    @Override
    public int otorgarCredito() { return this.creditos; }

    @Override
    public void suscribirTodo() {}

    @Override
    public void desuscribirTodo() {}

    @Override
    public void modificarVelocidadTierra(double modificadorVelocidad) {}

    @Override
    public String obtenerImagen() {
        return "lechuza.png";
    }
}
