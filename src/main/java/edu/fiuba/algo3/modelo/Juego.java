package edu.fiuba.algo3.modelo;

import java.util.Scanner;

public class Juego {
    Jugador jugador;
    Mapa mapa;
    Constructor constructor;
    String estado = "En juego";

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
        this.actualizarEstado();
    }

    private void actualizarEstado() {
        if (!jugador.estaVivo()) {
            this.estado = "Derrota";
        } else if (!mapa.tieneEnemigos()) {
            this.estado = "Victoria";
        }
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

    public String getEstado() {
        return this.estado;
    }
}