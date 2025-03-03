package eventos;

import java.util.ArrayList;
import java.util.EventListener;

import enums.DireccionCalle;
import mapa.ObjetoDelMapa;
import mapa.TileVacia;
import utiles.DireccionClickTileTransporte;

public abstract class Listeners {

	public static ArrayList<EventListener> listeners = new ArrayList<>();
	
	public static void agregarListener(EventListener listener) { 	//Anade las clases que tengan eventos. te lo re robe Facu
		if (!listeners.contains(listener)) {
			 System.out.println("Listener agregado: " + listener.getClass().getSimpleName());
			listeners.add(listener);
			
		}
		
	}
	
	public static void ponerCalle(TileVacia tile, DireccionClickTileTransporte direccion) {
		for (EventListener listener : listeners) {
			if((listener instanceof EventoPonerCalle)) {
				((EventoPonerCalle)listener).ponerCalle(tile, direccion);;
			}
		}
	}
	
	public static void rutaAgregada(String clave) {
		for (EventListener listener : listeners) {
			if((listener instanceof EventoAgregarRuta)) {
				((EventoAgregarRuta)listener).rutaAgregada(clave);;
			}
		}
	}
	
	public static void objetoDelMapaClickeado(ObjetoDelMapa om) {
		for (EventListener listener : listeners) {
			if((listener instanceof EventoObjetoDelMapaClickeado)) {
				((EventoObjetoDelMapaClickeado)listener).objetoDelMapaClickeado(om);;
			}
		}
	}
	
}
