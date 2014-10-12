package pokeAdventure.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * speichert/laedt alle images
 *
 */
public abstract class SpriteManager {

	public static Image menuBack;
	public static Image btnNeuesSpiel, btnNeuesSpielOver, btnSpielLaden, btnSpielLadenOver, btnOptionen, btnOptionenOver, btnBeenden, btnBeendenOver;
	public static Image geschlechtJunge, geschlechtJungeOver, geschlechtMaedchen, geschlechtMaedchenOver, geschlechtAnders, geschlechtAndersOver;
	public static Image pikachu, papierbg, prof;
	public static Image sliderBack, slider, sliderOver;
	
	public static void load() {
		try {

		// menu

		menuBack = new Image("res/menu/menu.png");
		
		btnNeuesSpiel = new Image("res/menu/buttons/neuesSpiel.png");
		btnNeuesSpielOver = new Image("res/menu/buttons/neuesSpielOver.png");

		btnSpielLaden = new Image("res/menu/buttons/laden.png");
		btnSpielLadenOver = new Image("res/menu/buttons/ladenOver.png");

		btnOptionen = new Image("res/menu/buttons/optionen.png");
		btnOptionenOver = new Image("res/menu/buttons/optionenOver.png");

		btnBeenden = new Image("res/menu/buttons/beenden.png");
		btnBeendenOver = new Image("res/menu/buttons/beendenOver.png");

		// start
		geschlechtJunge = new Image("res/gameStart/jungeIcon.png");
		geschlechtJungeOver = new Image("res/gameStart/jungeIconOver.png");

		geschlechtAnders = new Image("res/gameStart/anderesIcon.png");
		geschlechtAndersOver = new Image("res/gameStart/anderesIconOver.png");

		geschlechtMaedchen = new Image("res/gameStart/maedchenIcon.png");
		geschlechtMaedchenOver = new Image("res/gameStart/maedchenIconOver.png");
		
		//slider
		sliderBack = new Image("res/menu/slider/sliderBack.png");
		slider = new Image("res/menu/slider/slider.png");
		sliderOver = new Image("res/menu/slider/sliderOver.png");
		
		//misc
		pikachu = new Image("res/gameStart/pikachu.png");
		papierbg = new Image("res/gameStart/Papierbg.png");
		prof = new Image("res/gameStart/prof.png");
		
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
