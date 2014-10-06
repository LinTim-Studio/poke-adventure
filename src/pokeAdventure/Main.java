package pokeAdventure;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.game.Game;
import pokeAdventure.game.GameStart;
import pokeAdventure.state.Ladebildschirm;
import pokeAdventure.state.menu.Menu;
import pokeAdventure.state.menu.MenuLaden;
import pokeAdventure.state.menu.Optionen;
import pokeAdventure.util.SoundManager;
import pokeAdventure.util.error.Fehlermelder;

/**
 * Die Main-Klasse des Spiels
 */
public class Main extends StateBasedGame {

	/**
	 * Die ID's der states
	 */
	public static final int menuID = 0x0, ladenID = 0x1, optionenID = 0x2, gameStartID = 0x3, ladebildschirm = 0x4, gameID = 0x5;

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
	private static final int FPS = 60;

	/**
	 * Die Maße des Standartbildschirms
	 */
	private static final int defWIDTH = 800, defHEIGHT = defWIDTH / 16 * 9; // 16
																			// :
																			// 9

	/**
	 * Die main-Methode des Spiels
	 * 
	 * @param args
	 *            keine Auswirkung
	 */
	public static void main(String[] args) {
		try {
			ScalableGame game = new ScalableGame(new Main(), defWIDTH, defHEIGHT, true);
			AppGameContainer app = new AppGameContainer(game);
			// AppGameContainer app = new AppGameContainer(new Main());
			initApp(app);
			app.start();
		} catch (Exception e) {
			Fehlermelder.melde(e, "Fehler beim Starten der App!");
			Fehlermelder.crash();
		}
	}

	/**
	 * Starteigenschaften des Fensters definieren
	 * 
	 * @throws SlickException
	 *             Bei Fehler in DisplayMode
	 * @throws LWJGLException
	 */
	private static void initApp(AppGameContainer app) throws SlickException, LWJGLException {
		GameContainer.enableSharedContext();
		// Nur zu Debug Zwecken
		if (debug) {
			app.setVerbose(true);
			app.setShowFPS(true);
		}
		// Da die Karte alles bedeckt, können wir einfahc übermalen
		app.setClearEachFrame(true);
		// Die gewünschet FPS Rate
		app.setTargetFrameRate(FPS);
		// Damit wir nicht zuviel rendern
		app.setVSync(true);
		// Muss noch überarbeitete werden
		// app.setDisplayMode(defWIDTH, defHEIGHT, false);

		DisplayMode chosen = null;
		DisplayMode desktopMode = Display.getDesktopDisplayMode();
		for (DisplayMode mode : Display.getAvailableDisplayModes()) {
			if (mode.isFullscreenCapable()) {
				if (desktopMode.getWidth() > mode.getWidth() && desktopMode.getHeight() > mode.getHeight()) {
					if (mode.getWidth() / (float) mode.getHeight() == desktopMode.getWidth() / (float) desktopMode.getHeight()) {
						if (chosen == null)
							chosen = mode;
						else {
							if (chosen.getWidth() > mode.getWidth() && chosen.getHeight() > mode.getHeight())
								chosen = mode;
						}
					}
				}
			}
		}
		if (chosen != null) {
			app.setDisplayMode(chosen.getWidth(), chosen.getHeight(), false);
		} else {
			Fehlermelder.melde("Kein passendes Display gefunden!");
		}

	}

	/**
	 * Erstellt ein neues StateBasedGame
	 */
	public Main() {
		super(TITLE);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// Alle Dateien sollen nacheinander geladen werden, macht Sinn bei
		// vielen Dateien
		LoadingList.setDeferredLoading(true);
		// Alle states gemäß ihrer ID hinzufügen
		this.addState(new Ladebildschirm());
		this.addState(new Menu());
		this.addState(new MenuLaden());
		this.addState(new Optionen());
		this.addState(new GameStart());
		this.addState(new Game());
		
		init();
		
		this.enterState(ladebildschirm);
	}

	private void init() {
		Tastenbelegung.load();
		SoundManager.load();
	}

	public static int getWidth() {
		return defWIDTH;
	}

	public static int getHeight() {
		return defHEIGHT;
	}

}
