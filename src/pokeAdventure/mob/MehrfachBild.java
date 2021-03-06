package pokeAdventure.mob;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import pokeAdventure.Main;
import pokeAdventure.util.Box;

public class MehrfachBild {

	/**
	 * n, no, o, so, s, sw, w, nw
	 */
	private SpriteSheet sheet;
	private float current, framerate;
	
	/**
	 * padding
	 * oben, rechts, unten, links
	 */
	private Box box;

	public MehrfachBild(SpriteSheet sheet, float framerate, Box box) {
		this.sheet = sheet;
		this.framerate = framerate;
		this.box = box;
	}

	public Image get(Richtung r, boolean moving) {
		// System.out.println(r);
		switch (r) {
		case norden:
			return sheet.getSprite(0, 0 + animFrame(moving));
		case nordosten:
			return sheet.getSprite(1, 0 + animFrame(moving));
		case osten:
			return sheet.getSprite(2, 0 + animFrame(moving));
		case suedosten:
			return sheet.getSprite(3, 0 + animFrame(moving));
		case sueden:
			return sheet.getSprite(4, 0 + animFrame(moving));
		case suedwesten:
			return sheet.getSprite(5, 0 + animFrame(moving));
		case westen:
			return sheet.getSprite(6, 0 + animFrame(moving));
		case nordwesten:
			return sheet.getSprite(7, 0 + animFrame(moving));
		default:
			return sheet.getSprite(0, 0 + animFrame(moving));
		}
	}

	private int animFrame(boolean moving) {
		if (moving) {
			return (int) current % sheet.getVerticalCount();
		} else {
			return 0;
		}
	}
	
	public void addTime() {
		current += 1 / (float) (Main.FPS / framerate);
	}
	
	public Box getBox() {
		return box;
	}

}
