package edu.fiuba.algoIII.pruebas;

import edu.fiuba.algoIII.modelo.*;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.enemigos.Enemigo;
import edu.fiuba.algoIII.modelo.enemigos.Hormiga;
import edu.fiuba.algoIII.modelo.parcelas.Parcela;
import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import edu.fiuba.algoIII.modelo.parcelas.PasarelaNull;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;
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
        Pasarela pasarela = new Pasarela(1, 2, mapa, new PasarelaNull());
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);
        pasarela.recibirEnemigo(hormiga);
        ArrayList<Parcela> pasarelasEnRango = new ArrayList<>();
        pasarelasEnRango.add(pasarela);
        when(mapa.obtenerParcelasEnArea(1, 1, 2)).thenReturn(pasarelasEnRango);
        Torre torre = new Torre(0, 0, 2, 1, 1);
        Tierra tierra = new Tierra(1, 1, mapa);
        tierra.construirTorre(torre);
        tierra.avanzarTurno();
        assertFalse(hormiga.estaMuerto());
        tierra.avanzarTurno();
        assertTrue(hormiga.estaMuerto());
    }

    @Test
    public void unidadesEnemigasSoloSeMuevanPorLaParcelaAutorizada() {
        Mapa mapa = mock(Mapa.class);
        Pasarela pasarela = new Pasarela(1, 2, mapa, new PasarelaNull());
        Tierra tierra = new Tierra(2, 2, mapa);
        Enemigo hormiga = new Hormiga(2, 1, 1, "Vivo", pasarela);

        pasarela.recibirEnemigo(hormiga);
        // tierra.recibirEnemigo(hormiga);

        assertTrue(pasarela.tieneEnemigos());
        assertFalse(tierra.tieneEnemigos());
    }
}
