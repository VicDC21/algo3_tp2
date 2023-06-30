package edu.fiuba.algoIII.modelo.parsers;

import edu.fiuba.algoIII.modelo.enemigos.*;
import edu.fiuba.algoIII.modelo.excepciones.JsonDeEnemigosInvalido;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EnemigosParser {
    private boolean esJSONValido(JSONArray jsonEnemigos) {

        for(int i = 0; i < jsonEnemigos.length(); i++) {
            JSONObject enemigosEnTurno = jsonEnemigos.getJSONObject(i);
            Object numeroDeTurno;
            JSONObject cantidadPorEnemigo;
            Object cantidadHormigas;
            Object cantidadAranias;
            Object cantidadTopos;
            Object cantidadLechuzas;

            try {
                cantidadPorEnemigo = enemigosEnTurno.getJSONObject("enemigos");
                numeroDeTurno = enemigosEnTurno.get("turno");
                cantidadHormigas = cantidadPorEnemigo.get("hormiga");
                cantidadAranias = cantidadPorEnemigo.get("arana");
                cantidadTopos = cantidadPorEnemigo.get("topo");
                cantidadLechuzas = cantidadPorEnemigo.get("lechuza");
            } catch (JSONException exception) {
                return false;
            }

            if (enemigosEnTurno.isEmpty() || cantidadPorEnemigo.isEmpty()) {
                return false;
            }

            if (!(numeroDeTurno instanceof Integer) || !(cantidadHormigas instanceof Integer) ||
                    !(cantidadAranias instanceof Integer) || !(cantidadTopos instanceof Integer) ||
                    !(cantidadLechuzas instanceof Integer)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<List<Enemigo>> parseEnemigos(String path) {
        ArrayList<List<Enemigo>> enemigosParseados = new ArrayList<>();
        JSONArray jsonEnemigos;
        try {
            jsonEnemigos = new JSONArray(FileUtils.readFileToString(new File(path)));
        } catch (IOException | JSONException e) {
            throw new JsonDeEnemigosInvalido();
        }
        for (int i = 1; i <= jsonEnemigos.length(); i++) {
            enemigosParseados.add(parseEnemigosPorTurno(i, jsonEnemigos));
        }
        return enemigosParseados;
    }

    public ArrayList<Enemigo> parseEnemigosPorTurno(int turno, JSONArray jsonEnemigos) {
        ArrayList<Enemigo> enemigosTotales = new ArrayList<>();
        if (this.esJSONValido(jsonEnemigos)) {
            for (int i = 0; i < jsonEnemigos.length(); i++) {
                JSONObject enemigos = jsonEnemigos.getJSONObject(i);
                try {
                    if (enemigos.get("turno").equals(turno)) {
                        int cantidadHormigas = (int) enemigos.getJSONObject("enemigos").get("hormiga");
                        for (int j = 0; j < cantidadHormigas; j++) {
                            enemigosTotales.add(new Hormiga());
                        }

                        int cantidadAranas = (int) enemigos.getJSONObject("enemigos").get("arana");
                        for (int j = 0; j < cantidadAranas; j++) {
                            enemigosTotales.add(new Arania());
                        }

                        int cantidadTopos = (int) enemigos.getJSONObject("enemigos").get("topo");
                        for (int j = 0; j < cantidadTopos; j++) {
                            enemigosTotales.add(new Topo());
                        }

                        int cantidadLechuzas = (int) enemigos.getJSONObject("enemigos").get("lechuza");
                        for (int j = 0; j < cantidadLechuzas; j++) {
                            enemigosTotales.add(new Lechuza());
                        }
                    }
                } catch (JSONException e) {
                    throw new JsonDeEnemigosInvalido();
                }
            }
            return enemigosTotales;
        } else {
            throw new JsonDeEnemigosInvalido();
        }
    }
}
