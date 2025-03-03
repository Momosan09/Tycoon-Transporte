package eventos;

import java.util.EventListener;

import enums.DireccionCalle;
import mapa.TileVacia;
import utiles.DireccionClickTileTransporte;

public interface EventoPonerCalle extends EventListener{
	void ponerCalle(TileVacia tile, DireccionClickTileTransporte direccion);
	
}
