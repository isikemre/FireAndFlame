package de.mreturkey.games.faf.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import de.mreturkey.games.faf.Constants;

public class TestEntity extends Entity {

	private final Texture img;
	private boolean loop = true, backward = false;
	
	public TestEntity() {
		this.img = new Texture("badlogic.jpg");
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if(x >= Constants.WIDTH - img.getWidth()) backward = true;
		if(x <= 0) backward = false;
		
		if(loop && backward) x -= Constants.SPEED * delta;
		else x += Constants.SPEED * delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(img, x, y);
	}
	
}
