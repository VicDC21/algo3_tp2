package edu.fiuba.algo3.modelo;

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
            try {
                cantidadPorEnemigo = enemigosEnTurno.getJSONObject("enemigos");
                numeroDeTurno = enemigosEnTurno.get("turno");
                cantidadHormigas = cantidadPorEnemigo.get("hormiga");
                cantidadAranias = cantidadPorEnemigo.get("arana");
            } catch (JSONException exception) {
                return false;
            }

            if (enemigosEnTurno.isEmpty()) {
                return false;
            }

            if (!(numeroDeTurno instanceof Integer)) {
                return false;
            }

            if (cantidadPorEnemigo.isEmpty()) {
                return false;
            }

            if (!(cantidadHormigas instanceof Integer)) {
                return false;
            }

            if (!(cantidadAranias instanceof Integer)) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<List<Enemigo>> parseEnemigos(String path) {
        ArrayList<List<Enemigo>> enemigosParseados = new ArrayList<List<Enemigo>>();
        JSONArray jsonEnemigos;

        try {
            jsonEnemigos = new JSONArray(FileUtils.readFileToString(new File(path)));
        } catch (IOException exception) {
            return enemigosParseados;     //Si falla, devuelve un ArrayList vacío.
        }
        for (int i = 1; i <= jsonEnemigos.length(); i++) {
            enemigosParseados.add(this.parseEnemigosPorTurno(path, i, jsonEnemigos));
        }
        return enemigosParseados;
    }

    public ArrayList<Enemigo> parseEnemigosPorTurno(String path, int turno, JSONArray jsonEnemigos) {
        ArrayList<Enemigo> enemigosTotales = new ArrayList<Enemigo>();
        /*
        try {
            jsonEnemigos = new JSONArray(FileUtils.readFileToString(new File(path)));
        } catch (IOException exception) {
            return enemigosTotales;     //Si falla, devuelve un ArrayList vacío de enemigos.
        }
        */
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
                    }
                } catch (RuntimeException e) {
                    return new ArrayList<Enemigo>();    //Si falla, devuelve un ArrayList vacío de enemigos.
                }
            }

            return enemigosTotales;
        } else {
            throw new JsonDeEnemigosInvalido();
        }
    }
}
