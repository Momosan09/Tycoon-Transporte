package vehiculos;
import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import audio.MAudio;
import enums.DireccionCalle;
import eventos.Listeners;
import mapa.PuntoDePartida;
import mapa.PuntoMuerto;
import mapa.transporte.TileTransporte;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Render;
import utiles.Tiempo;

public abstract class Vehiculo {
    protected Vector2 posicion;
    protected Sprite spr;
    protected float velocidad;
    protected int capacidadDeCarga;
    protected ArrayList<TileTransporte> camino;
    protected TileTransporte actual, siguiente;
    protected boolean volviendo = false, esperando = false, descargando = false, cargando = false;
    protected int tiempoDeEsperaCarga = 2000, tiempoEsperaDescarga = 1000;
    protected Tiempo tiempo;//No es abstracta porque el metodo static de esperar tiempo trae problemas cuando es mas de un vehiculo esperando. cada vehiculo necesita una instancia propia del tiempo
    protected float cargaTransportando = 0;
    
    public Vehiculo(float x, float y, String rutaTextura, float velocidad, int capacidadDeCarga, ArrayList<TileTransporte> camino) {
        this.posicion = new Vector2(x, y);
        this.spr = new Sprite(new Texture(rutaTextura));
        this.velocidad = velocidad;
        this.capacidadDeCarga = capacidadDeCarga;
        this.camino = new ArrayList<>(camino);
        tiempo = new Tiempo();

        this.actual = this.camino.get(0);
        this.siguiente = (this.camino.size() > 1) ? this.camino.get(1) : actual;
        setPosicion(actual.x, actual.y);
    }

    public void dibujar() {
        spr.draw(Render.batch);
    }

    public void circular(PuntoDePartida pp, PuntoMuerto pm) {
        if (esperando) {
            if (tiempo.esperarTiempo(2000)) { // Si ya paso el tiempo de espera
                esperando = false;
                velocidad = 100;
                tiempo.reset(); // Reinicia el temporizador para futuras pausas
                if(descargando) {
                    descargando = false;
                }
                
                if(cargando) {
                    MAudio.reproducirCargaAlVehiculo();
                    cargando = false;
                }
            }
            return; // Mientras espera, no se mueve
        }

        if (actual == null || siguiente == null) return;

        // Vector de direccion normalizado
        Vector2 direccion = new Vector2(siguiente.x - posicion.x, siguiente.y - posicion.y).nor();
        
        float deltaTime = Gdx.graphics.getDeltaTime();
        // Aplicar movimiento
        posicion.x += direccion.x * velocidad * deltaTime;
        posicion.y += direccion.y * velocidad * deltaTime;
        setPosicion(posicion.x, posicion.y);

        // Si estamos lo suficientemente cerca de la siguiente tile, actualizar
        if (posicion.dst(siguiente.x, siguiente.y) < 2) {
            avanzarTile(pp,pm);
        }
    }

    private void avanzarTile(PuntoDePartida pp, PuntoMuerto pm) {
        int indexActual = camino.indexOf(actual);

        // Llega al final del camino (descarga de recursos)
        if (!volviendo && indexActual >= camino.size() - 1) {
        	descargando = true;
            esperar(tiempoEsperaDescarga);
            GlobalesJuego.dinero += pm.venderALaIndustria(cargaTransportando);
            Listeners.seGanoDinero();
            cargaTransportando = 0;
            volviendo = true;
            Collections.reverse(camino); // Invierte la ruta para volver
            actual = camino.get(0);
            siguiente = (camino.size() > 1) ? camino.get(1) : actual;
            return;
        }

        //  Llega al inicio después de volver (carga de recursos)
        if (volviendo && indexActual >= camino.size() - 1) {
            cargando = true;
        	esperar(tiempoDeEsperaCarga);
        	if(pp.getCantidadAlmacenada() >= this.capacidadDeCarga) {
        	cargaTransportando = pp.pasarCargaAlVehiculo(this.capacidadDeCarga);
        	}
        	volviendo = false;
            Collections.reverse(camino); // Invierte la ruta nuevamente
            actual = camino.get(0);
            siguiente = (camino.size() > 1) ? camino.get(1) : actual;
            return;
        }

        // Avanzar normalmente en la ruta
        if (indexActual < camino.size() - 1) {
            actual = camino.get(indexActual + 1);
            siguiente = (indexActual + 2 < camino.size()) ? camino.get(indexActual + 2) : actual;
        }
    }

    private void esperar() {
        esperando = true;
        velocidad = 0;
        tiempo.esperarTiempo(2000); // Marca que debe esperar
    }
    
    private void esperar(int tiempo) {
        esperando = true;
        velocidad = 0;
        this.tiempo.esperarTiempo(tiempo); // Marca que debe esperar
    }

    private void setPosicion(float x, float y) {
        this.posicion.set(x, y);
        spr.setPosition(posicion.x, posicion.y);
    }
}
