package edu.fiuba.algo3.pruebas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.excepciones.InvalidMap;
import edu.fiuba.algo3.modelo.parsers.MapaParser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HormigaTest {

    @Test
    public void matarUnaHormigaOtorga1CreditoSiMurieron10Hormigas() {
        Hormiga.hormigasMuertas = 0;
        List<Enemigo> enemigos = new ArrayList<Enemigo>();        
        for (int i = 0; i < 10; i++) {
            Hormiga hormiga = new Hormiga();
            enemigos.add(hormiga);
        }
        ArrayList<List<Enemigo>> PackEnemigos = new ArrayList<>();
        PackEnemigos.add(enemigos);

        MapaParser parser = new MapaParser();
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";
        Mapa mapaParseado;
        try {
            mapaParseado = parser.parseMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            return ;
        }

        Constructor constructor = new Constructor(mapaParseado);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);

        jugador.construirTorre("torreBlanca", 15);
        jugador.construirTorre("torreBlanca", 110);
        jugador.construirTorre("torreBlanca", 30);
        jugador.construirTorre("torreBlanca", 108);
        jugador.construirTorre("torreBlanca", 60);
        jugador.construirTorre("torreBlanca", 90);
        jugador.construirTorre("torreBlanca", 107);
        jugador.construirTorre("torreBlanca", 112);
        mapaParseado.avanzarTurno();
        
        mapaParseado.cargarEnemigos(PackEnemigos);
        
        for (int i = 0; i < 30; i++){
            mapaParseado.avanzarTurno();
            mapaParseado.actualizarEnemigos();
            jugador.recibirCreditos(mapaParseado.creditosGeneradosEnTurno());
            mapaParseado.removerMuertos();  
        }
        
        assertEquals(30, jugador.mostrarCreditos());
        assertFalse(mapaParseado.tieneEnemigos()); //muestra que todos murieron
        assertEquals(10, jugador.mostrarVida()); //  y ninguno llego al jugador
    }

    @Test
    public void matarUnaHormigaOtorga2CreditosSiMurieronMasDe10Hormigas() {
        Hormiga.hormigasMuertas = 0;
        List<Enemigo> enemigos = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            Hormiga hormiga = new Hormiga();
            enemigos.add(hormiga);
        }
        ArrayList<List<Enemigo>> PackEnemigos = new ArrayList<>();
        PackEnemigos.add(enemigos);

        MapaParser parser = new MapaParser();
        String pathAlJsonDelMapa = "src/main/resources/mapa.json";
        Mapa mapaParseado;
        try {
            mapaParseado = parser.parseMapa(pathAlJsonDelMapa);
        } catch (InvalidMap e) {
            return;
        }

        Constructor constructor = new Constructor(mapaParseado);
        Jugador jugador = new Jugador("Prueba", 10, 100, constructor);

        jugador.construirTorre("torreBlanca", 15);
        jugador.construirTorre("torreBlanca", 110);
        jugador.construirTorre("torreBlanca", 30);
        jugador.construirTorre("torreBlanca", 108);
        jugador.construirTorre("torreBlanca", 60);
        jugador.construirTorre("torreBlanca", 90); //Puse torres de sobra se pueden sacar
        jugador.construirTorre("torreBlanca", 107);
        jugador.construirTorre("torreBlanca", 112);
        mapaParseado.avanzarTurno();
        
        mapaParseado.cargarEnemigos(PackEnemigos);
        
        for (int i = 0; i <11; i++){  // Puse ciclos de mas 
            mapaParseado.avanzarTurno();
            mapaParseado.actualizarEnemigos();
            jugador.recibirCreditos(mapaParseado.creditosGeneradosEnTurno());
            mapaParseado.removerMuertos();  
        }
        assertEquals(32, jugador.mostrarCreditos());
    }
}
