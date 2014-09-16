package pokeAdventure.state;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;

public class Ladebildschirm extends BasicGameState {

	/** The next resource to load */
	private DeferredResource nextResource;
	/** Die Hintergrungfarbe */
	private Color bg;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		bg = new Color(0x700a00);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		int width = Main.getWidth();
		int height = Main.getHeight();
		g.setColor(bg);
		g.fillRect(0, 0, width, height);

		int total = LoadingList.get().getTotalResources();
		int left = LoadingList.get().getRemainingResources();
		int mult = width / total / 5 * 3;
		int barHeight = height / 20;
		int border = 5;
		total *= mult;
		left *= mult;

		g.setColor(Color.white);
		g.setLineWidth(2);
		g.drawRoundRect(width / 2 - total / 2 - border, height / 2 - barHeight / 2 - border, total + 2 * border, barHeight + 2 * border, 40);
		g.fillRoundRect(width / 2 - total / 2, height / 2 - barHeight / 2, total - left, barHeight, 40);

		// show the percentage
		int perc = 100 - 100 * left / total;
		g.drawString(perc + "%", width/2, height/2 - 2 * barHeight);
		
		// show thing to be loaded
		String current = "";
		if (nextResource != null)
			current = nextResource.getDescription();

		g.drawString(current, width / 2 - total / 2, height / 2 + barHeight + border);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
			} catch (IOException e) {
				throw new SlickException("Failed to load: " + nextResource.getDescription(), e);
			}
			nextResource = null;
		}
		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			game.enterState(Main.menuID);
		}
	}

	@Override
	public int getID() {
		return Main.ladebildschirm;
	}

}
