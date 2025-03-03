package mapa;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import enums.Recurso;
import jugador.HabilidadesJugador;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Recursos;
import utiles.Render;

public abstract class PuntoMuerto extends ObjetoDelMapa{


	protected Recurso consume;
	protected float produccionDeDinero;
	protected String rutaTexturaOrig;
	

	/**
	 * 
	 * @param rutaTextura
	 * @param x
	 * @param y
	 * @param consumo La cantidad que consume de su recurso 
	 * @param consume El recurso que consume
	 * @param nombre
	 */
	public PuntoMuerto(String rutaTextura, float x, float y, float consumo, Recurso consume,String nombre) {
		super(rutaTextura, x, y, nombre, consumo);

		this.consume = consume;
		this.rutaTexturaOrig = rutaTextura;
	}
	
	
	@Override
	public void onClick() {
		super.onClick();
		System.out.println("Tile ocupada por: " + nombre);
		System.out.println("Consume = " + cons_prod + " de " + consume.getNombre());
		if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
			setAsGoal();
		}
	}
	
	/**
	 * Setea la tile como el inicio del pathfinding
	 */
	public void setAsGoal() {
		this.goal = true;
		sprite = new Sprite(new Texture(Recursos.RUTA_FIN));
		sprite.setX(x);
		sprite.setY(y);
	}
	
	/**
	 * devuelve el sprite normal a la tile
	 */
	@Override
	public void volverALaNormalidad() {
		this.goal = false;
		sprite = new Sprite(new Texture(rutaTexturaOrig));
		sprite.setX(x);
		sprite.setY(y);
	}

}
