package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CreditoInsuficiente;
import edu.fiuba.algo3.modelo.ParcelaNoConstruible;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    @Test
    public void jugadorEmpiezaPartidaConVidaYCreditosCorrespondientes() {
        int cantidadDeVidaEsperada = 20, cantidadDeCreditosEsperados = 100;
        String path = new File("src/main/resources/mapa.json").getAbsolutePath();
        MapaParser parser = new MapaParser();
        Mapa mapa;
        try {
            mapa = parser.parseMapa(path);
        } catch (InvalidMap e) {
            return;
        }
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", cantidadDeVidaEsperada, cantidadDeCreditosEsperados, constructor);
        Juego juego = new Juego(jugador, mapa);

        assertEquals(cantidadDeVidaEsperada, juego.mostrarVidaDelJugador());
        assertEquals(cantidadDeCreditosEsperados, juego.mostrarCreditosActuales());
    }

    @Test
    public void torreTarda2TurnosEnConstruirse() {
        int cantidadDeTurnosParaConstruirse = 2;
        Torre torre = new Torre(10, cantidadDeTurnosParaConstruirse, 3, 3);
        assertFalse(torre.estaOperativa());

        torre.avanzarTurno();
        assertFalse(torre.estaOperativa());

        torre.avanzarTurno();
        assertTrue(torre.estaOperativa());
    }

    @Test
    public void antesDeConstruirSeVerificaTenerCreditoSuficiente() {
        int cantidadDeCreditosInsuficientes = 5;
        int cantidadDeCreditosSuficientes = 100;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, cantidadDeCreditosInsuficientes, constructor);
        assertThrows(CreditoInsuficiente.class, () -> jugador.construir("torreBlanca", 3));
        jugador.recibirCreditos(cantidadDeCreditosSuficientes);
        assertDoesNotThrow(() -> jugador.construir("torreBlanca", 3));
    }

    @Test
    public void defensasSoloPuedenSerConstruidasEnTierra() {
        int parcelaTierra = 3;
        int parcelaRocosa = 13;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        assertThrows(ParcelaNoConstruible.class, () -> jugador.construir("torreBlanca", parcelaRocosa));
        assertDoesNotThrow(() -> jugador.construir("torreBlanca", parcelaTierra));
    }

    @Test
    public void defensasAtacanEnRangoEsperado() {
        int tierra = 0;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);

        assertEquals(1, mapa.cantidadDeEnemigos());

        jugador.construir("torreBlanca", tierra);
        mapa.avanzarTurno();
        mapa.avanzarTurno();
        assertEquals(0, mapa.cantidadDeEnemigos());
    }

    @Test
    public void defensasNoAtacanFueraRangoEsperado() {
        int tierra = 8;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);

        assertEquals(1, mapa.cantidadDeEnemigos());

        jugador.construir("torreBlanca", tierra);
        mapa.avanzarTurno();
        mapa.avanzarTurno();
        assertEquals(1, mapa.cantidadDeEnemigos());
    }

    @Test
    public void testMapa() throws IOException {
        String path = new File("src/main/resources/mapa.json").getAbsolutePath();
        Juego juego = new Juego (path);
        assertEquals(20, juego.mostrarVidaDelJugador());
    }
}
