package edu.fiuba.algo3.modelo.parcelas;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.SuscriptorTurno;
import edu.fiuba.algo3.modelo.JavaFX.MapaPane;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.Trampa;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Parcela {
    protected int fila;
    protected int columna;
    protected List<Enemigo> enemigos = new ArrayList<>();
    protected List<Parcela> listaParcelas;
    private ObjectProperty<Enemigo> enemyProperty = new SimpleObjectProperty<>();


    Mapa mapa;

    public Parcela(int fila, int columna, Mapa mapa)
    {
        this.fila = fila;
        this.columna = columna;
        this.mapa = mapa;
    }

    public abstract boolean tieneEnemigos();

    public abstract void avanzarTurno();

    public abstract void construirTorre(Torre torre);

    public abstract void construirTrampa(Trampa trampa);

    public ObjectProperty<Enemigo> enemyProperty() {
        return enemyProperty;
    }
    
    public void setEnemy(Enemigo enemy) {
        enemyProperty.set(enemy);
    }
    
    public Enemigo getEnemy() {
        return enemyProperty.get();
    }

    public int devolverCantidadDeCreditosGeneradosEnTurno() {
        return 0;
    }

    public boolean enRadioDe(int fila, int columna, int radio) {
        return (fila - radio <= this.fila) && (this.fila <= fila + radio) && (columna - radio <= this.columna) && (this.columna <= columna + radio);
    }

    public abstract void recibirDanio(int danio);

    public abstract int cantidadDeEnemigos();

    public void actualizarEnemigos() {}

    public void suscribirTurno(SuscriptorTurno suscriptor) { mapa.suscribirTurno(suscriptor); }

    public void desuscribirTurno(SuscriptorTurno suscriptor) { mapa.desuscribirTurno(suscriptor); }

    public void destruirConstuccion() {}

    public void removerMuertos() {}

    public List<Enemigo> devolverEnemigos() {
        return enemigos;
    }
}
