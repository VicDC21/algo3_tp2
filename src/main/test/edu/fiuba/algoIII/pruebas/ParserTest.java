package edu.fiuba.algoIII.pruebas;

import edu.fiuba.algoIII.modelo.*;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import edu.fiuba.algoIII.modelo.excepciones.JsonDeEnemigosInvalido;
import edu.fiuba.algoIII.modelo.parsers.EnemigosParser;
import edu.fiuba.algoIII.modelo.parsers.MapaParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void intentarParsearUnJSONDeEnemigosConUnErrorDeSintaxisLanzaUnaExcepcion() {
        EnemigosParser parser = new EnemigosParser();
        assertThrows(JsonDeEnemigosInvalido.class, () -> parser.parseEnemigos("src/main/resources/enemigosErrorSintaxis.json"));
    }

    @Test
    public void intentarParsearUnJSONDeEnemigosSinElFormatoAdecuadoLanzaUnaExcepcion() {
        EnemigosParser parser = new EnemigosParser();
        assertThrows(JsonDeEnemigosInvalido.class, () -> parser.parseEnemigos("src/main/resources/enemigosFormatoInvalido.json"));
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
    public void simulaYVerificaQueElJugadorPierdeLaPartida() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigos.json");

        juego.jugar();
        assertEquals("Derrota", juego.estadoJuego());
    }
}
