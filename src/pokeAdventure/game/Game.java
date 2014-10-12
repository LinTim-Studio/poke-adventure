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
		map = new TiledMap("res/map/map.tmx");
		gui = new GUI(container);
		mapOffset = new Vector2f(0,0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		if (showGUI) {
			gui.render(container, g);
		} else {
			renderScene(container, game, g);
		}
	}
	
	public void renderScene(GameContainer container, StateBasedGame game, Graphics g) {
		map.render((int) mapOffset.x, (int) mapOffset.y);
		Spieler.getInstance().render(container, g);
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
			mapOffset = Spieler.getInstance().getKoordinaten();
		}
	}

	@Override
	public int getID() {
		return Main.gameID;
	}

}
