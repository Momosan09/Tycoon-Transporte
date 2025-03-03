package jugador;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import mapa.Mapa;
import pantallas.juegoGlobales.GlobalesJuego;

public class Habilidades implements InputProcessor{
    
    private Mapa mapa;
    private Vector2 posicionInicial;
    private Vector2 posicionFinal;
    private boolean dibujando;

    public Habilidades(Mapa mapa) {
        this.mapa = mapa;
        this.dibujando = false;
    }

    // Iniciar el dibujo de una calle
    public void iniciarDibujo(Vector2 posicion) {
        this.posicionInicial = posicion;
        this.dibujando = true;
    }

    // Finalizar el dibujo y crear la calle en el mapa
    public void finalizarDibujo(Vector2 posicion) {
        if (dibujando) {
            this.posicionFinal = posicion;
//            mapa.agregarCalle(posicionInicial, posicionFinal);
            this.dibujando = false;
        }
    }

    // Cancelar el dibujo actual
    public void cancelarDibujo() {
        this.dibujando = false;
    }

    // Eliminar una calle en una posición específica
    public void eliminarCalle(Vector2 posicion) {
        mapa.removerCalle(posicion);
    }

    // Rotar una calle en una posición específica
    public void rotarCalle(Vector2 posicion) {
        mapa.rotarCalle(posicion);
    }

    // Seleccionar un elemento del mapa (calle, edificio, semáforo, etc.)
    public void seleccionar(Vector2 posicion) {
        mapa.seleccionarElemento(posicion);
    }

    // Mover un elemento seleccionado a una nueva posición
    public void moverElemento(Vector2 nuevaPosicion) {
        mapa.moverElemento(nuevaPosicion);
    }

    // Colocar una estructura (ej: semáforo, edificio, etc.)
    public void colocarEstructura(String tipo, Vector2 posicion) {
        mapa.agregarEstructura(tipo, posicion);
    }

    // Dibujar elementos si es necesario
    public void render(SpriteBatch batch) {
        if (dibujando && posicionInicial != null) {
            // Aquí podrías dibujar una vista previa de la calle
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.NUM_1) {
            if (GlobalesJuego.habilidadEnUso == HabilidadesJugador.PONER_TILES_TRANSPORTE) {
                System.out.println("Modo INFO");
                GlobalesJuego.habilidadEnUso = HabilidadesJugador.INFO;
            } else {
                System.out.println("Modo colocar calles");
                GlobalesJuego.habilidadEnUso = HabilidadesJugador.PONER_TILES_TRANSPORTE;
                if(mapa.getGoalReached()) {
                	mapa.reiniciar();                	
                }
            }
        }

        if (keycode == Keys.NUM_2) {
            if (GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
                System.out.println("Modo INFO");
                GlobalesJuego.habilidadEnUso = HabilidadesJugador.INFO;
                if(mapa.getGoalReached()) {
                	mapa.reiniciar();                	
                }
            } else {
                System.out.println("Modo crear rutas");
                GlobalesJuego.habilidadEnUso = HabilidadesJugador.CREAR_RUTA;
            }
        }
        
        if(keycode == Keys.ENTER) {
        	if(GlobalesJuego.habilidadEnUso == HabilidadesJugador.CREAR_RUTA) {
        		if(mapa.getEndNode() != null) {
        			mapa.search();        			
        		}
        	}
        }
        

        return false;
    }


	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
