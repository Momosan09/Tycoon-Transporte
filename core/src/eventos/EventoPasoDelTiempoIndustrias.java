package eventos;

import java.util.EventListener;

import mapa.ObjetoDelMapa;

public interface EventoPasoDelTiempoIndustrias extends EventListener{
	void pasoTiempo(ObjetoDelMapa om);
}
