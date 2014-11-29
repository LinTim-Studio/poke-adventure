package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;

public class MenuLaden extends BasicGameState {
	
	Image back;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// test render
		g.setColor(Color.red);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Main.allgemeinesUpdate(container, delta);
	}

	@Override
	public int getID() {
		return Main.ladenID;
	}

}
