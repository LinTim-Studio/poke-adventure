package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;

public class Menu extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		MenuButton newGame = new MenuButton();
		MenuButton laden = new MenuButton();
		MenuButton optionen= new MenuButton();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.green);
		g.fillRect(0, 0, container.getScreenWidth(), container.getScreenHeight());
		
		newGame.zeichneButton();
		laden.zeichneButton();
		optionen.zeichneButton();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		newGame.update();
		laden.update();
		optionen.update();
	}

	@Override
	public int getID() {
		return Main.menuID;
	}

}
