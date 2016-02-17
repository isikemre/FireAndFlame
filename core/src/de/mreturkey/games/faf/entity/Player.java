package de.mreturkey.games.faf.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.mreturkey.games.faf.util.CoordUtil;
import de.mreturkey.games.faf.util.ScreenSide;

public class Player extends Entity {

	private final Texture texture;
	private final float radius;
	
	private boolean lastOutOfScene = false;
	private float speed = 300;
	
	public ScreenSide nextScreenSide = null;
	
	public Player(Texture texture, float x, float y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.radius = texture.getWidth() / 2;

		setBounds(x, y, texture.getWidth(), texture.getHeight());
		this.toFront();
		
		CoordUtil.setPositionToCenter(this, CoordUtil.coordY, 50);
	}
	
	public Player(Texture texture) {
		this(texture, 0, 0);
	}
	
	public Player() {
		this(new Texture("player.png"), 0, 0);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void act(float delta) {
		setPosition(x + speed * delta, y);
		
		if(CoordUtil.outOfScene(x, y, getWidth(), getHeight())) {
			if(!lastOutOfScene) {
				changeDirection();
				lastOutOfScene = true;
			}
		} else {
			if(lastOutOfScene) lastOutOfScene = false;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, x, y);
	}
	
	public float getRadius() {
		return radius;
	}
	
	public void changeDirection() {
		speed = -speed;
	}
}
