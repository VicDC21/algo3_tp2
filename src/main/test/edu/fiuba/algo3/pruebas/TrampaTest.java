package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrampaTest {

    @Test
    public void trampaArenosaRalentilizaEnemigos() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosUnaArania.json");

        //juego.construirTrampa("trampaArenosa", 3, 1);
        //juego.construirTrampa("trampaArenosa", 4, 1);
        //juego.construirTrampa("trampaArenosa", 5, 1);

        assertEquals(1, juego.cantidadDeEnemigos());

        for (int i = 0; i < 11 ; i++) {
            juego.avanzarTurno();
        }
        assertEquals(1, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(0, juego.cantidadDeEnemigos());
    }
}
