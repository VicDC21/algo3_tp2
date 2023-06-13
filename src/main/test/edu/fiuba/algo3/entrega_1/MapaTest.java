package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.InvalidMap;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.MapaParser;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MapaTest {
    @Test
    public void testMapa() throws IOException {
        String path = new File("src/main/resources/mapa.json").getAbsolutePath();
        Juego juego = new Juego(path);
        assertEquals(20, juego.mostrarVidaDelJugador());
    }

    @Test
    public void elMapaSeParseaCorrectamenteConUnJSONValido() {
        MapaParser parser = new MapaParser();
        Mapa mapaParseado;
        JSONObject jsonDelMapa;
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";

        try {
            jsonDelMapa = new JSONObject(FileUtils.readFileToString(new File(pathAlJsonDelMapa)));
        } catch (IOException | JSONException e) {
            fail();
            return;
        }

        try {
            mapaParseado = parser.parseMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            fail();
            return;
        }

        for (int i = 1; i < 16; i++) {
            for (int j = 0; j < 15; j++) {      // Las filas comienzan a contarse desde el 1, pero las columnas, contenidas en un array, desde el 0
                String tipoDeParcelaEnPosicionEnJson = jsonDelMapa
                        .getJSONObject("Mapa")
                        .getJSONArray(Integer.toString(i)).getString(j);
                String tipoDeParcelaEnPosicionEnMapa = mapaParseado.obtenerParcela(i, j)
                        .getClass()
                        .getName()
                        .split("\\.")[4];
                if (tipoDeParcelaEnPosicionEnMapa.equals("PasarelaLlegada") || tipoDeParcelaEnPosicionEnMapa.equals("PasarelaSalida")) {
                    tipoDeParcelaEnPosicionEnMapa = "Pasarela";
                }
                assertEquals(tipoDeParcelaEnPosicionEnJson, tipoDeParcelaEnPosicionEnMapa);
            }
        }
    }
}
