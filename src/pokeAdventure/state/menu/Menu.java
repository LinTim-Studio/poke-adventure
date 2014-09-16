package pokeAdventure.state.menu;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CombinedTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;
import pokeAdventure.transitions.LightningTransition;

public class Menu extends BasicGameState {

	MenuButton newGame;
	MenuButton laden;
	MenuButton optionen;

	Image back;

	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		// Hintergrundbild laden
		back = new Image("res/menu/menu.png");

		// ein gemeinsames x für alle buttons
		int x = Main.getWidth() / 2;
		// der Startwert für die y Richtung
		int y = Main.getHeight() / 3;
		// Der zu addierende Wert pro button
		int dy = (int) (Main.getHeight() / 7.5); // 60

		newGame = new MenuButton(x, y, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Neues Spiel!");
				int time = 500;
				CombinedTransition tranOut = new CombinedTransition();
				tranOut.addTransition(new LightningTransition(time));
				tranOut.addTransition(new FadeOutTransition(Color.black, time));
				CombinedTransition tranIn = new CombinedTransition();
				tranIn.addTransition(new LightningTransition(time));
				tranIn.addTransition(new FadeInTransition(Color.black, time));
				game.enterState(Main.gameStartID, tranOut, tranIn);
			}
		});

		laden = new MenuButton(x, y + dy, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				game.enterState(Main.ladenID, new HorizontalSplitTransition(Color.green), new VerticalSplitTransition(Color.green));
			}
		});
		// tempörär zum Vollbildwechsel-Button missbraucht :)
		optionen = new MenuButton(x, y + 2 * dy, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				try {
					container.setFullscreen(!container.isFullscreen());
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		});

		newGame.setHorizontalZentriert(true);
		laden.setHorizontalZentriert(true);
		optionen.setHorizontalZentriert(true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// füllt automatisch den ganzen Bildschirm
		back.draw(0, 0, Main.getWidth(), Main.getHeight());

		newGame.zeichneButton();
		laden.zeichneButton();
		optionen.zeichneButton();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input in = container.getInput();
		int x = in.getMouseX();
		int y = in.getMouseY();
		boolean mouseDown = in.isMousePressed(0);

		newGame.update(x, y, mouseDown);
		laden.update(x, y, mouseDown);
		optionen.update(x, y, mouseDown);
	}

	@Override
	public int getID() {
		return Main.menuID;
	}

}
