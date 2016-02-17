package de.mreturkey.games.faf.util;

public enum ScreenSide {

	LEFT,
	RIGHT,
	TOP,
	BOTTOM;
	
	private ScreenSide oposite;
	
	public ScreenSide getOposite() {
		if(oposite == null) {
			switch (this) {
			case LEFT: oposite = RIGHT; break;
			case RIGHT: oposite = LEFT; break;
			case TOP: oposite = BOTTOM; break;
			case BOTTOM: oposite = TOP; break;

			default: throw new IllegalArgumentException("ScreenSide not found");
			}
		}
		return oposite;
	}
	
}
