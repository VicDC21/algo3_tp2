package edu.fiuba.algoIII.pruebas;

import edu.fiuba.algoIII.modelo.*;
import edu.fiuba.algoIII.modelo.enemigos.Arania;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.enemigos.Hormiga;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import edu.fiuba.algoIII.modelo.parsers.ObtenedorDeEnemigos;
import edu.fiuba.algoIII.modelo.parsers.ObtenedorDeMapa;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JuegoTest {
    String mapaPath = new File("src/main/resources/mapa.json").getAbsolutePath();

    @Test
    public void jugadorEmpiezaPartidaConVidaYCreditosCorrespondientes() {

        int cantidadDeVidaEsperada = 20, cantidadDeCreditosEsperados = 100;
        Constructor constructor = mock(Constructor.class);

        Jugador jugador = new Jugador("Prueba", cantidadDeVidaEsperada, cantidadDeCreditosEsperados, constructor);

        assertEquals(cantidadDeVidaEsperada, jugador.mostrarVida());
        assertEquals(cantidadDeCreditosEsperados, jugador.mostrarCreditos());
    }

    @Test
    public void elJugadorGanaSiEstaVivoYEliminoATodosLosEnemigos() {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        Mapa mapaParseado;
        String pathAlJsonDelMapa = mapaPath;
        try {
            mapaParseado = obtenedorDeMapa.obtenerMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            fail();
            return;
        }

        List<Enemigo> enemigos = new ArrayList<>();
        Hormiga hormiga = new Hormiga();
        enemigos.add(hormiga);
        ArrayList<List<Enemigo>> PackEnemigos = new ArrayList<>();
        PackEnemigos.add(enemigos);
        mapaParseado.cargarEnemigos(PackEnemigos);

        Constructor constructor = new Constructor(mapaParseado);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Juego juego = new Juego(jugador, mapaParseado);

        jugador.construirTorre("torreBlanca", 1, 2);

        assertEquals(1, mapaParseado.cantidadDeEnemigos());
        juego.avanzarTurno();
        juego.avanzarTurno();
        assertEquals(0, mapaParseado.cantidadDeEnemigos());
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
    public void losEnemigosSeParseanCorrectamenteConUnJSONValido() {
        ObtenedorDeEnemigos obtenedorDeEnemigos = new ObtenedorDeEnemigos();
        ArrayList<List<Enemigo>> enemigosParseados = obtenedorDeEnemigos.obtenerEnemigos("src/main/resources/enemigos.json");
        assertEquals(Hormiga.class, (enemigosParseados.get(0)).get(0).getClass());
        assertEquals(Hormiga.class, (enemigosParseados.get(1)).get(0).getClass());
        assertEquals(Arania.class, (enemigosParseados.get(1)).get(1).getClass());
    }

    @Test
    public void simulaYVerificaQueElJugadorGanaLaPartida() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosUno.json");

        juego.construirTorre("torrePlateada", 6, 0);
        juego.construirTorre("torrePlateada", 9, 7);
        juego.construirTorre("torrePlateada", 9, 0);

        juego.jugar();

        assertEquals("Victoria", juego.estadoJuego());
    }

    @Test
    public void simulaYVerificaQueElJugadorPierdeLaPartida() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json");

        //juego.construirTorre("torrePlateada", 60);
        //juego.construirTorre("torrePlateada", 75);
        //juego.construirTorre("torrePlateada", 90);

        juego.jugar();

        assertEquals("Derrota", juego.estadoJuego());
    }
}