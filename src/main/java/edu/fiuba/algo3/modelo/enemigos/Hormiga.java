package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Pasarela;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hormiga extends Enemigo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hormiga.class.getSimpleName());
    public static int hormigasMuertas = 0;
    private int creditos = 0;

    public Hormiga(int energia, int velocidad, int danio, String estado, Pasarela pasarelaActual) {
        super(energia, velocidad, danio, estado, pasarelaActual);
    }

    public Hormiga() {
        super(1, 1, 1, "Vivo", null);
    }

    @Override
    public void recibirDanio(int danio) {
        if (!this.estaMuerto()) {
            this.energia -= danio;
            LOGGER.info("Hormiga recibe " + danio + " de daño");
            if (this.estaMuerto()) {
                hormigasMuertas++;
                if (hormigasMuertas <= 10) {
                    this.creditos = 1;
                } else {
                    this.creditos = 2;
                }
            }
        }
    }

    @Override
    public int otorgarCredito() {
        LOGGER.info("Hormiga otorga " + creditos + " créditos");
        return this.creditos;
    }

    @Override
    public void suscribirTodo() {}

    @Override
    public void desuscribirTodo() {}
}
