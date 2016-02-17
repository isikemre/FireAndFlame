package de.mreturkey.games.faf.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	private final GameStage stage;
	private boolean visible = true, doAct = true, doDraw = true;
	
	public GameScreen(Game game) {
		this.stage = new GameStage(this);
	}
	
	public GameStage getStage() {
		return stage;
	}
	
	public GameState getState() {
		return GameState.PLAY;
	}

	@Override
	public void render(float delta) {
		if(!visible) return;
		if(doAct) stage.act(delta);
		if(doDraw) stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		doAct = false;
	}

	@Override
	public void resume() {
		doAct = true;
	}
	
	@Override
	public void show() {
		visible = true;
	}

	@Override
	public void hide() {
		visible = false;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
