package pokeAdventure;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.einstellungen.Einstellungen;
import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.game.Game;
import pokeAdventure.game.GameStart;
import pokeAdventure.state.Ladebildschirm;
import pokeAdventure.state.menu.Menu;
import pokeAdventure.state.menu.MenuLaden;
import pokeAdventure.state.menu.Optionen;
import pokeAdventure.util.SoundManager;
import pokeAdventure.util.SpriteManager;
import pokeAdventure.util.error.Fehlermelder;

/**
 * Die Main-Klasse des Spiels
 */
public class Main extends StateBasedGame {

	/**
	 * Die ID's der states
	 */
	public static final int menuID = 0x0, ladenID = 0x1, optionenID = 0x2, gameStartID = 0x3, ladebildschirmID = 0x4, gameID = 0x5;

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
	public static final int FPS = 60;
	
	/**
	 * Das Zufallselement
	 */
	public static final Random rand = new Random();

	/**
	 * Die Maße des Standardbildschirms (16 : 9)
	 */
	private static final int defWIDTH = 800, defHEIGHT = defWIDTH / 16 * 9;

	/**
	 * Maus verschwindet nach gegebener Zeit (in ms)
	 */
	private static int maxMouseTimer = 4000, mouseTimer;
	/**
	 * Die alte Position der Maus um eine Bewegung festzustellen
	 */
	private static Vector2f oldMouse;

	/**
	 * Startpunkt der App
	 */
	private static Main mainGame;

	/**
	 * Zustand, in welchem das eigentliche Spiel läuft
	 */
	private static Game gameState;

	/**
	 * Die main-Methode des Spiels
	 * 
	 * @param args
	 *            keine Auswirkung
	 */
	public static void main(String[] args) {
		try {
			mainGame = new Main();
			ScalableGame scalableGame = new ScalableGame(mainGame, defWIDTH, defHEIGHT, true);
			AppGameContainer app = new AppGameContainer(scalableGame);
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
		app.setClearEachFrame(false);
		// Die gewünschte FPS Rate
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
		init();
		
		// Alle states gemäß ihrer ID hinzufügen
		this.addState(new Ladebildschirm());
		this.addState(new Menu());
		this.addState(new MenuLaden());
		this.addState(new Optionen());
		this.addState(new GameStart());
		
		gameState = new Game();
		this.addState(gameState);
		
		this.enterState(ladebildschirmID);
	}

	private void init() {
		Einstellungen.load();
		SpriteManager.load();
		Tastenbelegung.load();
		SoundManager.load();
	}

	public static int getWidth() {
		return defWIDTH;
	}

	public static int getHeight() {
		return defHEIGHT;
	}

	public static Game getGameState() {
		return gameState;
	}

	public static StateBasedGame getMainGame() {
		return mainGame;
	}

	/**
	 * Kann von allen Zuständen aus aufgerufen werden, die Updates (z.B Tasten)
	 * betreffen alle
	 */
	public static void allgemeinesUpdate(GameContainer container, int delta) throws SlickException {
		if (Tastenbelegung.isPressed(container.getInput(), Taste.Vollbild)) {
			container.setFullscreen(!container.isFullscreen());
		}
		mausVerstecken(container, delta);
		
		//Graphiken neu laden:
		if (debug && container.getInput().isKeyPressed(Keyboard.KEY_BACK)) {
			SpriteManager.load();
		}
	}

	private static void mausVerstecken(GameContainer container, int delta) {
		// Wenn die Maus lange nicht bewegt wird, soll sie verschwinden, aber
		// nur wenn wir im Vordergrund sind
		if (!container.hasFocus())
			return;

		Vector2f mouse = new Vector2f(container.getInput().getMouseX(), container.getInput().getMouseY());
		boolean bewegt = !mouse.equals(oldMouse);
		if (!bewegt && !container.isMouseGrabbed()) {
			mouseTimer += delta;
			if (mouseTimer >= maxMouseTimer) {
				container.setMouseGrabbed(true);
			}
		} else if (bewegt && mouseTimer != 0 && container.isMouseGrabbed()) {
			mouseTimer = 0;
			container.setMouseGrabbed(false);
		}
		oldMouse = mouse;
	}

}
