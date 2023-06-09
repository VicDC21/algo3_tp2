package edu.fiuba.algo3.modelo;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MapaParser {

    static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
    static Map<String, Parcela> classMap2 = new HashMap<String, Parcela>();
    static {
        classMap.put("Pasarela", Pasarela.class);
        classMap.put("Rocoso", Rocoso.class);
        classMap.put("Tierra", Tierra.class);
    }

    boolean isFirst = true;
    int lastFila;
    int lastColumna;

    public Mapa parseMapa(String path) throws InvalidMap {
        Mapa mapa = new Mapa();

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(FileUtils.readFileToString(new File(path)));
        } catch (IOException | JSONException e) {
            throw new InvalidMap();
        }
        JSONObject mapaJson = jsonObject.getJSONObject("Mapa");

        List<Integer> intList = mapaJson.keySet().stream().map(Integer::parseInt).collect(Collectors.toList());
        Collections.sort(intList);

        List<Parcela> lista = new ArrayList<>();
        for (Integer num : intList) {
            try {
                lista.addAll(parseParcelas(num, mapaJson.getJSONArray(num.toString()), mapa));
            } catch (RuntimeException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                return null;
            }
        }
        setPasarelaDeLlegada(lista, mapa);
        linkPasarelas(lista);
        mapa.agregarParcelas(lista);
        return mapa;
    }

    private void linkPasarelas(List<Parcela> lista) {
        int prevIndex = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) instanceof Pasarela) {
                if (prevIndex > 0) {
                    ((Pasarela) lista.get(prevIndex)).setPasarelaSiguiente((Pasarela) lista.get(i));
                }
                prevIndex = i;
            }
        }
    }

    private void setPasarelaDeLlegada(List<Parcela> lista, Mapa mapa) {
        for (int i = (lista.size() - 1); i >= 0; i--) {
            if (lista.get(i) instanceof Pasarela) {
                lista.set(i, new PasarelaLlegada(lastFila, lastColumna, mapa)); // PONER DE LLEGADA
                break;
            }
        }
    }

    private List<Parcela> parseParcelas(int key, JSONArray lista, Mapa mapa) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Parcela> parcelas = new ArrayList<>();
        for (int i = 0; i < lista.length(); i++) {
            Class<?> parcela = classMap.get(lista.getString(i));
            parcelas.add((Parcela) parcela.getDeclaredConstructor(int.class, int.class, Mapa.class).newInstance(key, i, mapa));
            if (parcelas.get(i) instanceof Pasarela) {
                if (isFirst) {
                    isFirst = false;
                    parcelas.set(i, new PasarelaSalida(key, i, mapa));
                } else {
                    lastColumna = i;
                    lastFila = key;
                }
            }
        }
        return parcelas;
    }

}

