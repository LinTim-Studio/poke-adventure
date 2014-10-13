package pokeAdventure.state.menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;
import pokeAdventure.einstellungen.Einstellungen;
import pokeAdventure.interfaces.Action;
import pokeAdventure.util.SpriteManager;

public class Optionen extends BasicGameState {

	Slider soundVolumeSlider;
	ButtonArray btns;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {

		int x = Main.getWidth() / 2;

		soundVolumeSlider = new Slider(x, Main.getHeight() / 3, Main.getWidth() / 3, Main.getHeight() / 10, 0, (int) (Einstellungen.getSoundVolume() * 100), 100);
		soundVolumeSlider.setHorizontalZentriert(true);

		MenuButton back = new MenuButton(x, Main.getHeight() / 3 * 2, SpriteManager.btnSpeichern, SpriteManager.btnSpeichernOver, new Action() {
			public void action() {
				Einstellungen.setSoundVolume(soundVolumeSlider.getValue() / (float) 100);
				System.out.println(Einstellungen.getSoundVolume());
				Einstellungen.save();
				game.enterState(Main.menuID);
			}
		});

		btns = new ButtonArray(soundVolumeSlider, back);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		btns.render();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		btns.update(container.getInput());
	}

	@Override
	public int getID() {
		return Main.optionenID;
	}

}
