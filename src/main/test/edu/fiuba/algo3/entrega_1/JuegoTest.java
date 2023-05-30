package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.exceptions.CreditoInsuficiente;
import edu.fiuba.algo3.exceptions.ParcelaNoConstruible;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    @Test
    public void jugadorEmpiezaPartidaConVidaYCreditosCorrespondientes() {
        int cantidadDeVidaEsperada = 20, cantidadDeCreditosEsperados = 100;
        Mapa mapa = new Mapa();
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
        int parcelaRocosa = 7;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        assertThrows(ParcelaNoConstruible.class, () -> jugador.construir("torreBlanca", parcelaRocosa));
        assertDoesNotThrow(() -> jugador.construir("torreBlanca", parcelaTierra));
    }
//    @Test
//    public void torreBlancaTarda1TurnoEnConstruirseYLaPlateada2() {
//        Mapa mapa = new Mapa();
//        Constructor constructor = new Constructor(mapa);
//        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
//        Juego juego = new Juego(jugador, mapa);
//
//        jugador.construir("torreBlanca", 3);
//        jugador.construir("torrePlateada", 5);
//
//        juego.avanzarTurno();
//        assertEquals(1, juego.cantidadDeTorresOperativas());
//
//        juego.avanzarTurno();
//        assertEquals(2, juego.cantidadDeTorresOperativas());
//        assertTrue(true);
//    }
}
