package edu.fiuba.algo3.pruebas;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.enemigos.Lechuza;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parsers.ObtenedorDeMapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LechuzaTest {

    @Test
    public void seMuevePorDondeDebeCuandoTieneMasDeLaMitadDeLaVida() {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        Mapa mapa;
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";

        try {
            mapa = obtenedorDeMapa.obtenerMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            fail();
            return;
        }

        // Ubica a la lechuza en la pasarela inicial para la prueba
        Parcela pasarelaInicial = mapa.obtenerParcela(1, 1);
        Lechuza lechuza = new Lechuza(5, 5, 0, "Vivo", pasarelaInicial);
        pasarelaInicial.recibirEnemigo(lechuza);
        pasarelaInicial.actualizarEnemigos();

        for (int i = 6; i < 12; i += 5){
            mapa.avanzarTurno();
            mapa.actualizarEnemigos();
            assertTrue(mapa.obtenerParcela(i, 1).tieneEnemigos());
        }

        for (int i = 6; i < 14; i += 5){
            mapa.avanzarTurno();
            mapa.actualizarEnemigos();
            assertTrue(mapa.obtenerParcela(11, i).tieneEnemigos());
        }

        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        assertTrue(mapa.obtenerParcela(11, 14).tieneEnemigos());
    }

    @Test
    public void seMuevePorDondeDebeCuandoTieneMenosDeLaMitadDeLaVida() {
        ObtenedorDeMapa obtenedorDeMapa = new ObtenedorDeMapa();
        Mapa mapa;
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";

        try {
            mapa = obtenedorDeMapa.obtenerMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            fail();
            return;
        }

        // Ubica a la lechuza en la pasarela inicial para la prueba
        Parcela pasarelaInicial = mapa.obtenerParcela(1, 1);
        Lechuza lechuza = new Lechuza(2, 5, 0, "Vivo", pasarelaInicial);
        pasarelaInicial.recibirEnemigo(lechuza);
        pasarelaInicial.actualizarEnemigos();

        for (int i = 6; i < 12; i += 5){
            mapa.avanzarTurno();
            mapa.actualizarEnemigos();
            assertTrue(mapa.obtenerParcela(i, i).tieneEnemigos());
        }

        mapa.avanzarTurno();
        mapa.actualizarEnemigos();
        assertTrue(mapa.obtenerParcela(11, 14).tieneEnemigos());
    }

    @Test
    public void lechuzaDestruyePrimeraTorreAlLlegarALaMeta() throws InvalidMap {
        Juego juego = new Juego("src/main/resources/mapa.json", "src/main/resources/enemigosUnaLechuza.json");

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        juego.construirTorre("torrePlateada", 1, 3);
        assertEquals(1, juego.cantidadDeTorres());
        assertEquals(1, juego.cantidadDeEnemigos());

        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.avanzarTurno();

        assertEquals(0, juego.cantidadDeEnemigos());
        assertEquals(0, juego.cantidadDeTorres());
    }
}
