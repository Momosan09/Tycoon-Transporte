package eventos;

import java.util.EventListener;

import mapa.ObjetoDelMapa;

public interface EventoObjetoDelMapaClickeado extends EventListener{

	void objetoDelMapaClickeado(ObjetoDelMapa om);
}
