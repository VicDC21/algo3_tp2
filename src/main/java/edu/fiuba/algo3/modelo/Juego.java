package edu.fiuba.algo3.modelo;

import java.util.Scanner;

public class Juego {
    Jugador jugador;
    Mapa mapa;
    Constructor constructor;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador(leerNombre(), 20, 100, new Constructor(mapa));
    }

    public Juego(String path) {
        MapaParser parser = new MapaParser();
        try {
            mapa = parser.parseMapa(path);
        } catch (InvalidMap e) {
            return;
        }
        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
    }

    public Juego(String pathMapa, String pathEnemigos) {
        MapaParser parserMapa = new MapaParser();
        EnemigosParser parserEnemigos = new EnemigosParser();
        try {
            mapa = parserMapa.parseMapa(pathMapa);
        } catch (InvalidMap e) {
            return;
        }
        try {
            mapa.cargarEnemigos(parserEnemigos.parseEnemigos(pathEnemigos));
        } catch (JsonDeEnemigosInvalido e) {
            return;
        }

        jugador = new Jugador("test", 20, 100, new Constructor(mapa));
    }

    public void construir(String construible, int numeroParcela) {
        jugador.construir(construible, numeroParcela);
    }

    public Juego(Jugador jugador, Mapa mapa) {
        this.mapa = mapa;
        this.jugador = jugador;
    }

    public int mostrarVidaDelJugador() {
        return jugador.mostrarVida();
    }

    public int mostrarCreditosActuales() {
        return jugador.mostrarCreditos();
    }

    public void jugar() {
        while (jugador.estaVivo() && mapa.tieneEnemigos()) {
            avanzarTurno();
        }
    }

    public void avanzarTurno() {
//        jugador.avanzarTurno();
        mapa.avanzarTurno();
    }

    public String leerNombre() {
        Scanner in = new Scanner(System.in);
        String name = "";
        while (name.length() < 6) {
            System.out.println("Ingrese un nombre de al menos 6 caracteres: ");
            name = in.nextLine();
        }
        in.close();
        return name;
    }

    public String estadoJuego() {
        if (!(this.jugador.estaVivo())) {
            return "Derrota";
        } else if (this.mapa.cantidadDeEnemigos() == 0) {
            return "Victoria";
        }
        return "En juego";
    }
}