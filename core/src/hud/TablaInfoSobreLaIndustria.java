package hud;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import utiles.Colores;
import utiles.EstiloFuente;

public class TablaInfoSobreLaIndustria extends Table{

	private Label nombreIndustria, produccionLbl, produccion, recursoLbl, recurso;
	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	
	
	public TablaInfoSobreLaIndustria() {
		
		nombreIndustria = new Label("Nombre", estiloLabel);
		produccionLbl = new Label("Produccion= ", estiloLabel);
		produccion = new Label("0", estiloLabel);
		recursoLbl = new Label("Produce: " , estiloLabel);
		recurso = new Label("Nada", estiloLabel);
		
		this.add(nombreIndustria);
		this.row();
		this.add(produccionLbl);
		this.add(produccion);
		this.row();
		this.add(recursoLbl);
		this.add(recurso);
		
	}
	
	public void setValores(String nombreIndustria, String produce_consume, String produccion, String recurso) {
		this.nombreIndustria.setText(nombreIndustria);
		this.produccionLbl.setText(produce_consume);
		this.produccion.setText(produccion);
		this.recurso.setText(recurso);
	}

}
