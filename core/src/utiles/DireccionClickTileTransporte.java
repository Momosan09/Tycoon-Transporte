package utiles;

import enums.DireccionCalle;
import enums.Esquina;

/**
 * Almacena la direccion que debe tener la tile de transporte y la esquina en donde fue clickeada la tile
 * @author  Momosan09
 *
 */
public class DireccionClickTileTransporte {
	
	public DireccionCalle direccion;
	public Esquina esquina;
	
	public DireccionClickTileTransporte(DireccionCalle direccion, Esquina esquina) {
		this.direccion = direccion;
		this.esquina = esquina;
	}

}
