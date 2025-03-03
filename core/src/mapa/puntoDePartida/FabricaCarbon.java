
package mapa.puntoDePartida;

import mapa.PuntoDePartida;
import utiles.Recursos;

public class FabricaCarbon extends PuntoDePartida{

	public FabricaCarbon(float x, float y) {
		super(Recursos.RUTA_CARBON, x, y, Recursos.bundle.get("objetosDelMapa_puntoDePartida_carbon_nombre"));
		
	}

	
}
