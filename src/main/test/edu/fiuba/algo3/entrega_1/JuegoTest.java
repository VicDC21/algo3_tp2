package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.CreditoInsuficiente;
import edu.fiuba.algo3.modelo.ParcelaNoConstruible;
import edu.fiuba.algo3.modelo.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void torreNoPuedeAtacarHastaTerminarDeConstruirse() {
        int cantidadDeTurnosParaConstruirse = 2;
        int cantidadDeDanio = 3;
        int rango = 3;

        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = mock(Pasarela.class);

        when(mapa.obtenerParcelasEnArea(0, 0, rango)).thenReturn(Collections.singletonList(pasarela));

        Torre torre = new Torre(10, cantidadDeTurnosParaConstruirse, rango, cantidadDeDanio);

        torre.avanzarTurno(mapa, 0, 0);
        torre.avanzarTurno(mapa, 0, 0);

        verify(pasarela, times(0)).recibirDanio(cantidadDeDanio);
    }

    @Test
    public void torrePuedeAtacarAlTerminarDeConstruirse() {
        int cantidadDeTurnosParaConstruirse = 2;
        int cantidadDeDanio = 3;
        int rango = 3;

        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = mock(Pasarela.class);

        when(mapa.obtenerParcelasEnArea(0, 0, rango)).thenReturn(Collections.singletonList(pasarela));
        when(pasarela.tieneEnemigos()).thenReturn(true);

        Torre torre = new Torre(10, cantidadDeTurnosParaConstruirse, rango, cantidadDeDanio);

        torre.avanzarTurno(mapa, 0, 0);
        torre.avanzarTurno(mapa, 0, 0);
        torre.avanzarTurno(mapa, 0, 0);

        verify(pasarela, times(1)).recibirDanio(cantidadDeDanio);
    }


    @Test
    public void antesDeConstruirSeVerificaTenerCreditoSuficiente() {
        int cantidadDeCreditosInsuficientes = 5, cantidadDeCreditosSuficientes = 100;
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, cantidadDeCreditosInsuficientes, constructor);
        assertThrows(CreditoInsuficiente.class, () -> jugador.construir("torreBlanca", 3));
        jugador.recibirCreditos(cantidadDeCreditosSuficientes);
        assertDoesNotThrow(() -> jugador.construir("torreBlanca", 3));
    }

    @Test
    public void defensasSoloPuedenSerConstruidasEnTierra() {

        Mapa mapa = mock(Mapa.class);
        Torre torre = mock(Torre.class);

        Pasarela pasarela = new Pasarela(0, 0, mapa);
        assertThrows(ParcelaNoConstruible.class, () -> pasarela.construir(torre));

        Rocoso rocoso = new Rocoso(0, 0, mapa);
        assertThrows(ParcelaNoConstruible.class, () -> rocoso.construir(torre));

        Tierra tierra = new Tierra(0, 0, mapa);
        assertDoesNotThrow(() -> tierra.construir(torre));
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
        Pasarela pasarela = new Pasarela(1, 2, mapa, null);
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
    public void unidadesEnemigasSoloSeMuevanPorLaParcelaAutorizada() {
        Mapa mapa = new Mapa();
        Pasarela pasarela = new Pasarela(1, 2, mapa, null);
        Tierra tierra = new Tierra(2, 2, mapa);
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);

        pasarela.recibirEnemigo(hormiga);
        // tierra.recibirEnemigo(hormiga);

        assertTrue(pasarela.tieneEnemigos());
        assertFalse(tierra.tieneEnemigos());
    }

    @Test
    public void elJugadorGanaSiEstaVivoYEliminoATodosLosEnemigos() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Juego juego = new Juego(jugador, mapa);

        jugador.construir("torreBlanca", 1);

        assertEquals(1, mapa.cantidadDeEnemigos());
        juego.avanzarTurno();
        juego.avanzarTurno();
        assertEquals(0, mapa.cantidadDeEnemigos());
        assertEquals("Victoria", juego.estadoJuego());
    }

    @Test
    public void elJugadorPierdeCuandoSeMuere() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);

        Jugador jugador = new Jugador("Prueba", 0, 100, constructor);

        Juego juego = new Juego(jugador, mapa);

        assertEquals("Derrota", juego.estadoJuego());
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
    public void losEnemigosSeParseanCorrectamenteConUnJSONValido() {
        EnemigosParser parser = new EnemigosParser();
        ArrayList<List<Enemigo>> enemigosParseados = parser.parseEnemigos("src/main/resources/enemigos.json");
        assertEquals(Hormiga.class, (enemigosParseados.get(0)).get(0).getClass());
        assertEquals(Hormiga.class, (enemigosParseados.get(1)).get(0).getClass());
        assertEquals(Arania.class, (enemigosParseados.get(1)).get(1).getClass());
    }

    @Test
    public void intentarParsearUnJSONDeEnemigosConUnErrorDeSintaxisLanzaUnaExcepcion() {
        EnemigosParser parser = new EnemigosParser();
        assertThrows(JSONException.class, () -> parser.parseEnemigos("src/main/resources/enemigosErrorSintaxis.json"));
    }

    @Test
    public void intentarParsearUnJSONDeEnemigosSinElFormatoAdecuadoLanzaUnaExcepcion() {
        EnemigosParser parser = new EnemigosParser();
        assertThrows(JsonDeEnemigosInvalido.class, () -> parser.parseEnemigos("src/main/resources/enemigosFormatoInvalido.json"));
    }

    @Test
    public void testMapa() throws IOException {
        String path = new File("src/main/resources/mapa.json").getAbsolutePath();
        Juego juego = new Juego(path);
        assertEquals(20, juego.mostrarVidaDelJugador());
    }

    @Test
    public void intentarParsearUnJSONDeMapaSinElFormatoAdecuadoLanzaUnaExcepcion() {
        MapaParser parser = new MapaParser();
        assertThrows(InvalidMap.class, () -> parser.parseMapa("src/main/resources/mapaFormatoInvalido.json"));
    }

    @Test
    public void intentarParsearUnJSONDeMapaConUnErrorDeSintaxisLanzaUnaExcepcion() {
        MapaParser parser = new MapaParser();
        assertThrows(InvalidMap.class, () -> parser.parseMapa("src/main/resources/mapaErrorSintaxis.json"));
    }

    @Test
    public void elMapaSeParseaCorrectamenteConUnJSONValido() {
        MapaParser parser = new MapaParser();
        Mapa mapaParseado;
        JSONObject jsonDelMapa;
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";

        try {
            jsonDelMapa = new JSONObject(FileUtils.readFileToString(new File(pathAlJsonDelMapa)));
        } catch (IOException | JSONException e) {
            fail();
            return;
        }

        try {
            mapaParseado = parser.parseMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            fail();
            return;
        }

        for (int i = 1; i < 16; i++) {
            for (int j = 0; j < 15; j++) {      // Las filas comienzan a contarse desde el 1, pero las columnas, contenidas en un array, desde el 0
                String tipoDeParcelaEnPosicionEnJson = jsonDelMapa
                        .getJSONObject("Mapa")
                        .getJSONArray(Integer.toString(i)).getString(j);
                String tipoDeParcelaEnPosicionEnMapa = mapaParseado.obtenerParcela(i, j)
                        .getClass()
                        .getName()
                        .split("\\.")[4];
                if (tipoDeParcelaEnPosicionEnMapa.equals("PasarelaLlegada") || tipoDeParcelaEnPosicionEnMapa.equals("PasarelaSalida")) {
                    tipoDeParcelaEnPosicionEnMapa = "Pasarela";
                }
                assertEquals(tipoDeParcelaEnPosicionEnJson, tipoDeParcelaEnPosicionEnMapa);
            }
        }
    }

    @Test
    public void simulaYVerificaQueElJugadorGanaPartida() {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json");

        //juego.construir("torreBlanca", 4);
        //juego.construir("torreBlanca", 6);

        for (int i = 0; i < 40; i++) {
            juego.avanzarTurno();
        }

        assertEquals(0, juego.mostrarVidaDelJugador());
        assertEquals("En juego", juego.estadoJuego());
    }
}