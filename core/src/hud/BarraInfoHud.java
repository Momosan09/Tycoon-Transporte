package hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.Alignment;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;

import audio.MAudio;
import eventos.EventoAgregarRuta;
import eventos.EventoObjetoDelMapaClickeado;
import eventos.EventoPasoDelTiempoIndustrias;
import eventos.EventoSeGanoDinero;
import eventos.Listeners;
import mapa.ObjetoDelMapa;
import mapa.PuntoMuerto;
import mapa.transporte.Ruta;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Colores;
import utiles.EstiloFuente;
import utiles.Recursos;
import java.util.HashMap;


public class BarraInfoHud extends Table implements EventoAgregarRuta, EventoObjetoDelMapaClickeado, EventoPasoDelTiempoIndustrias, EventoSeGanoDinero{
	

	private Label label;
	private Label rutasLbl, dineroLbl, dinero;
	private TablaInfoSobreLaIndustria infoIndustria;
	private Label cantidadVehiculos;
	private ScrollPane scrollpanel;
	private Table tablaDeRutas;
	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	private Skin skin = new Skin(Gdx.files.internal(Recursos.RUTA_BOTONES));
	private HashMap<String, Label> labelsCantidadVehiculos = new HashMap<>();
	private ObjetoDelMapa objClickeado;

	
	
	public BarraInfoHud() {
		Listeners.agregarListener(this);
		
		infoIndustria = new TablaInfoSobreLaIndustria();
		
		label = new Label("INFO GENERAL", estiloLabel);
		rutasLbl = new Label("Rutas", estiloLabel);
		
		dineroLbl = new Label("Dinero= $", estiloLabel);
		dinero = new Label(GlobalesJuego.dinero+"",estiloLabel);
		tablaDeRutas = new Table();
		
		scrollpanel = new ScrollPane(tablaDeRutas);
		scrollpanel.setScrollingDisabled(true, false);
		scrollpanel.setVariableSizeKnobs(true);

		
		this.add(infoIndustria).size(180, 200).align(Align.top);
		this.row();
		this.add(label);
		this.row();
		this.add(dineroLbl);
		this.add(dinero).align(Align.left);
		this.row();
		this.add(rutasLbl);
		this.row();
		this.add(scrollpanel).expandY();

		
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

	    labelsCantidadVehiculos.put(clave, cantidadVehiculos); // <-- Guardamos el Label

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
	    tabla.add(vehiculosLbl).padLeft(10);
	    tabla.add(cantidadVehiculos);
	    tabla.add(boton).padRight(10);
	    tabla.setBackground(new NinePatchDrawable(new NinePatch(new Texture(Recursos.RUTA_FONDO_TABLA))));
	    tablaDeRutas.row();
	    tablaDeRutas.add(tabla);
	}



	@Override
	public void objetoDelMapaClickeado(ObjetoDelMapa om) {
		objClickeado = om;
		String tipo;

		if(om instanceof PuntoMuerto) {
			tipo = "Consume";

		}else {
			tipo = "Produce";
		}
		
		String cantidad = String.valueOf(om.getCantidad());
		infoIndustria.setValores(om.getNombre(), tipo, cantidad , "no", om.getCantidadAlmacenada()+"");
	}
	
	private void refrescarTextos(Ruta r) {
	    Label label = labelsCantidadVehiculos.get(r.nombre);
	    if(label != null) {
	        label.setText(r.getCantidadDeVehiculos());
	    }
	}


	@Override
	public void pasoTiempo(ObjetoDelMapa om) {
		if(objClickeado != null && objClickeado == om) {
			infoIndustria.setValoreDinamicos(om.getCantidadAlmacenada()+"");			
		}
	}


	@Override
	public void seGanoDinero() {
        MAudio.reproducirDescargaDeMercancias();
		dinero.setText(GlobalesJuego.dinero+"");
	}

	
	
}