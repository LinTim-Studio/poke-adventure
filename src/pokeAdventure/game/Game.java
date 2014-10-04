package pokeAdventure.game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.Main;
import pokeAdventure.spieler.Spieler;

public class Game extends BasicGameState {

	TiledMap map;
	Vector2f mapOffset;

	GUI gui;
	boolean showGUI;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO load map
		gui = new GUI();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		//temp
		g.fillRect(0, 0, Main.getWidth(), Main.getHeight());
//		map.render((int) mapOffset.x, (int) mapOffset.y);
		Spieler.getInstance().render(container, g);
		if (showGUI)
			gui.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (container.getInput().isKeyPressed(Keyboard.KEY_TAB)) {
			container.setFullscreen(!container.isFullscreen());
		}
		
		if (container.getInput().isKeyPressed(Keyboard.KEY_SPACE)) {
			showGUI = !showGUI;
		}
		
		if (showGUI) {
			gui.update(container, delta, map);
		}
		else {
			Spieler.getInstance().update(container, delta, map);
		}
	}

	@Override
	public int getID() {
		return Main.gameID;
	}

}
