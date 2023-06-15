package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parsers.EnemigosParser;
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
    public void losEnemigosSeParseanCorrectamenteConUnJSONValido() {
        EnemigosParser parser = new EnemigosParser();
        ArrayList<List<Enemigo>> enemigosParseados = parser.parseEnemigos("src/main/resources/enemigos.json");
        assertEquals(Hormiga.class, (enemigosParseados.get(0)).get(0).getClass());
        assertEquals(Hormiga.class, (enemigosParseados.get(1)).get(0).getClass());
        assertEquals(Arania.class, (enemigosParseados.get(1)).get(1).getClass());
    }

    @Test
    public void simulaYVerificaQueElJugadorGanaLaPartida() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json");

        juego.construir("torrePlateada", 60);
        juego.construir("torrePlateada", 75);
        juego.construir("torrePlateada", 90);

        juego.jugar();

        assertEquals("Victoria", juego.estadoJuego());
    }
}