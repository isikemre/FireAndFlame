package de.mreturkey.games.faf.util;

public class Point {

	public float x = 0, y = 0;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point() {}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public void clear() {
		this.x = 0;
		this.y = 0;
	}
}
