package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CombinedTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;
import pokeAdventure.transitions.LightningTransition;
import pokeAdventure.util.SpriteManager;

public class Menu extends BasicGameState {

	ButtonArray btns;

	MenuButton newGame;
	MenuButton laden;
	MenuButton optionen;
	MenuButton ende;

	Slider test;

	int selected;

	int testInt;

	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		// ein gemeinsames x für alle buttons
		int x = Main.getWidth() / 2;
		// der Startwert für die y Richtung
		int y = Main.getHeight() / 3;
		// Der zu addierende Wert pro button
		int dy = (int) (Main.getHeight() / 7.5); // 60

		int time = 500;
		final CombinedTransition tranOut = new CombinedTransition();
		tranOut.addTransition(new LightningTransition(time));
		tranOut.addTransition(new FadeOutTransition(Color.black, time));
		final CombinedTransition tranIn = new CombinedTransition();
		tranIn.addTransition(new LightningTransition(time));
		tranIn.addTransition(new FadeInTransition(Color.black, time));

		newGame = new MenuButton(x, y, SpriteManager.btnNeuesSpiel, SpriteManager.btnNeuesSpielOver, new Action() {
			@Override
			public void action() {
				game.enterState(Main.gameStartID, tranOut, tranIn);
			}
		});

		laden = new MenuButton(x, y + dy, SpriteManager.btnSpielLaden, SpriteManager.btnSpielLadenOver, new Action() {
			@Override
			public void action() {
				// für Testzwecke
				game.enterState(Main.gameID);
				// game.enterState(Main.ladenID, tranOut, tranIn);
			}
		});
		// tempörär zum Vollbildwechsel-Button missbraucht :)
		optionen = new MenuButton(x, y + 2 * dy, SpriteManager.btnOptionen, SpriteManager.btnOptionenOver, new Action() {
			@Override
			public void action() {
				try {
					container.setFullscreen(!container.isFullscreen());
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});
		//lalalala
		ende = new MenuButton(x, y + 3 * dy, SpriteManager.btnBeenden, SpriteManager.btnBeendenOver, new Action() {
			@Override
			public void action() {
				System.exit(0);
			}
		});

		test = new Slider(Main.getWidth() / 2, 10, 500, 100, 0, 50, 100);
		test.setHorizontalZentriert(true);

		newGame.setHorizontalZentriert(true);
		laden.setHorizontalZentriert(true);
		optionen.setHorizontalZentriert(true);
		ende.setHorizontalZentriert(true);
		
		btns = new ButtonArray(newGame, laden, optionen, ende, test);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// füllt automatisch den ganzen Bildschirm
		SpriteManager.menuBack.draw(0, 0, Main.getWidth(), Main.getHeight());

		btns.render();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input in = container.getInput();
		btns.update(in);
		SoundStore.get().setSoundVolume(test.getValue() / (float) 100);
	}

	@Override
	public int getID() {
		return Main.menuID;
	}

}
