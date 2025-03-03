package audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import config.Configs;

import utiles.Recursos;


public abstract class MAudio {
	
	private static Sound caching = Gdx.audio.newSound(Gdx.files.internal(Recursos.RUTA_AUDIO_CACHING));
	private static Sound cargaAlVehiculo = Gdx.audio.newSound(Gdx.files.internal(Recursos.RUTA_AUDIO_CARGA_AL_VEHICULO));
	
	
	public static void reproducirSonido(String ruta) {
		Gdx.audio.newSound(Gdx.files.internal(ruta)).play(Configs.volumenSonidos);
	}
	
	public static void reproducirDescargaDeMercancias() {
		caching.play(Configs.volumenSonidos);
	}
	
	public static void reproducirCargaAlVehiculo() {
		cargaAlVehiculo.play(Configs.volumenSonidos);
	}
	
	
}
