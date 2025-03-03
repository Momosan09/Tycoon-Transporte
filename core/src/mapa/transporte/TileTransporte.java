package mapa.transporte;

import enums.DireccionCalle;
import mapa.ObjetoDelMapa;
import utiles.Recursos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class TileTransporte extends ObjetoDelMapa{

	protected DireccionCalle direccion = DireccionCalle.CALLE_HORIZONTAL;
	private String ruta;
	protected boolean inicio;
	protected boolean fin;
	
	public TileTransporte(String rutaTextura, float x, float y) {
		super(rutaTextura,x, y, DireccionCalle.CALLE_HORIZONTAL.getNombre(), -1);
		this.ruta = rutaTextura;
		this.setTextura(prepararTextura(rutaTextura));
		solida = false;
	}

	public TileTransporte(String rutaTextura, float x, float y, DireccionCalle direccion) {
		super(rutaTextura,x, y, direccion.getNombre(),-1);
		this.direccion = direccion;
		this.ruta = rutaTextura;
		this.setTextura(prepararTextura(rutaTextura));
		solida = false;
	}

	private TextureRegion prepararTextura(String ruta) {
		Texture t = new Texture(ruta);
		float filas = t.getHeight()/64;
		float columnas = t.getWidth()/64;
		int filaADibujar = (int) direccion.getPosicion().y;
		int columnaADibujar = (int) direccion.getPosicion().x;
		
		//Divide la textura en partes de 64x64
		TextureRegion[][] tR = TextureRegion.split(t, 64, 64);
		
		return tR[filaADibujar][columnaADibujar];
		
	}
	
	public DireccionCalle getDireccion() {
		return direccion;
	}
	
	public void setDireccion(DireccionCalle direccion) {
		this.direccion = direccion;
		this.setTextura(prepararTextura(ruta));
	}
	
	@Override
	public void setAsPath() {
		super.setAsPath();
		this.sprite = new Sprite(new Texture(Recursos.RUTA_PRECIOSO));
		sprite.setX(x);
		sprite.setY(y);
	}
	

}
