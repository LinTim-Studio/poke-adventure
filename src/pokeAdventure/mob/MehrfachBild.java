package pokeAdventure.mob;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import pokeAdventure.Main;

public class MehrfachBild {

	/**
	 * 
	 * 
	 * 
	 */
	private SpriteSheet sheet;
	private int framerate;
	private float current;
	private boolean moving;

	public MehrfachBild(SpriteSheet sheet, int framerate) {
		this.sheet = sheet;
		this.framerate = framerate;
	}

	public Image get(Richtung r) {
		// System.out.println(r);
		switch (r) {
		case norden:
			return sheet.getSprite(0, 0 + animFrame());
		case nordosten:
			return sheet.getSprite(1, 0 + animFrame());
		case osten:
			return sheet.getSprite(2, 0 + animFrame());
		case suedosten:
			return sheet.getSprite(3, 0 + animFrame());
		case sueden:
			return sheet.getSprite(4, 0 + animFrame());
		case suedwesten:
			return sheet.getSprite(5, 0 + animFrame());
		case westen:
			return sheet.getSprite(6, 0 + animFrame());
		case nordwesten:
			return sheet.getSprite(7, 0 + animFrame());
		default:
			return sheet.getSprite(0, 0 + animFrame());
		}
	}

	private int animFrame() {
		if (moving) {
			current += 1 / (float) (Main.FPS / framerate);
			return (int) current % sheet.getVerticalCount();
		} else {
			return 0;
		}
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
