package mapa;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import enums.DireccionCalle;
import eventos.Listeners;
import utiles.Recursos;
import utiles.Render;

public abstract class ObjetoDelMapa extends Tile{

	protected Sprite sprite;
	protected Texture textura;
	protected float cons_prod; //Lo que consume o produce, dependiendo de la instancia
	
	
	public ObjetoDelMapa(String rutaTextura, float x, float y, String nombre, float cons_prod) {
		super(x, y, nombre);
		this.textura = new Texture(rutaTextura);
		this.sprite = new Sprite(textura);
		this.cons_prod = cons_prod;
		sprite.setX(this.x);
		sprite.setY(this.y);
		solida=false;
	}
	
	public ObjetoDelMapa(float x, float y, String nombre, float cons_prod) {
		super(x, y, nombre);
		this.cons_prod = cons_prod;
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
	
	
}
