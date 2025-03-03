
package mapa.puntoDePartida;

import mapa.PuntoDePartida;
import utiles.Recursos;

public class FabricaMadera extends PuntoDePartida{

	public FabricaMadera(float x, float y) {
		super(Recursos.RUTA_MADERA, x ,y, Recursos.bundle.get("objetosDelMapa_puntoDePartida_madera_nombre"));
		
	}

}
