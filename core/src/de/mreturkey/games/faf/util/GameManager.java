package de.mreturkey.games.faf.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.mreturkey.games.faf.FireAndFlame;
import de.mreturkey.games.faf.screen.GameScreen;
import de.mreturkey.games.faf.screen.GameState;

public class GameManager {

	private static FireAndFlame game;
	
	private static Screen currentScreen;
	private static Stage currentStage;
	private static GameState currentState;
	
	public GameManager(FireAndFlame game) {
		GameManager.game = game;
		final GameScreen gameScreen = new GameScreen(game);
		currentScreen = gameScreen;
		currentStage = gameScreen.getStage();
		currentState = GameState.PLAY;
	}
	
	public static FireAndFlame getGame() {
		return game;
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	
	public static Stage getCurrentStage() {
		return currentStage;
	}
	
	public static GameState getCurrentState() {
		return currentState;
	}
	
	public static void render() {
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}
}
