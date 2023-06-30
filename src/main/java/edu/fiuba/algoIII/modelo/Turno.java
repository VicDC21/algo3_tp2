package edu.fiuba.algoIII.modelo;

import java.util.ArrayList;

public class Turno {
    private int turno = 0;
    ArrayList<SuscriptorTurno> suscriptores = new ArrayList<>();

    public void avanzarTurno() {
        turno++;
        notificar();
    }

    public void suscribir(SuscriptorTurno suscriptor) {
        suscriptores.add(suscriptor);
    }

    // problema: habria que desuscribirlos cuando se hayan muerto.
    // podemos
    public void desuscribir(SuscriptorTurno suscriptor) { suscriptores.remove(suscriptor); }

    public void notificar() {
        for (SuscriptorTurno suscriptor: suscriptores) {
            suscriptor.notificar(turno);
        }
    }
}
