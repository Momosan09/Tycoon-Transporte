package principal;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ScreenUtils;

import pantallas.Juego;

public class Principal extends Game {

	
	@Override
	public void create () {
		this.setScreen(new Juego(this));
	}

	@Override
	public void render () {
		ScreenUtils.clear(.3f, 1, .3f, 1);
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
