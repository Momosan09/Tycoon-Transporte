
package mapa.puntoDePartida;

import mapa.PuntoDePartida;
import utiles.Recursos;

public class FabricaHierro extends PuntoDePartida{

	public FabricaHierro(float x, float y) {
		super(Recursos.RUTA_HIERRO, x, y, Recursos.bundle.get("objetosDelMapa_puntoDePartida_hierro_nombre"));
		
	}

}

