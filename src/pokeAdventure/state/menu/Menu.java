package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;

public class Menu extends BasicGameState {
	
	MenuButton newGame;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		newGame = new MenuButton(100, 100, "res/menu/neuesSpiel.png", "res/menu/neuesSpielHighlight.png", new Action() {
			@Override
			public void action() {
				System.out.println("Neues Spiel!");
			}
		});
		
//		MenuButton laden = new MenuButton();
//		MenuButton optionen= new MenuButton();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.green);
		g.fillRect(0, 0, container.getScreenWidth(), container.getScreenHeight());
		
		Image back= new Image("res/menu/menuHintergrund.png");
		back.draw(0,0);
		
		newGame.zeichneButton();
//		laden.zeichneButton();
//		optionen.zeichneButton();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input in = container.getInput();
		int x = in.getMouseX();
		int y = in.getMouseY();
		boolean mouseDown = in.isMousePressed(0);
		newGame.update(x, y, mouseDown);
//		laden.update();
//		optionen.update();
	}

	@Override
	public int getID() {
		return Main.menuID;
	}

}
