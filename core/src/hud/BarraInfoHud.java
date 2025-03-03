package hud;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import eventos.EventoAgregarRuta;
import eventos.EventoObjetoDelMapaClickeado;
import eventos.Listeners;
import mapa.ObjetoDelMapa;
import mapa.PuntoMuerto;
import mapa.transporte.Ruta;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Colores;
import utiles.EstiloFuente;

public class BarraInfoHud extends Table implements EventoAgregarRuta, EventoObjetoDelMapaClickeado{
	

	private Label label;
	private Label rutasLbl;
	private TablaInfoSobreLaIndustria infoIndustria;

	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	
	
	public BarraInfoHud() {
		Listeners.agregarListener(this);
		
		infoIndustria = new TablaInfoSobreLaIndustria();
		
		label = new Label("INFO GENERAL", estiloLabel);
		
		rutasLbl = new Label("Rutas", estiloLabel);
		this.add(infoIndustria);
		this.row();
		this.add(label);
		this.row();
		this.add(rutasLbl);
		
	}


	@Override
	public void rutaAgregada(String clave) {
		agregarRutasAlHUD(clave);
	}

	
	private void agregarRutasAlHUD(String clave) {
		
		Ruta ruta = GlobalesJuego.mapa.getRuta(clave);
		Table tabla = new Table();
		Label nombre = new Label(ruta.nombre, estiloLabel);
		Label vehiculosLbl = new Label("Vehiculos= ", estiloLabel);
		Label cantidadVehiculos = new Label(ruta.getCantidadDeVehiculos()+"", estiloLabel);
		
		tabla.add(nombre);
		tabla.row();
		tabla.add(vehiculosLbl);
		tabla.add(cantidadVehiculos);
		this.row();
		this.add(tabla);
		
	}


	@Override
	public void objetoDelMapaClickeado(ObjetoDelMapa om) {
		String tipo;

		if(om instanceof PuntoMuerto) {
			tipo = "Consume";

		}else {
			tipo = "Produce";
		}
		
		String cantidad = String.valueOf(om.getCantidad());
		infoIndustria.setValores(om.getNombre(), tipo, cantidad , "no");
	}
	
}