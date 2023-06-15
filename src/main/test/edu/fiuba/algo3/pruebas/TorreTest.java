package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TorreTest {
    @Test
    public void torreNoPuedeAtacarHastaTerminarDeConstruirse() {
        int cantidadDeTurnosParaConstruirse = 2;
        int cantidadDeDanio = 3;
        int rango = 3;

        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = mock(Pasarela.class);

        when(mapa.obtenerParcelasEnArea(any(int.class), any(int.class), any(int.class))).thenReturn(Collections.singletonList(pasarela));

        Torre torre = new Torre(10, cantidadDeTurnosParaConstruirse, rango, cantidadDeDanio);

        for (int i = 0; i < cantidadDeTurnosParaConstruirse; i++) {
            torre.avanzarTurno(mapa, 0, 0);
        }

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

        for (int i = 0; i < cantidadDeTurnosParaConstruirse + 1; i++) {
            torre.avanzarTurno(mapa, 0, 0);
        }

        verify(pasarela, times(1)).recibirDanio(cantidadDeDanio);
    }

    @Test
    public void defensasAtacanEnRangoEsperado() throws InvalidMap {     //Hay que modificar este test.
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosUno.json");

        juego.construir("torrePlateada", 1, 3);

        assertEquals(1, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(0, juego.cantidadDeEnemigos());
    }

    @Test
    public void defensasNoAtacanFueraRangoEsperado() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosUno.json");

        juego.construir("torrePlateada", 12, 12);

        assertEquals(1, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(1, juego.cantidadDeEnemigos());
    }
}
