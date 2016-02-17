package de.mreturkey.games.faf;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import de.mreturkey.games.faf.util.GameManager;

public class FireAndFlame extends Game {
	
	private static FireAndFlame instance = null;
	
	@Override
	public void create () {
		instance = this;
		new GameManager(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		GameManager.render();
	}
	
	public static FireAndFlame getInstance() {
		return instance;
	}
}
