package edu.fiuba.algoIII.modelo.parsers;

import edu.fiuba.algoIII.modelo.*;
import edu.fiuba.algoIII.modelo.excepciones.InvalidMap;
import edu.fiuba.algoIII.modelo.parcelas.*;
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

    boolean isFirst = true;
    int lastFila, lastColumna;

    public Mapa parseMapa(String path) throws InvalidMap {
        Mapa mapa = new Mapa();
        JSONObject jsonObject;
        try {
            jsonObject = (new JSONObject(FileUtils.readFileToString(new File(path)))).getJSONObject("Mapa");
        } catch (IOException | JSONException e) {
            throw new InvalidMap();
        }

        List<Integer> intList = jsonObject.keySet()
                                .stream()
                                .map(Integer::parseInt)
                                .sorted()
                                .collect(Collectors.toList());

        List<Parcela> lista = new ArrayList<>();
        for (Integer num : intList) {
            try {
                lista.addAll(parseParcelas(num, jsonObject.getJSONArray(num.toString()), mapa));
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
                PasarelaLlegada pasarelaLlegada = new PasarelaLlegada(lastFila, lastColumna, mapa);
                lista.set(i, pasarelaLlegada);
                mapa.agregarPasarelaLlegada(pasarelaLlegada);
                break;
            }
        }
    }

    private List<Parcela> parseParcelas(int key, JSONArray lista, Mapa mapa) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<Parcela> parcelas = new ArrayList<>();
        for (int i = 0; i < lista.length(); i++) {
            switch (lista.getString(i)) {
                case "Rocoso":
                    parcelas.add(new Rocoso(key, i, mapa));
                    break;
                case "Tierra":
                    parcelas.add(new Tierra(key, i, mapa));
                    break;
                case "Pasarela": {
                    if (!isFirst)
                        parcelas.add(new Pasarela(key, i, mapa));
                    else {
                        isFirst = false;
                        parcelas.add(new PasarelaSalida(key, i, mapa));
                    }
                    lastColumna = i;
                    lastFila = key;
                    break;
                }
            }
        }
        return parcelas;
    }

}

