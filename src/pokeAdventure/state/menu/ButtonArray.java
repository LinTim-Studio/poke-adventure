package pokeAdventure.state.menu;

import java.util.ArrayList;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.util.SoundManager;

public class ButtonArray {

	ArrayList<MenuButton> btns;
	int rows, cols;
	Vector2f selected;
	
	public ButtonArray(MenuButton ... btns) {
		this(btns.length, 1, btns);
	}

	public ButtonArray(int rows, int cols, MenuButton ... btns) {
		selected = new Vector2f(0, 0);
		this.rows = rows;
		this.cols = cols;
		this.btns = new ArrayList<MenuButton>();
		for (MenuButton menuButton : btns) {
			this.btns.add(menuButton);
		}
	}

	public void render() {
		for (MenuButton menuButton : btns) {
			menuButton.zeichneButton();
		}
	}
	
	public void update(Input in) {
		int mx = in.getMouseX();
		int my = in.getMouseY();
		boolean mouseBtnDown = in.isMouseButtonDown(0);
		this.update(mx, my, mouseBtnDown, in);
	}

	public void update(int mx, int my, boolean mouseBtnDown, Input in) {
		
		Vector2f before = selected.copy();
		
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				btns.get(x + y * cols).update(mx, my, mouseBtnDown);
				if (btns.get(x + y * cols).istUeber()) {
					selected.set(x, y);
				}
			}
		}

		if (Tastenbelegung.isPressed(in, Taste.Runter)) {
			selected.y++;
			if (selected.y >= rows)
				selected.y = 0;
		}

		if (Tastenbelegung.isPressed(in, Taste.Hoch)) {
			selected.y--;
			if (selected.y < 0)
				selected.y = rows - 1;
		}

		if (Tastenbelegung.isPressed(in, Taste.Links)) {
			selected.x--;
			if (selected.x < 0)
				selected.x = cols - 1;
		}

		if (Tastenbelegung.isPressed(in, Taste.Rechts)) {
			selected.x++;
			if (selected.x >= cols)
				selected.x = 0;
		}

		if (Tastenbelegung.isPressed(in, Taste.Enter)) {
			btns.get((int) (selected.x + selected.y * cols)).doAction();	
		}

		btns.get((int) (selected.x + selected.y * cols)).setUeber(true);
		
		if (!selected.equals(before))
			SoundManager.beep.play();

	}

}
