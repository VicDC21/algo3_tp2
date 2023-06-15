package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.Constructor;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AraniaTest {
    @Test
    public void matarUnaAraniaOtorgaCreditosAlAzarEntre0Y10() {
        Mapa mapa = new Mapa();
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);
        Arania arania = new Arania(1, 1, 1, "Vivo", null);
        arania.recibirDanio(1);
        jugador.recibirCreditos(arania.otorgarCredito());
        assertTrue(jugador.mostrarCreditos() >= 100 && jugador.mostrarCreditos() <= 110);
    }
}
