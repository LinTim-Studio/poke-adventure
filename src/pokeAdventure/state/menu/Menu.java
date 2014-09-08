package pokeAdventure.state.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;

public class Menu extends BasicGameState {

	MenuButton newGame;
	MenuButton laden;
	MenuButton optionen;
	
	Image back;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Hintergrundbild laden
		back = new Image("res/menu/menuHintergrund.png");
		
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
			}
		});

		laden = new MenuButton(x, y + dy, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Laden!");
			}
		});

		optionen = new MenuButton(x, y + 2 * dy, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Optionen!");
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
