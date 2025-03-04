package mapa.puntoMuerto;

import enums.Recurso;
import mapa.PuntoMuerto;
import utiles.Recursos;
import utiles.Util;

public class FabricaEnergia extends PuntoMuerto{

	public FabricaEnergia( float x, float y) {
		super(Recursos.RUTA_ENERGIA, x, y, Util.generarAleatorio(1, 100), Recurso.CARBON,Recursos.bundle.get("objetosDelMapa_puntoMuerto_energia_nombre"));
		
	}

}
