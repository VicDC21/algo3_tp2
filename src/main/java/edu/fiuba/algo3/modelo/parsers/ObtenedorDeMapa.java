package edu.fiuba.algo3.modelo.parsers;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;

public class ObtenedorDeMapa {
    MapaParser mapaParser = new MapaParser();

    public Mapa obtenerMapa(String path) throws InvalidMap {
        return mapaParser.parseMapa(path);
    }
}
