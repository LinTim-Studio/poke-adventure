package pokeAdventure.state.menu;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;

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
		
		if (Tastenbelegung.isPressed(in, Taste.Runter)) {
			selected++;
			if (selected >= btns.size())
				selected = 0;
		}
		
		if (Tastenbelegung.isPressed(in, Taste.Hoch)) {
			selected--;
			if (selected < 0)
				selected = btns.size() - 1;
		}
		
		if (Tastenbelegung.isPressed(in, Taste.Enter)) {
			btns.get(selected).doAction();
		}
		
		btns.get(selected).setUeber(true);
		
		
	}
	
}
