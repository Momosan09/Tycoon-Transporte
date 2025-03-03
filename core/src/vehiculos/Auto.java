package vehiculos;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import mapa.Tile;
import mapa.transporte.TileTransporte;
import utiles.Recursos;

public class Auto extends Vehiculo{

	public Auto(float x, float y,  ArrayList<TileTransporte> camino) {
		super(x,y, Recursos.RUTA_AUTO, 100, 80, camino);
		
	}

}
