package mapa;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import enums.DireccionCalle;
import eventos.Listeners;
import utiles.Recursos;
import utiles.Render;
import utiles.Tiempo;

public abstract class ObjetoDelMapa extends Tile{

	protected Sprite sprite;
	protected Texture textura;
	protected float cons_prod; //Lo que consume o produce, dependiendo de la instancia
	protected float produccion_por_cantidad_tiempo = 4f;
	protected float cantidadAlmacenada = 0;
	
	private Tiempo tiempo;
	private boolean esperando;
	
	
	public ObjetoDelMapa(String rutaTextura, float x, float y, String nombre, float cons_prod) {
		super(x, y, nombre);
		this.textura = new Texture(rutaTextura);
		this.sprite = new Sprite(textura);
		this.cons_prod = cons_prod;
		sprite.setX(this.x);
		sprite.setY(this.y);
		solida=false;
		tiempo = new Tiempo();
	}
	
	public ObjetoDelMapa(float x, float y, String nombre, float cons_prod) {
		super(x, y, nombre);
		this.cons_prod = cons_prod;
		sprite.setX(this.x);
		sprite.setY(this.y);
		solida=false;
	}
	
	public ObjetoDelMapa(float x, float y, String nombre) {
		super(x, y, nombre);
		sprite.setX(this.x);
		sprite.setY(this.y);
		solida=false;
	}
	
	@Override
	public void onClick() {
		Listeners.objetoDelMapaClickeado(this);
	}
	
	@Override
	public void dibujar() {
		sprite.draw(Render.batch);
	}
	
	public void producir() {
	    cantidadAlmacenada += cons_prod;
	    //System.out.println(nombre + " producción manual de " + cons_prod + ". Total almacenado: " + cantidadAlmacenada);
	}

	
	public void setTextura(TextureRegion t) {
		sprite = new Sprite(t);
		sprite.setX(x);
		sprite.setY(y);
	}
	
	/**
	 * devuelve el sprite normal a la tile
	 */
	public void volverALaNormalidad() {
		this.goal = false;
		sprite = new Sprite(textura);
		sprite.setX(x);
		sprite.setY(y);
	}
	
	public void cambiarDireccion(DireccionCalle d) {
		
	}

	public float getCantidad() {
		return cons_prod;
	};
	
	public float getCantidadAlmacenada() {
		return cantidadAlmacenada;
	}
	
	public void actualizar() {
	    if (tiempo.esperarTiempo(2000)) { // Cada 2000 ms (2 segundos)
	        cantidadAlmacenada += cons_prod; // Suma lo que produce o consume
	        Listeners.industriaPasoElTiempo(this);
	    }
	}

	public float pasarCargaAlVehiculo(float cantidad) {
		cantidadAlmacenada -= cantidad;
		return cantidad;
	}
	
	public float venderALaIndustria(float cantidad) {
		cantidadAlmacenada += cantidad;
		return 200;
	}
	
	
}
