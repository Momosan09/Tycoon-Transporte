package eventos;

import java.util.EventListener;

public interface EventoAgregarRuta extends EventListener{

	void rutaAgregada(String clave);
}
