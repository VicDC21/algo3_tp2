package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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
    public void defensasAtacanEnRangoEsperado() {     //Hay que modificar este test.
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);

        assertEquals(1, mapa.cantidadDeEnemigos());

        jugador.construir("torrePlateada", 3, 0);
        mapa.avanzarTurno();
        mapa.reset();
        mapa.avanzarTurno();
        mapa.reset();
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
}
