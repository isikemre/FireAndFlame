package de.mreturkey.games.faf.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import de.mreturkey.games.faf.Constants;
import de.mreturkey.games.faf.screen.GameStage;
import de.mreturkey.games.faf.screen.GameState;
import de.mreturkey.games.faf.util.GameManager;

public class TheFire extends Entity {

	public static Circle tmpCircle;
	public static float speed = 300;

	private final Texture texture;
	private final float radius;
	
	private boolean free = false;
	private boolean scored = false;
	
	/**
	 * Creates a new TheFire entity which loads the default texture.
	 */
	public TheFire() {
		this.texture = new Texture("the_fire.png");
		this.x = Constants.WIDTH / 2 - texture.getWidth() / 2;
		this.y = Constants.HEIGHT + (texture.getHeight() / 2) + 12;
		this.radius = texture.getWidth() / 2;
		this.toBack();
		setup();
	}
	
	public TheFire(Texture texture) {
		this(texture, Constants.WIDTH / 2 - texture.getWidth() / 2, Constants.HEIGHT + (texture.getHeight() / 2) + 12);
	}
	
	public TheFire(Texture texture, float x, float y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.radius = texture.getWidth() / 2;
		this.toBack();
		setup();
	}
	
	private void setup() {
		setBounds(x, y, texture.getWidth(), texture.getHeight());
	}
	
	@Override
	public Actor hit(float x, float y, boolean touchable){
		if(!this.isVisible() || this.getTouchable() == Touchable.disabled) return null;
		if (tmpCircle.contains(x,y)) return this;
		return null;
	}
	
	@Override
	public void act(float delta) {
		if(free) return;
		super.act(delta);
		
		if(y <= 0 - texture.getHeight()) {
			free();
			return;
		}
		
		setPosition(x, y - speed * delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(free) return;
		super.draw(batch, parentAlpha);
		batch.draw(texture, x - texture.getWidth() / 2, y - texture.getHeight() / 2, this.getWidth(), this.getHeight());
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
	
	public float getRadius() {
		return radius;
	}
	
	@Override
	public boolean remove() {
		if(GameManager.getCurrentState() == GameState.PLAY) ((GameStage) GameManager.getCurrentStage()).removeTheFire(this);
		return super.remove();
	}
	
	public void free(boolean free) {
		this.free = free;
	}
	
	public void free() {
		free = true;
	}
	
	public void unfree() {
		free = false;
	}
	
	public boolean isFree() {
		return free;
	}
	
	public void scored() {
		scored = true;
	}
	
	public boolean isScored() {
		return scored;
	}
	
	public void reset() { 
		final float x = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
		final float y = Gdx.graphics.getHeight() + texture.getHeight() / 2 + 12;
		this.scored = false;
		setPosition(x, y);
	}
	
	@Override
	public String toString() {
		return "my name is spartaaa";
	}
	
}
