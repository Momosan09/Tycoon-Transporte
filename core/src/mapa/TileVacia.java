package mapa;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;

import enums.DireccionCalle;
import enums.Esquina;
import eventos.Listeners;
import jugador.HabilidadesJugador;
import pantallas.juegoGlobales.GlobalesJuego;
import eventos.EventoPonerCalle;
import utiles.DireccionClickTileTransporte;
import utiles.Recursos;
import utiles.Render;

public class TileVacia extends Tile{

	public TileVacia(float x, float y) {
		super(x, y, Recursos.bundle.get("objetosDelMapa_tile_vacia_nombre"));
		
	}

/**
 * 
 * @param worldCodrdsX asegurarse de pasar worldcords.x
 * @param worldCodrdsY asegurarse de pasar worldcords.y
 * @return
 */
	public DireccionClickTileTransporte determinarDireccion(float worldCodrdsX, float worldCodrdsY) {
		float xRelativoATile = worldCodrdsX - this.x;
		float yRelativoATile = worldCodrdsY - this.y;
		float margen = 16;
		
		
		if(xRelativoATile <= margen && (yRelativoATile <= 64-margen &&  yRelativoATile >= margen)){
			System.out.println("horizontal izquierda");
			return new DireccionClickTileTransporte(DireccionCalle.CALLE_HORIZONTAL, Esquina.IZQUIERDA);
		}
		
		if(xRelativoATile >= margen && (yRelativoATile <= 64-margen &&  yRelativoATile >= margen)){
			System.out.println("horizontal derecha");
			return new DireccionClickTileTransporte(DireccionCalle.CALLE_HORIZONTAL, Esquina.DERECHA);
		}
		
		if(yRelativoATile <= margen && (xRelativoATile <= 64-margen && xRelativoATile >= margen)) {
			System.out.println("Vertical abajo");
			return new DireccionClickTileTransporte(DireccionCalle.CALLE_VERTICAL, Esquina.ABAJO);
		}
		
		if(yRelativoATile >= margen && (xRelativoATile <= 64-margen && xRelativoATile >= margen)) {
			System.out.println("Vertical arriba");
			return new DireccionClickTileTransporte(DireccionCalle.CALLE_VERTICAL, Esquina.ARRIBA);
		}
		
		

		return null;//aca puedo hacer que en lugar de devolver esto, devuelva la ultima usada
	}
	
	@Override
	public void onClick(float clickX, float clickY) {
	    super.onClick(clickX, clickY);
	    DireccionClickTileTransporte direccionClick = determinarDireccion(clickX, clickY);
	    if (GlobalesJuego.habilidadEnUso == HabilidadesJugador.PONER_TILES_TRANSPORTE) {
	        Listeners.ponerCalle(this, direccionClick);
	    }
	}
	
	@Override
	public void onClick() {
	    super.onClick();
	    System.out.println("La tile esta vacia");
	}


}
