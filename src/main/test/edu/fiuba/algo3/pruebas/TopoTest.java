package edu.fiuba.algo3.pruebas;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TopoTest {

    @Test
    public void toposNoSonAtacadosPorTorres() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosTopos.json");

        juego.construirTorre("torrePlateada", 1, 3);
        juego.construirTorre("torrePlateada", 2, 3);
        juego.construirTorre("torrePlateada", 3, 3);
        juego.construirTorre("torrePlateada", 4, 3);

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(4, juego.cantidadDeTorres());
        assertEquals(3, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(3, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(3, juego.cantidadDeEnemigos());
    }

    @Test
    public void toposHacenDaniosCorrespondientesASusTurnosDeLlegada() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosTopos.json");

        for (int i = 0; i < 12; i++) {
            juego.avanzarTurno();
        }

        juego.avanzarTurno();
        assertEquals(3, juego.cantidadDeEnemigos());
        assertEquals(15, juego.mostrarVidaDelJugador());

        juego.avanzarTurno();
        assertEquals(2, juego.cantidadDeEnemigos());
        assertEquals(13, juego.mostrarVidaDelJugador());

        juego.avanzarTurno();
        assertEquals(1, juego.cantidadDeEnemigos());
        assertEquals(8, juego.mostrarVidaDelJugador());
    }
}
