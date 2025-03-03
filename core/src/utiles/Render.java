
package utiles;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Render {
	public static SpriteBatch batch = new SpriteBatch();
	public static ShapeRenderer sr = new ShapeRenderer();
	public static OrthographicCamera camaraJuego;
	public static OrthographicCamera camaraHUD;
}
