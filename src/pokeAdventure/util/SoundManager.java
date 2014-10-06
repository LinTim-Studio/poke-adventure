package pokeAdventure.util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public abstract class SoundManager {

	public static Sound beep;

	public static void load() {
		try {
			beep = new Sound("res/sounds/beep.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
