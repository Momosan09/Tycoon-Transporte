package entradas;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import utiles.Render;

public class MovimientoCamara implements InputProcessor {

    private int lastScreenX, lastScreenY;
    private boolean moviendo = false;
    private OrthographicCamera camara;

    public MovimientoCamara(OrthographicCamera camara) {
        this.camara = camara;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == 1) { // Boton derecho
            moviendo = true;
            lastScreenX = screenX;
            lastScreenY = screenY;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == 1) {
            moviendo = false;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (moviendo) {
            float deltaX = (lastScreenX - screenX) * camara.zoom;
            float deltaY = (screenY - lastScreenY) * camara.zoom;

            camara.translate(deltaX, deltaY);
            camara.update();

            lastScreenX = screenX;
            lastScreenY = screenY;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camara.zoom += amountY * 0.1f; // Ajustar sensibilidad del zoom
        if (camara.zoom < 1) camara.zoom = 1;
        if (camara.zoom > 5) camara.zoom = 5;
        camara.update();
        return true;
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
}
