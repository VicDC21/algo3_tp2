package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.SuscriptorTurno;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class Parcela {
    protected int fila;
    protected int columna;
    protected List<Enemigo> enemigos = new ArrayList<>();
    protected List<Enemigo> arribos = new ArrayList<>();
    protected static final Logger LOGGER = LoggerFactory.getLogger(Pasarela.class.getSimpleName());
    Mapa mapa;

    public Parcela(int fila, int columna, Mapa mapa)
    {
        this.fila = fila;
        this.columna = columna;
        this.mapa = mapa;
    }

    public void actualizarEnemigos() {
        enemigos = arribos;
        arribos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            e.reestablecerVelocidad();
        }
    }

    public void recibirEnemigo(Enemigo enemigo) {
        this.arribos.add(enemigo);
        LOGGER.info("Pasarela "+ this.fila+ " " + this.columna +" recibiendo enemigo " + enemigo.getClass().getSimpleName());

    }

    public int distanciaFilasHastaParcela(int desdeFila) {
        return this.fila - desdeFila;
    }

    public int distanciaColumnasHastaParcela(int desdeColumna) {
        return this.columna - desdeColumna;
    }

    public void avanzarTurno() {
        if (!tieneEnemigos()) {
            return;
        }
        enemigos.forEach(Enemigo::avanzar);
    }

    public abstract void construirTorre(Torre torre);

    public abstract void construirTrampa(Trampa trampa);

    public boolean enRadioDe(int fila, int columna, int radio) {
        return (fila - radio <= this.fila) && (this.fila <= fila + radio) && (columna - radio <= this.columna) && (this.columna <= columna + radio);
    }

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        return
                enemigos.stream()
                        .filter(Enemigo::estaMuerto)
                        .mapToInt(Enemigo::otorgarCredito)
                        .sum();
    }

    public boolean tieneEnemigos() {
        for (Enemigo e: enemigos) {
            if (!e.estaMuerto()) {
                return true;
            }
        }
        for (Enemigo e: arribos) {
            if (!e.estaMuerto()) {
                return true;
            }
        }
        return false;
    }

    public boolean tieneEnemigosFueraDeTierra() {
        for (Enemigo e: enemigos) {
            if (!e.estaMuerto() && e.estaFueraDeTierra()) {
                return true;
            }
        }
        for (Enemigo e: arribos) {
            if (!e.estaMuerto() && e.estaFueraDeTierra()) {
                return true;
            }
        }
        return false;
    }

    public void removerMuertos() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaMuerto()) {enemigo.desuscribirTodo();}
        }
        enemigos.removeIf(Enemigo::estaMuerto);
        arribos.removeIf(Enemigo::estaMuerto);
    }

    public void recibirDanio(int danio) {

        Stream<Enemigo> enemigosTotales =
                Stream.concat(
                        enemigos.stream()
                                .filter(Objects::nonNull),
                        arribos.stream()
                                .filter(Objects::nonNull)
                );
        enemigosTotales.findAny()
                .orElseThrow()
                .recibirDanio(danio);
    }

    public void suscribirTurno(SuscriptorTurno suscriptor) { mapa.suscribirTurno(suscriptor); }

    public void desuscribirTurno(SuscriptorTurno suscriptor) { mapa.desuscribirTurno(suscriptor); }

    public void destruirConstruccion() {}

    public Parcela proximaParcelaComoCatetoHastaLlegadaDesdeAca() {
        return mapa.proximaParcelaComoCatetoHastaLlegada(this.fila, this.columna);
    }

    public Parcela proximaParcelaEnLineaRectaHastaLlegadaDesdeAca() {
        return mapa.proximaParcelaEnLineaRectaHastaLlegada(this.fila, this.columna);
    }

    public void destruirPrimeraTorre() {}
}
