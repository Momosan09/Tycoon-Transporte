
package mapa.puntoDePartida;

import mapa.PuntoDePartida;
import utiles.Recursos;

public class FabricaComida extends PuntoDePartida{

	public FabricaComida(float x, float y) {
		super(Recursos.RUTA_COMIDA, x, y, Recursos.bundle.get("objetosDelMapa_puntoDePartida_comida_nombre"));
		
	}

}

