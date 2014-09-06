package pokeAdventure.state.menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import pokeAdventure.interfaces.Action;

public class MenuButton {
	public int y;
	public int x;
	public Image bild;
	public Image bildHighlight;

	public Action event;

	private boolean ueber;

	public MenuButton(int koorX, int koorY, String btnPath, String btnHighlightPath, Action aktion) throws SlickException {
		this(koorX, koorY, new Image(btnPath), new Image(btnHighlightPath), aktion);
	}

	public MenuButton(int koorX, int koorY, Image pic, Image picHighlight, Action aktion) {
		x = koorX;
		y = koorY;
		bild = pic;
		bildHighlight = picHighlight;
		event = aktion;
	}

	public void zeichneButton() {
		if (ueber)
			bildHighlight.draw(x, y);
		else
			bild.draw(x, y);
	}

	public void update(int mx, int my, boolean mouseBtnDown) {
		if (istUeber(mx, my)) {
			ueber = true;
			if (mouseBtnDown)
				event.action();
		} else
			ueber = false;

	}

	public boolean istUeber(int mx, int my) {
		if ((mx > x) && (mx < x + bild.getHeight()) && (my > y) && (my < y + bild.getWidth()))
			return true;
		else
			return false;
	}
}
