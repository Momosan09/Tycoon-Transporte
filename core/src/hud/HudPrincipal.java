package hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import config.Configs;
import utiles.Colores;
import utiles.EstiloFuente;
import utiles.Recursos;

public class HudPrincipal extends Stage{
	
	private Table contenedor, tabla;
	private Label label;
	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	private BarraInfoHud barraDer;
	private BarraHabilidadesHud barraIz;
	
	
	public HudPrincipal() {
		
		barraDer = new BarraInfoHud();
		barraIz = new BarraHabilidadesHud();


		this.act();
		
		label = new Label("Tyccon", estiloLabel);
		
		tabla = new Table();
		contenedor = new Table();
		contenedor.setFillParent(true);

		
		tabla.add(barraIz).size(Configs.anchoPantalla/4f, Configs.altoPantalla);
		tabla.add().size(Configs.anchoPantalla/2f, Configs.altoPantalla);
		tabla.add(barraDer).size(Configs.anchoPantalla/4f, Configs.altoPantalla);
		contenedor.add(tabla).grow();
		this.addActor(contenedor);
		this.setDebugAll(true);

		barraIz.setBackground(new SpriteDrawable(new Sprite(new Texture(Recursos.RUTA_FONDO))));
		barraDer.setBackground(new SpriteDrawable(new Sprite(new Texture(Recursos.RUTA_FONDO))));
		
	}


	public void reEscalar() {
		this.getViewport().update(Configs.anchoPantalla, Configs.altoPantalla, true);
	}
	

}
