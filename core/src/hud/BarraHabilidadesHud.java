package hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import config.Configs;
import jugador.HabilidadesJugador;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.Colores;
import utiles.EstiloFuente;
import utiles.Recursos;

public class BarraHabilidadesHud extends Table{
	

	private Label label;
	private Label.LabelStyle estiloLabel = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
	private ImageButton agregarCalle, crearRutas;
	
	
	public BarraHabilidadesHud() {
		
		label = new Label("HABILIDADES", estiloLabel);
		
		agregarCalle = new ImageButton(new Skin(Gdx.files.internal(Recursos.RUTA_BOTONES)), "default");
		crearRutas = new ImageButton(new Skin(Gdx.files.internal(Recursos.RUTA_BOTONES)), "agregarRutas");
		this.add(label);
		this.row();
		this.add(agregarCalle);
		this.add(crearRutas);

		agregarListeners();
		
	}
	
	
	private void agregarListeners() {
		
		agregarCalle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (GlobalesJuego.habilidadEnUso == HabilidadesJugador.PONER_TILES_TRANSPORTE) {
                    System.out.println("Modo INFO");
                    GlobalesJuego.habilidadEnUso = HabilidadesJugador.INFO;
                    if(GlobalesJuego.mapa.getGoalReached()) {
                    	GlobalesJuego.mapa.reiniciar();                	
                    }
                } else {
                    System.out.println("Modo colocar calles");
                    GlobalesJuego.habilidadEnUso = HabilidadesJugador.PONER_TILES_TRANSPORTE;

                }
            }
        });
		
		crearRutas.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
                    System.out.println("Modo INFO");
                    GlobalesJuego.habilidadEnUso = HabilidadesJugador.INFO;
                    if(GlobalesJuego.mapa.getGoalReached()) {
                    	GlobalesJuego.mapa.reiniciar();                	
                    }
                } else {
                    System.out.println("Modo crear rutas");
                    GlobalesJuego.habilidadEnUso = HabilidadesJugador.CREAR_RUTA;
                }
            }
        });
		
	}
	

}