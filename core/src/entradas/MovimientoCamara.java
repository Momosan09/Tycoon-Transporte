package entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import utiles.Render;

public class MovimientoCamara implements InputProcessor{

	
    private Vector3 lastTouch = new Vector3(); // Ultima posicion del mouse
	private OrthographicCamera camara;
    private int boton;
    
    public MovimientoCamara(OrthographicCamera camara) {
    	this.camara = camara;
    }
    
	@Override
	public boolean keyDown(int keycode) {
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
        // Convertir coordenadas de pantalla a mundo y guardarlas
    	if(button == 1) {
    		boton = 1;
        lastTouch.set(screenX, screenY, 0);
        camara.unproject(lastTouch);
        return true;
    	}else {
    		boton = 0;
    		return false;
    	}

    }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
	    // Convertir la nueva posicion del mouse a coordenadas de mundo
		if(boton == 1) {
	    Vector3 newTouch = new Vector3(screenX, screenY, 0);
	    camara.unproject(newTouch);

	    // Calcular diferencia de movimiento
	    float deltaX = lastTouch.x - newTouch.x;
	    float deltaY = lastTouch.y - newTouch.y;

	    // Mover la camara en la dirección correcta
	    camara.position.add(deltaX, deltaY, 0);

	    // **Solo actualizamos despues de calcular el nuevo movimiento**
	    camara.update();


	    return true;
		}else {
			boton = 0;
			return false;
		}
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
