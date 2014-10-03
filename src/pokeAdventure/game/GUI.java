package pokeAdventure.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.Main;

public class GUI {
	
	public GUI() {
	}

	public void render(GameContainer container, Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(10, 10, 10, 100));
		int x = Main.getWidth()/10;
		int y = Main.getHeight()/10;
		g.fillRect(x, y, Main.getWidth() - 2 * x,  Main.getHeight() - 2 * y);
	}

	public void update(GameContainer container, int delta, TiledMap map) {
		// TODO Auto-generated method stub
	}

}
