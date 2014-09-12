package pokeAdventure.game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;

public class gameStart extends BasicGameState {

	private static TrueTypeFont t;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		// soll nur einmal aufgerufen werden
		if (t == null) {
			Font font = new Font("Verdana", Font.BOLD, 32);
			t = new TrueTypeFont(font, false);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.setColor(Color.gray);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());

		t.drawString(40, 40, "Willkommen zu Pokemon ...");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

	}

	@Override
	public int getID() {
		return Main.gameStartID;
	}

}
