package edu.fiuba.algoIII.modelo.parsers;

import edu.fiuba.algoIII.modelo.enemigos.Enemigo;

import java.util.ArrayList;
import java.util.List;

public class ObtenedorDeEnemigos {
    private EnemigosParser parserDeEnemigosEnJson = new EnemigosParser();

    public ArrayList<List<Enemigo>> obtenerEnemigos(String path) {
        return parserDeEnemigosEnJson.parseEnemigos(path);
    }
}
