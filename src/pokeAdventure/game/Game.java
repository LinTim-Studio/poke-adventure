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
import pokeAdventure.mob.spieler.Spieler;

public class Game extends BasicGameState {

	TiledMap map;
	Vector2f mapOffset, spielerOffset;

	GUI gui;
	boolean showGUI;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		map = new TiledMap("res/map/map.tmx");
		gui = new GUI(container);
		mapOffset = new Vector2f(0, 0);
		spielerOffset = new Vector2f(0, 0);
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
		Spieler.getInstance().render(container, g, spielerOffset);
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
		} else {
			Spieler.getInstance().update(container, delta, map);
			mapOffset = Spieler.getInstance().getPosition().negateLocal();
			
			/**
			 * Der Spieler wird immer in der Mitte angezeigt, ausser wenn dadurch 
			 * Elemente ausserhalb der Karte angezeigt werden, dann verschiebt er sich
			 * 
			 * Man koennte das natuerlich auch schoener schreiben, aber ich wollte das in zwei Zeilen machen ;)
			 */
			spielerOffset.set(
					(mapOffset.x > 0) ? -mapOffset.x : (mapOffset.x < Main.getWidth() - map.getWidth() * map.getTileWidth() ) ?  Main.getWidth() - map.getWidth() * map.getTileWidth() - mapOffset.x: 0,
					(mapOffset.y > 0) ? -mapOffset.y : (mapOffset.y < Main.getHeight() - map.getHeight() * map.getTileHeight()) ?  Main.getHeight() - map.getHeight() * map.getTileHeight() - mapOffset.y : 0);
					
			mapOffset.set(
					(mapOffset.x > 0) ? 0 : (mapOffset.x < Main.getWidth() - map.getWidth() * map.getTileWidth() ) ?  Main.getWidth() - map.getWidth() * map.getTileWidth(): mapOffset.x,
					(mapOffset.y > 0) ? 0 : (mapOffset.y < Main.getHeight() - map.getHeight() * map.getTileHeight()) ?  Main.getHeight() - map.getHeight() * map.getTileHeight() : mapOffset.y);
			
		}
	}

	@Override
	public int getID() {
		return Main.gameID;
	}

}
