package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.parsers.MapaParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HormigaTest {
    @Test
    public void matarUnaHormigaOtorga1Credito() {
        int tierra = 0;
        Hormiga.hormigasMuertas = 0;
        MapaParser parser = new MapaParser();
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";
        Mapa mapaParseado;
        mapaParseado = parser.parseMapa(pathAlJsonDelMapa);
        Constructor constructor = new Constructor(mapaParseado);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        jugador.construir("torreBlanca", tierra);
        mapaParseado.removerMuertos();
        mapaParseado.avanzarTurno();
        mapaParseado.actualizarEnemigos();
        jugador.recibirCreditos(mapaParseado.creditosGeneradosEnTurno());
        mapaParseado.removerMuertos();
        mapaParseado.avanzarTurno();
        mapaParseado.actualizarEnemigos();
        jugador.recibirCreditos(mapaParseado.creditosGeneradosEnTurno());
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
        mapa.removerMuertos();
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());

        mapa.removerMuertos();
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());
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
        mapa.removerMuertos();
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());

        mapa.removerMuertos();
        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        jugador.recibirCreditos(mapa.creditosGeneradosEnTurno());
        assertEquals(102, jugador.mostrarCreditos());
    }
}
