package de.mreturkey.games.faf.screen;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import de.mreturkey.games.faf.Constants;
import de.mreturkey.games.faf.entity.Player;
import de.mreturkey.games.faf.entity.TheFire;
import de.mreturkey.games.faf.util.CoordUtil;
import de.mreturkey.games.faf.util.ScreenSide;

public class GameStage extends Stage {

	private final Screen screen;
	private final Texture theFireTexture;
	private final Texture playerTexture;
	
	private final Player player;
	private final Label scoreLabel;
	private int score = 0;
	
	private float distanceForSpawn = 0;
	private float cEinViertel = 0;
	private float cDreiVirtel = 0;
	private TheFire lastSpawnedFire = null;
	
	private float firstSpawnY = 0;
	
	private final Queue<TheFire> firesQueue = new LinkedList<TheFire>();
	
	private final Array<TheFire> fires = new Array<TheFire>();
	
	public GameStage(Screen screen) {
		super();
		Gdx.input.setInputProcessor(this);
		this.screen = screen;
		
		this.theFireTexture = new Texture("the_fire.png");
		this.playerTexture = new Texture("player.png");
		
 		this.player = new Player(playerTexture);
		this.addActor(this.player);
		
		this.scoreLabel = new Label(Integer.toString(score), new LabelStyle(new BitmapFont(), Color.DARK_GRAY));
		this.scoreLabel.setPosition(20, getHeight() - (scoreLabel.getHeight() + 20));
		this.addActor(scoreLabel);
		
		TheFire.tmpCircle = new Circle(0, 0, theFireTexture.getWidth() / 2);
		cEinViertel = Gdx.graphics.getHeight() / 4;
		cDreiVirtel = cEinViertel * 3;
		
		this.distanceForSpawn = MathUtils.random(Gdx.graphics.getHeight() / 2, Gdx.graphics.getHeight());
		
		setupListener();
	}

	private void setupListener() {
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(player != null) player.changeDirection();
				return true;
			}
		});
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public void gameOver() {
		TheFire.speed = 0;
	}
	
	private TheFire addOrUnfreeTheFire() {
		for(TheFire fire : fires) {
			if(fire.isFree()) {
				fire.reset();
				fire.unfree();
				firesQueue.add(fire);
				return fire;
			}
		}
		return addTheFire();
	}
	
	private void checkDistanceForSpawn() {
		if(fires.size <= 0) {
			if(firstSpawnY < distanceForSpawn) { //das hier ist für den firstSpawn zuständig damit keine doppelt hintereinander spawnen
				distanceForSpawn = MathUtils.random(cEinViertel, cDreiVirtel);
				lastSpawnedFire = addOrUnfreeTheFire();
				System.out.println(distanceForSpawn);
			} else firstSpawnY += 10;
			return;
		}
		if(!lastSpawnedFire.isFree() && lastSpawnedFire.isVisible()) {
			if(lastSpawnedFire.getY() < distanceForSpawn) {
				distanceForSpawn = MathUtils.random(cEinViertel, cDreiVirtel);
				lastSpawnedFire = addOrUnfreeTheFire();
				System.out.println(distanceForSpawn);
			}
		}
	}
	
	private void checkScreenSide() {
		ScreenSide nextScreenSide;
		if(player.getX() < Constants.WIDTH / 2) nextScreenSide = ScreenSide.LEFT;
		else if(player.getX() >= Constants.WIDTH - Constants.WIDTH / 2) nextScreenSide = ScreenSide.RIGHT;
		else throw new NullPointerException("ScreenSide cannot be init");
		
		if(player.nextScreenSide != null && player.nextScreenSide == nextScreenSide) this.gameOver();
		else player.nextScreenSide = nextScreenSide;
	}
	
	public TheFire addTheFire() {
		final TheFire theFire = new TheFire(theFireTexture);
		return addTheFire(theFire);
	}
	
	public TheFire addTheFire(TheFire theFire) {
		fires.add(theFire);
		firesQueue.add(theFire);
		addActor(theFire);
		return theFire;
	}
	
	public void removeTheFire(TheFire theFire) {
		fires.removeValue(theFire, true);
	}

	public Array<TheFire> getFires() {
		return fires;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(CoordUtil.isCollidingPlayerWithTheFires(player, fires)) this.gameOver();
		
		if(firesQueue.peek() != null && !firesQueue.peek().isScored() && firesQueue.peek().getY() < player.getY()) {
			score++;
			firesQueue.poll().scored();
			scoreLabel.setText(score+"");
			checkScreenSide();
		}
		checkDistanceForSpawn();
	}
}
