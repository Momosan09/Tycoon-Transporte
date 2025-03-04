
package mapa;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import jugador.HabilidadesJugador;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Recursos;
import utiles.Render;
import utiles.Tiempo;
import utiles.Util;

public abstract class PuntoDePartida extends ObjetoDelMapa{


	protected String rutaTexturaOrig;

	
	
	
	public PuntoDePartida(String rutaTextura, float x, float y,  String nombre) {
		super(rutaTextura, x, y, nombre, Util.generarAleatorio(0, 100));
		this.rutaTexturaOrig = rutaTextura;
		
	}
	
	
	@Override
	public void onClick() {
		super.onClick();
		System.out.println("Tile ocupada por: " + nombre);
		System.out.println("Produccion = " + cons_prod);
		System.out.println(GlobalesJuego.habilidadEnUso);
		if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
			setAsStart();
		}
	}
	
	
	/**
	 * Setea la tile como el inicio del pathfinding
	 */
	public void setAsStart() {
		System.out.println("que cool");
		this.inicio = true;
		sprite = new Sprite(new Texture(Recursos.RUTA_INICIO));
		sprite.setX(x);
		sprite.setY(y);
	}
	
	/**
	 * devuelve el sprite normal a la tile
	 */
	@Override
	public void volverALaNormalidad() {
		this.inicio = false;
		sprite = new Sprite(new Texture(rutaTexturaOrig));
		sprite.setX(x);
		sprite.setY(y);
	}
	


}

