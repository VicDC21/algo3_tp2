package edu.fiuba.algo3.modelo.parsers;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;

import java.util.ArrayList;
import java.util.List;

public class ObtenedorDeEnemigos {
    private EnemigosParser parserDeEnemigosEnJson = new EnemigosParser();

    public ArrayList<List<Enemigo>> obtenerEnemigos(String path) {
        return parserDeEnemigosEnJson.parseEnemigos(path);
    }
}
