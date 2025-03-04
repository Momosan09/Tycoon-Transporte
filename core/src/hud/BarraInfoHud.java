package hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import eventos.EventoAgregarRuta;
import eventos.EventoObjetoDelMapaClickeado;
import eventos.Listeners;
import mapa.ObjetoDelMapa;
import mapa.PuntoMuerto;
import mapa.transporte.Ruta;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Colores;
import utiles.EstiloFuente;
import utiles.Recursos;

public class BarraInfoHud extends Table implements EventoAgregarRuta, EventoObjetoDelMapaClickeado{
	

	private Label label;
	private Label rutasLbl;
	private TablaInfoSobreLaIndustria infoIndustria;
	private Label cantidadVehiculos;

	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	private Skin skin = new Skin(Gdx.files.internal(Recursos.RUTA_BOTONES));
	
	
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
		
		final Ruta ruta = GlobalesJuego.mapa.getRuta(clave);
		Table tabla = new Table();
		Label nombre = new Label(ruta.nombre, estiloLabel);
		Label vehiculosLbl = new Label("Vehiculos= ", estiloLabel);
		Label cantidadVehiculos = new Label(ruta.getCantidadDeVehiculos()+"", estiloLabel);
		this.cantidadVehiculos = cantidadVehiculos;
		TextButton boton = new TextButton("Agregar auto", skin);
		
		boton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            	System.out.println(ruta.nombre);
            	GlobalesJuego.mapa.getRuta(ruta.nombre).agregarVehiculo();
            	refrescarTextos(ruta);
            }
        });
		
		tabla.add(nombre);
		tabla.row();
		tabla.add(vehiculosLbl);
		tabla.add(cantidadVehiculos);
		tabla.add(boton);
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
	
	private void refrescarTextos(Ruta r) {
		cantidadVehiculos.setText(r.getCantidadDeVehiculos());
	}
	
	
}