package mapa.transporte;

import enums.DireccionCalle;
import utiles.Recursos;

public class Calle extends TileTransporte{

	public Calle(float x, float y) {
		super(Recursos.RUTA_CALLES, x, y);
		
	}
	
	public Calle(float x, float y, DireccionCalle direccion) {
		super(Recursos.RUTA_CALLES, x, y, direccion);
	}
	


}
