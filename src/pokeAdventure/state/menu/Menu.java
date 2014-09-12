package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;

public class Menu extends BasicGameState {

	MenuButton newGame;
	MenuButton laden;
	MenuButton optionen;
	
	Image back;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		// Hintergrundbild laden
<<<<<<< HEAD
		back = new Image("res/menu/Titelbild2.png");
		
		// ein gemeinsames x für alle buttons
		int x = container.getWidth() / 2;
		// der Startwert für die y Richtung
		int y = container.getHeight() / 3;
		// Der zu addierende Wert pro button
		int dy = (int) (container.getHeight() / 7.5); // 60
		newGame = new MenuButton(x, y, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Neues Spiel!");
				game.enterState(Main.gameStartID, null, new SelectTransition(Color.darkGray));
			}
		});

		laden = new MenuButton(x, y + dy, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
//				game.enterState(Main.ladenID);
				game.enterState(Main.ladenID, new HorizontalSplitTransition(Color.green), new VerticalSplitTransition(Color.green));
			}
		});

		optionen = new MenuButton(x, y + 2 * dy, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
=======
		back = new Image("res/menu/Titelbild2.0.png");
		
		// ein gemeinsames x für alle buttons
		int x = container.getWidth() / 2;
		// der Startwert für die y Richtung
		int y = container.getHeight() / 3;
		// Der zu addierende Wert pro button
		int dy = (int) (container.getHeight() / 7.5); // 60
		newGame = new MenuButton(x, y, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Neues Spiel!");
				game.enterState(Main.gameStartID, null, new SelectTransition(Color.darkGray));
			}
		});

		laden = new MenuButton(x, y + dy, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
//				game.enterState(Main.ladenID);
				game.enterState(Main.ladenID, new HorizontalSplitTransition(Color.green), new VerticalSplitTransition(Color.green));
			}
		});

		optionen = new MenuButton(x, y + 2 * dy, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
>>>>>>> branch 'develop' of https://github.com/LinTim-Studio/poke-adventure
			@Override
			public void action() {
				game.enterState(Main.optionenID, null, new SelectTransition(Color.darkGray));
			}
		});

		newGame.setHorizontalZentriert(true);
		laden.setHorizontalZentriert(true);
		optionen.setHorizontalZentriert(true);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// füllt automatisch den ganzen Bildschirm
		back.draw(0, 0, container.getWidth(), container.getHeight());

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
