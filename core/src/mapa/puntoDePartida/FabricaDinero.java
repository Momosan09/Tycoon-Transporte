
package mapa.puntoDePartida;

import mapa.PuntoDePartida;
import utiles.Recursos;

public class FabricaDinero extends PuntoDePartida{

	public FabricaDinero(float x, float y) {
		super(Recursos.RUTA_PRECIOSO, x, y, Recursos.bundle.get("objetosDelMapa_puntoDePartida_precioso_nombre"));
		
	}

}

