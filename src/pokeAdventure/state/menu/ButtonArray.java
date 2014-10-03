package pokeAdventure.state.menu;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

public class ButtonArray {

	ArrayList<MenuButton> btns;
	int selected;
	
	public ButtonArray() {
		btns = new ArrayList<MenuButton>();
	}
	
	public void add(MenuButton menuButton) {
		btns.add(menuButton);
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
		
		for (int i = 0; i < btns.size(); i++) {
			btns.get(i).update(mx, my, mouseBtnDown);
			if (btns.get(i).istUeber()) {
				selected = i;
			}
		}
		
		if (in.isKeyPressed(Keyboard.KEY_DOWN)) {
			selected++;
			if (selected >= btns.size())
				selected = 0;
		}
		
		if (in.isKeyPressed(Keyboard.KEY_UP)) {
			selected--;
			if (selected < 0)
				selected = btns.size() - 1;
		}
		
		if (in.isKeyPressed(Keyboard.KEY_RETURN)) {
			btns.get(selected).doAction();
		}
		
		btns.get(selected).setUeber(true);
		
		
	}
	
}
