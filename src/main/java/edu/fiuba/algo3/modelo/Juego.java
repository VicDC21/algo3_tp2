package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parsers.EnemigosParser;
import edu.fiuba.algo3.modelo.parsers.MapaParser;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.JavaFX.Vista;

public class Juego {
    Jugador jugador;
    Mapa mapa;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /*Deberiamos borrar
    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador(leerNombre(), 20, 100, new Constructor(mapa));
    }*/

    /*
    public Juego(String path) throws InvalidMap {
        MapaParser parser = new MapaParser();
        mapa = parser.parseMapa(path);
        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
    }*/


    public Juego(String pathMapa, String pathEnemigos) throws InvalidMap {
        MapaParser parserMapa = new MapaParser();
        EnemigosParser parserEnemigos = new EnemigosParser();
        mapa = parserMapa.parseMapa(pathMapa);
        mapa.cargarEnemigos(parserEnemigos.parseEnemigos(pathEnemigos));
        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
        mapa.setJugador(jugador);       // Esto hay que sacarlo
        logger.info("Juego Iniciado"); 
    }

    public Juego(String pathMapa, String pathEnemigos, String nombre) throws InvalidMap {
        MapaParser parserMapa = new MapaParser();
        EnemigosParser parserEnemigos = new EnemigosParser();
        mapa = parserMapa.parseMapa(pathMapa);
        mapa.cargarEnemigos(parserEnemigos.parseEnemigos(pathEnemigos));
        jugador = new Jugador(nombre, 20, 100, new Constructor(mapa));
        mapa.setJugador(jugador);    // Esto hay que sacarlo
        logger.info("Juego Iniciado");   
    }

    public void construir(String construible, int numeroParcela) {
        jugador.construir(construible, numeroParcela);
    }

    public void construir(String construible, int fila, int columna) {
        jugador.construir(construible, fila, columna);
    }

    public Juego(Jugador jugador, Mapa mapa) {
        this.mapa = mapa;
        this.jugador = jugador;
    }

    public int mostrarVidaDelJugador() {
        return jugador.mostrarVida();
    }

    public int cantidadDeEnemigos() { return mapa.cantidadDeEnemigos(); }

    public void jugar() {
        while (jugador.estaVivo() && mapa.tieneEnemigos()) {
            avanzarTurno();
        }
    }

    public void avanzarTurno() {
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());
        mapa.removerMuertos();
    }

    /*public String leerNombre() {
        Scanner in = new Scanner(System.in);
        String name = "";
        while (name.length() < 6) {
            System.out.println("Ingrese un nombre de al menos 6 caracteres: ");
            name = in.nextLine();
        }
        in.close();
        return name;
    }*/

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
    public List<Pane> getTiles(int width, int height) {
        return mapa.getTiles(width, height);
    }
}