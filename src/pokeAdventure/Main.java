package pokeAdventure;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.state.menu.Menu;
import pokeAdventure.state.menu.MenuLaden;
import pokeAdventure.state.menu.Optionen;
import pokeAdventure.game.gameStart;
import pokeAdventure.util.error.Fehlermelder;

/**
 * Die Main-Klasse des Spiels
 */
public class Main extends StateBasedGame {
	
	/**
	 * Die ID's der states
	 */
	public static final int menuID = 0x0, ladenID = 0x1, optionenID = 0x2, gameStartID = 0x3;

	/**
	 * Gibt an, ob wir am debuggen sind oder nicht
	 */
	public static boolean debug = true;

	/**
	 * Der Titel des Fensters
	 */
	private static final String TITLE = "Poke-Adventure";

	/**
	 * Die fps-Rate des Spiels
	 */
	private static final int FPS = 30;

	/**
	 * Die Maße des Standartbildschirms
	 */
	private static final int defWIDTH = 800, defHEIGHT = defWIDTH / 16 * 9; // 16
																			// :
																			// 9
																			// Verhältnis

	/**
	 * Die main-Methode des Spiels
	 * 
	 * @param args
	 *            keine Auswirkung
	 */
	public static void main(String[] args) {
		try {
			// Scalable funktioniert noch nicht so wie ich dachte ...
			AppGameContainer app = new AppGameContainer(new ScalableGame(new Main(), defWIDTH, defHEIGHT, true));
			initApp(app);
			app.start();
		} catch (SlickException e) {
			Fehlermelder.melde(e, "Fehler beim starten!");
			Fehlermelder.crash();
		}
	}

	/**
	 * Starteigenschaften des Fensters definieren
	 * 
	 * @throws SlickException
	 *             Bei Fehler in DisplayMode
	 */
	private static void initApp(AppGameContainer app) throws SlickException {
		// Nur zu Debug Zwecken
		if (debug) {
			app.setVerbose(true);
			app.setShowFPS(true);
		}
		// Da die Karte alles bedeckt, können wir einfahc übermalen
		app.setClearEachFrame(false);
		// Die gewünschet FPS Rate
		app.setTargetFrameRate(FPS);
		// Damit wir nicht zuviel rendern
		app.setVSync(true);
		// Muss noch überarbeitete werden
		app.setDisplayMode(defWIDTH, defHEIGHT, false);
	}

	/**
	 * Erstellt ein neues StateBasedGame
	 * 
	 * @param name
	 *            Der Name des Spiels
	 */
	public Main() {
		super(TITLE);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// Alle states gemäß ihrer ID hinzufügen
		 this.addState(new Menu());
		 this.addState(new MenuLaden());
		 this.addState(new Optionen());
		 this.addState(new gameStart());
	}

}
