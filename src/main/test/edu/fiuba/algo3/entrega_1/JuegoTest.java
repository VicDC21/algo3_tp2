package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CreditoInsuficiente;
import edu.fiuba.algo3.modelo.ParcelaNoConstruible;
import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
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

    @Test
    public void elJugadorGanaSiEstaVivoYEliminoATodosLosEnemigos() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Juego juego = new Juego(jugador, mapa)

        assertEquals(1, mapa.cantidadDeEnemigos());

        jugador.construir("torreBlanca", 1);
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        assertEquals(0, mapa.cantidadDeEnemigos());
        assertEquals("Victoria", juego.getEstado());
    }

    @Test
    public void elJugadorGanaSiLosEnemigosNoPuedenMatarlo() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Juego juego = new Juego(jugador, mapa)

        assertEquals(1, mapa.cantidadDeEnemigos());

        jugador.construir("torreBlanca", 1);
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        assertEquals(0, mapa.cantidadDeEnemigos());
        assertEquals("Victoria", juego.getEstado());
    }

    @Test
    public void elJugadorPierdeCuandoSeMuere() {

    }

    @Test
    public void matarUnaHormigaOtorga1Credito() {
        int tierra = 0;
        Hormiga.hormigasMuertas = 0;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        jugador.construir("torreBlanca", tierra);
        mapa.avanzarTurno();
        mapa.avanzarTurno();
        jugador.recibirCreditos(mapa.devolverCantidadDeCreditosGeneradosEnTurno());
        assertEquals(101, jugador.mostrarCreditos());
    }
    @Test
    public void matarUnaHormigaOtorga1CreditoSiMurieron10Hormigas() {
        int tierra = 0;
        Hormiga.hormigasMuertas = 0;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        for (int i = 0; i < 10; i++) {
            Enemigo hormiga = new Hormiga(1, 1, 1, "Vivo", null);
            hormiga.recibirDanio(1);
        }
        jugador.construir("torreBlanca", tierra);
        mapa.avanzarTurno();
        mapa.avanzarTurno();
        jugador.recibirCreditos(mapa.devolverCantidadDeCreditosGeneradosEnTurno());
        assertEquals(101, jugador.mostrarCreditos());
    }

    @Test
    public void matarUnaHormigaOtorga2CreditosSiMurieronMasDe10Hormigas() {
        int tierra = 0;
        Hormiga.hormigasMuertas = 0;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        for (int i = 0; i < 11; i++) {
            Enemigo hormiga = new Hormiga(1, 1, 1, "Vivo", null);
            hormiga.recibirDanio(1);
        }
        jugador.construir("torreBlanca", tierra);
        mapa.avanzarTurno();
        mapa.avanzarTurno();
        jugador.recibirCreditos(mapa.devolverCantidadDeCreditosGeneradosEnTurno());
        assertEquals(102, jugador.mostrarCreditos());
    }

    @Test
    public void matarUnaAraniaOtorgaCreditosAlAzarEntre0Y10() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Arania arania = new Arania(1, 1, 1, "Vivo", null);
        arania.recibirDanio(1);
        jugador.recibirCreditos(arania.otorgarCredito());
        assertTrue(jugador.mostrarCreditos() >= 100 && jugador.mostrarCreditos() <= 110);
    }

    @Test
    public void testMapa() throws IOException {
        String path = new File("src/main/resources/mapa.json").getAbsolutePath();
        Juego juego = new Juego (path);
        assertEquals(20, juego.mostrarVidaDelJugador());
    }
}