package mapa.puntoMuerto;

import enums.Recurso;
import mapa.PuntoMuerto;
import utiles.Recursos;

public class FabricaEnergia extends PuntoMuerto{

	public FabricaEnergia( float x, float y, float consumo) {
		super(Recursos.RUTA_ENERGIA, x, y, consumo, Recurso.CARBON,Recursos.bundle.get("objetosDelMapa_puntoMuerto_energia_nombre"));
		
	}

}
