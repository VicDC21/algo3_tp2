package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
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
        assertThrows(CreditoInsuficiente.class, () -> constructor.construir("torreBlanca", cantidadDeCreditosInsuficientes, 0));
        assertDoesNotThrow(() -> constructor.construir("torreBlanca", cantidadDeCreditosSuficientes, 0));
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
