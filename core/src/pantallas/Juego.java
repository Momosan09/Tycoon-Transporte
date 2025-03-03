
package pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import config.Configs;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import entradas.Entradas;
import entradas.MovimientoCamara;
import enums.DireccionCalle;
import enums.Esquina;
import eventos.Listeners;
import hud.HudPrincipal;
import eventos.EventoPonerCalle;
import mapa.Mapa;
import mapa.PuntoDePartida;
import mapa.PuntoMuerto;
import mapa.Tile;
import mapa.TileVacia;
import mapa.puntoDePartida.FabricaCarbon;
import mapa.puntoMuerto.FabricaEnergia;
import mapa.transporte.Calle;
import mapa.transporte.TileTransporte;
import pantallas.juegoGlobales.GlobalesJuego;
import utiles.DireccionClickTileTransporte;
import utiles.Recursos;
import utiles.Render;
import jugador.Habilidades;
import vehiculos.Auto;

import java.util.HashMap;
import java.util.ArrayList;

public class Juego implements Screen{

	private Game g;
	private Tile[][] tiles;
	private Mapa mapa;
	
	private Habilidades habilidades;
	
	private HashMap<Vector2, PuntoDePartida> puntosDePartida = new HashMap<Vector2, PuntoDePartida>();
	private HashMap<Vector2, PuntoMuerto> puntosMuerto = new HashMap<Vector2, PuntoMuerto>();
	private HashMap<Vector2, Calle> calles = new HashMap<Vector2, Calle>();
	
	private ArrayList<Auto> autos = new ArrayList<>();
	
	
	//HUD
	private HudPrincipal hudP;
	private MovimientoCamara movCam;
	private ScreenViewport vp;
	
	public Juego(Game g) {
		this.g = g;
		Gdx.input.setInputProcessor(Entradas.muxTiles);
		float vpAncho = Gdx.graphics.getWidth(), vpAlto = Gdx.graphics.getHeight();
		Render.camaraJuego = new OrthographicCamera(vpAncho, vpAlto);
		Render.camaraJuego.position.x = 0 + (vpAncho/2);
		Render.camaraJuego.position.y = 0 + (vpAlto/2);
		Render.camaraJuego.update();
		
		Render.camaraHUD = new OrthographicCamera(vpAncho, vpAlto);
		Render.camaraHUD.position.x = 0 + (vpAncho/2);
		Render.camaraHUD.position.y = 0 + (vpAlto/2);
		Render.camaraHUD.update();
		
		mapa = new Mapa();
		GlobalesJuego.mapa = mapa;
		movCam = new MovimientoCamara(Render.camaraJuego);
		habilidades = new Habilidades(mapa);
		hudP = new HudPrincipal();
		Entradas.muxTiles.addProcessor(hudP);
		Entradas.muxTiles.addProcessor(mapa);
		Entradas.muxTiles.addProcessor(movCam);
		Entradas.muxTiles.addProcessor(habilidades);

	}
	
	@Override
	public void show() {
		mapa.inicializar(10,10);
		

		//creacionInicial();
	}

	@Override
	public void render(float delta) {

		Render.sr.setProjectionMatrix(Render.camaraJuego.combined);
		Render.sr.begin(ShapeType.Line);
		mapa.dibujarTiles();
		Render.sr.end();
		
		Render.batch.setProjectionMatrix(Render.camaraJuego.combined);
		Render.batch.begin();
		mapa.dibujar();


		Render.batch.end();
		
		Render.batch.setProjectionMatrix(Render.camaraHUD.combined);
		
		Render.batch.begin();
		hudP.act();
		hudP.draw();
		Render.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		Configs.anchoPantalla = width;
		Configs.altoPantalla = height;
		hudP.reEscalar();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		Render.batch.dispose();
		Render.sr.dispose();
	}


}