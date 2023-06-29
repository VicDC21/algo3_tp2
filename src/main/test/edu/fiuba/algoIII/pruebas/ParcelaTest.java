package edu.fiuba.algoIII.pruebas;

import edu.fiuba.algoIII.modelo.*;
import edu.fiuba.algoIII.modelo.defensas.Torre;
import edu.fiuba.algoIII.modelo.excepciones.CreditoInsuficiente;
import edu.fiuba.algoIII.modelo.excepciones.ParcelaNoConstruible;
import edu.fiuba.algoIII.modelo.parcelas.Pasarela;
import edu.fiuba.algoIII.modelo.parcelas.Rocoso;
import edu.fiuba.algoIII.modelo.parcelas.Tierra;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ParcelaTest {
    @Test
    public void antesDeConstruirSeVerificaTenerCreditoSuficiente() {
        int cantidadDeCreditosInsuficientes = 1, cantidadDeCreditosSuficientes = 100;
        Mapa mapa = mock(Mapa.class);
        Constructor constructor = new Constructor(mapa);
        Jugador jugador = new Jugador("test", 20, 1, constructor);
        assertThrows(CreditoInsuficiente.class, () -> constructor.construirTorre("torreBlanca", cantidadDeCreditosInsuficientes, 0, 0));
        assertDoesNotThrow(() -> constructor.construirTorre("torreBlanca", cantidadDeCreditosSuficientes, 0, 0));
    }

    @Test
    public void defensasSoloPuedenSerConstruidasEnTierra() {

        Mapa mapa = mock(Mapa.class);
        Torre torre = mock(Torre.class);

        Pasarela pasarela = new Pasarela(0, 0, mapa);
        assertThrows(ParcelaNoConstruible.class, () -> pasarela.construirTorre(torre));

        Rocoso rocoso = new Rocoso(0, 0, mapa);
        assertThrows(ParcelaNoConstruible.class, () -> rocoso.construirTorre(torre));

        Tierra tierra = new Tierra(0, 0, mapa);
        assertDoesNotThrow(() -> tierra.construirTorre(torre));
    }
}
