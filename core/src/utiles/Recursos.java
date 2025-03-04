
package utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.I18NBundle;

public abstract class Recursos {

	public static final I18NBundle bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
	
	 public static final String RUTA_CARBON = "mapa/puntoDePartida/carbon.png";
	 public static final String RUTA_HIERRO = "mapa/puntoDePartida/hierro.png";
	 public static final String RUTA_MADERA = "mapa/puntoDePartida/madera.png";
	 public static final String RUTA_COMIDA = "mapa/puntoDePartida/comida.png";
	 public static final String RUTA_PRECIOSO = "mapa/puntoDePartida/dinero.png";
	 
	 public static final String RUTA_ENERGIA = "mapa/puntoMuerto/energia.png";
	 
	 public static final String RUTA_CALLES = "mapa/transporte/calle/calles.png";
	 
	 public static final String RUTA_INICIO = "mapa/pathfinding/inicio.png";
	 public static final String RUTA_FIN = "mapa/pathfinding/fin.png";
			 
	 public static final String RUTA_AUTO = "vehiculos/auto.png";
	 
	 //audio
	 public static final String RUTA_AUDIO_CACHING = "audio/vehiculos/ca-ching.mp3";
	 public static final String RUTA_AUDIO_CARGA_AL_VEHICULO = "audio/vehiculos/cargaAlVehiculo.mp3";
	 
	 public static final String RUTA_FUENTE = "fuentes/PixelOperator.ttf";
	 
	 public static final String RUTA_BOTONES = "hud/botones.json";
	 public static final String RUTA_FONDO = "hud/fondo.png";
	 public static final String RUTA_FONDO_TABLA = "hud/tablaFondo.png";
}

