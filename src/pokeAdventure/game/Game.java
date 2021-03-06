package pokeAdventure.game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;
import pokeAdventure.mob.spieler.Spieler;
import pokeAdventure.util.Karte;
import pokeAdventure.util.SpriteManager;

public class Game extends BasicGameState {
	
	KartenManager kartenManager;
	Vector2f mapOffset;

	GUI gui;
	boolean showGUI;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		kartenManager = new KartenManager();
		gui = new GUI(container);
		mapOffset = new Vector2f(0, 0);
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
		kartenManager.getKarte().render(container, g, mapOffset);
		Spieler.getInstance().render(container, g, mapOffset);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Main.allgemeinesUpdate(container, delta);

		if (container.getInput().isKeyPressed(Keyboard.KEY_SPACE)) {
			showGUI = !showGUI;
		}

		Karte map = kartenManager.getKarte();

		if (showGUI) {
			gui.update(container, delta, map);
		} else {
			SpriteManager.update();
			Spieler.getInstance().update(container, delta, map);
			kartenManager.update(container, delta);

			/**
			 * Der Spieler wird immer in der Mitte angezeigt, ausser wenn
			 * dadurch Elemente ausserhalb der Karte angezeigt werden, dann
			 * verschiebt er sich
			 * 
			 * Man koennte das natuerlich auch schoener schreiben, aber ich
			 * wollte das in zwei Zeilen machen ;)
			 */
			mapOffset = Spieler.getInstance().getPosition().negateLocal().add(new Vector2f(Main.getWidth() / 2, Main.getHeight() / 2));
			mapOffset.set((mapOffset.x > 0) ? 0 : (mapOffset.x < Main.getWidth() - map.getWidth() * map.getTileWidth()) ? Main.getWidth() - map.getWidth() * map.getTileWidth() : mapOffset.x, (mapOffset.y > 0) ? 0 : (mapOffset.y < Main.getHeight() - map.getHeight() * map.getTileHeight()) ? Main.getHeight() - map.getHeight() * map.getTileHeight() : mapOffset.y);

		}
	}

	@Override
	public int getID() {
		return Main.gameID;
	}

}
