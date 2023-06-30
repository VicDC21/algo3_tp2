package edu.fiuba.algoIII.modelo;

import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;

import java.util.List;


import edu.fiuba.algoIII.modelo.parsers.ObtenedorDeEnemigos;
import edu.fiuba.algoIII.modelo.parsers.ObtenedorDeMapa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algoIII.App;

public class Juego {
    private static final Logger LOGGER = LoggerFactory.getLogger(Enemigo.class.getSimpleName());
    Jugador jugador;
    Mapa mapa;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    //Solo se usa para probar la ruta JSon del mapa
    public Juego(String path) throws InvalidMap {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        mapa = obtenedorDeMapa.obtenerMapa(path);
        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
    }

    public Juego(String pathMapa, String pathEnemigos) throws InvalidMap {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        ObtenedorDeEnemigos obtenedorDeEnemigos = new ObtenedorDeEnemigos();
        mapa = obtenedorDeMapa.obtenerMapa(pathMapa);
        mapa.cargarEnemigos(obtenedorDeEnemigos.obtenerEnemigos(pathEnemigos));
        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
        mapa.setJugador(jugador);       // Esto hay que sacarlo
        logger.info("Juego Iniciado"); 
    }

    public Juego(String pathMapa, String pathEnemigos, String nombre) throws InvalidMap {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        ObtenedorDeEnemigos obtenedorDeEnemigos = new ObtenedorDeEnemigos();
        mapa = obtenedorDeMapa.obtenerMapa(pathMapa);
        mapa.cargarEnemigos(obtenedorDeEnemigos.obtenerEnemigos(pathEnemigos));
        Constructor constructor = new Constructor(mapa);
        jugador = new Jugador(nombre, 20, 100, constructor);
        mapa.setJugador(jugador);    // Esto hay que sacarlo
        logger.info("Juego Iniciado");   
    }

    public void construirTorre(String nombreTorre, int fila, int columna) {
        jugador.construirTorre(nombreTorre, fila, columna);
    }

    public void construirTrampa(/*String nombreTrampa,*/ int fila, int columna) {
        jugador.construirTrampa(/*nombreTrampa,*/ fila, columna);
    }

    public Juego(Jugador jugador, Mapa mapa) {
        this.mapa = mapa;
        this.jugador = jugador;
    }

    public int mostrarVidaDelJugador() {
        return jugador.mostrarVida();
    }

    public int mostrarCreditosDelJugador(){
        return jugador.mostrarCreditos();
    }

    public int cantidadDeEnemigos() { return mapa.cantidadDeEnemigos(); }

    public int cantidadDeTorres() { return mapa.cantidadDeTorres(); }

    public void jugar() {
        while (jugador.estaVivo() && mapa.tieneEnemigos()) {
            avanzarTurno();
        }
    }

    public void avanzarTurno() {
        LOGGER.info("Avanzando Turno");
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());
        mapa.removerMuertos();
    }

    public String estadoJuego() {
        if (!(this.jugador.estaVivo())) {
            logger.info("Jugador pierde la partida");
            return "Derrota";
        } else if (this.mapa.cantidadDeEnemigos() == 0) {
            logger.info("Jugador gana la partida");
            return "Victoria";
        }
        return "En juego";
    }
    public List<Parcela> getParcelas() {
        return mapa.getParcelas();
    }

    public Jugador getJugador() { return this.jugador; }
}