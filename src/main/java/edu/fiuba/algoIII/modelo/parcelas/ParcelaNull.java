package edu.fiuba.algoIII.modelo.parcelas;

import edu.fiuba.algoIII.modelo.SuscriptorTurno;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.defensas.Trampa;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;

public class ParcelaNull extends Parcela{
    public ParcelaNull()
    {
        super(-1, -1, null);
    }

    @Override
    public void actualizarEnemigos() {}

    @Override
    public void recibirEnemigo(Enemigo enemigo) {}

    @Override
    public int distanciaFilasHastaParcela(int desdeFila) { return 0; }

    @Override
    public int distanciaColumnasHastaParcela(int desdeColumna) {
        return 0;
    }
    @Override
    public void avanzarTurno() {}

    public void construirTorre(Torre torre) {}

    public void construirTrampa(Trampa trampa) {}

    @Override
    public boolean enRadioDe(int fila, int columna, int radio) {
        return false;
    }

    @Override
    public void removerMuertos() {}

    @Override
    public void recibirDanio(int danio) {}

    @Override
    public void suscribirTurno(SuscriptorTurno suscriptor) {}

    @Override
    public void desuscribirTurno(SuscriptorTurno suscriptor) {}

    @Override
    public Parcela proximaParcelaComoCatetoHastaLlegadaDesdeAca() {
        return this;
    }

    @Override
    public Parcela proximaParcelaEnLineaRectaHastaLlegadaDesdeAca() {
        return this;
    }

    public boolean puedeContenerTorre() { return false; }

    public String obtenerImagen() { return "parcelaNula.jpg"; }
}
