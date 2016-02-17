package de.mreturkey.games.faf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.mreturkey.games.faf.Constants;
import de.mreturkey.games.faf.FireAndFlame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		new LwjglApplication(new FireAndFlame(), config);
	}
}
