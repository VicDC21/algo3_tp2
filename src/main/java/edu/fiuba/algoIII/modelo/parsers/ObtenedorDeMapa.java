package edu.fiuba.algoIII.modelo.parsers;

import edu.fiuba.algoIII.modelo.Mapa;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;

public class ObtenedorDeMapa {
    MapaParser mapaParser = new MapaParser();

    public Mapa obtenerMapa(String path) throws InvalidMap {
        return mapaParser.parseMapa(path);
    }
}
