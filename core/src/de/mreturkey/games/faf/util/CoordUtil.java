package de.mreturkey.games.faf.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;

import de.mreturkey.games.faf.entity.Entity;
import de.mreturkey.games.faf.entity.Player;
import de.mreturkey.games.faf.entity.TheFire;

public class CoordUtil {
	
	public static final char coordX = 'x';
	public static final char coordY = 'y';
	public static final char coordZ = 'z';
	
	private static final Circle tmpCircle1 = new Circle();
	private static final Circle tmpCircle2 = new Circle();
	
	public static void setPositionToCenter(Entity entity) {
		entity.setPosition(Gdx.graphics.getWidth() / 2 - entity.getWidth() / 2, Gdx.graphics.getHeight() / 2 - entity.getHeight() / 2);
	}
	
	public static void setPositionToCenter(Entity entity, char coord, float value) {
		switch (coord) {
		case coordX:
			entity.setPosition(value, Gdx.graphics.getHeight() / 2 - entity.getHeight() / 2);
			break;
		case coordY:
			entity.setPosition(Gdx.graphics.getWidth() / 2 - entity.getWidth() / 2, value);
			break;

		default:
			setPositionToCenter(entity);
			break;
		}
	}
	
	public static boolean outOfScene(float x, float y, float width, float height) {
		final float sceneWidth = Gdx.graphics.getWidth();
		final float sceneHeight = Gdx.graphics.getHeight();
		return x < 0 || y < 0 || x > sceneWidth || y > sceneHeight
				|| x + width < 0 || y + height < 0 || x + width > sceneWidth || y + height > sceneHeight;
	}
	
	public static boolean outOfScene(Entity entity) {
		return outOfScene(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
	}
	
	public static boolean isCollidingPlayerWithTheFires(final Player player, final Array<TheFire> fires) {
		for(TheFire fire : fires) {
			tmpCircle1.set(fire.getX(), fire.getY(), fire.getRadius());
			tmpCircle2.set(player.getX(), player.getY(), player.getRadius());
			
			if(tmpCircle1.overlaps(tmpCircle2)) return true;
		}
		return false;
	}

}
