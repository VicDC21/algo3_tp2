package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CreditoInsuficiente;
import edu.fiuba.algo3.modelo.ParcelaNoConstruible;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void unidadesEnemigasSonDaniadasDeAcuerdoAlDanioRecibido() {
        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = new Pasarela(1,2, mapa, null);
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);
        pasarela.recibirEnemigo(hormiga);
        ArrayList<Parcela> pasarelasEnRango = new ArrayList<Parcela>();
        pasarelasEnRango.add(pasarela);
        when(mapa.obtenerParcelasEnArea(1, 1, 2)).thenReturn(pasarelasEnRango);
        Torre torre = new Torre(0, 0, 2, 1);
        Tierra tierra = new Tierra(1, 1, mapa);
        tierra.construir(torre);
        tierra.avanzarTurno();
        assertFalse(hormiga.estaMuerto());
        tierra.avanzarTurno();
        assertTrue(hormiga.estaMuerto());
    }

    @Test
    public void unidadesEnemigasSoloSeMuevanPorLaParcelaAutorizada(){
        Mapa mapa = new Mapa();
        Pasarela pasarela = new Pasarela(1,2, mapa, null);
        Tierra tierra = new Tierra(2, 2, mapa);
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);
        
        pasarela.recibirEnemigo(hormiga);
       // tierra.recibirEnemigo(hormiga);

        assertEquals(true,pasarela.tieneEnemigos());
        assertEquals(false,tierra.tieneEnemigos());
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
