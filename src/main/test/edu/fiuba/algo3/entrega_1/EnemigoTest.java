package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnemigoTest {
    @Test
    public void unidadesEnemigasSonDaniadasDeAcuerdoAlDanioRecibido() {
        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = new Pasarela(1, 2, mapa, null);
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);
        pasarela.recibirEnemigo(hormiga);
        ArrayList<Parcela> pasarelasEnRango = new ArrayList<>();
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
}
