package pokeAdventure.game;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;
import pokeAdventure.state.menu.MenuButton;

public class gameStart extends BasicGameState {

	private static TrueTypeFont t;
	private static int fort;
	private static MenuButton junge;
	@SuppressWarnings("unused")
	private static MenuButton maedchen;
	private static String geschlecht;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		// soll nur einmal aufgerufen werden
		if (t == null) {
			Font font = new Font("Lucida Handwriting", Font.BOLD, 20);
			t = new TrueTypeFont(font, false);
		}
		fort = 1;

		junge = new MenuButton(50, 150, new Image("res/menu/buttons/neuesSpiel.png"), new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action() {
			@Override
			public void action() {
				fort=13;
				geschlecht="Junge";
			}
		});

		maedchen = new MenuButton(50, 200, new Image("res/menu/buttons/neuesSpiel.png"), new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action() {
			@Override
			public void action() {
				fort=13;
				geschlecht="Maedchen";
			}
		});
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.drawImage(new Image("res/gameStart/Papierbg.png"), 0, 0);
		g.drawImage(new Image("res/gameStart/prof.png"), 475, 125);
		switch (fort) {
		case 1:
			write("Willkommen in der Welt der Pokemon!");
			break;
		case 2:
			write("Mein Name ist .... ");
			break;
		case 3:
			write("Aber jeder nennt mich den Pokemon Professor.");
			break;
		case 4:
			write("Dies nennen wir ein 'Pokemon'.");
			// Pokemon Bild einfgen
			break;
		case 5:
			write("Unsere Welt ist bev\u00FClkert von Wesen, die als Pokemon bekannnt sind.");
			break;
		case 6:
			write("Wir Menschen leben friedlich mit den Pokemon zusammen, sowohl als Freunde, als auch als Arbeitspartner.");
			break;
		case 7:
			write("Und manchmal k\u00E4mpfen wir mit Pokemon gegen andere Menschen mit ihren Pokemon.");
			break;
		case 8:
			write("Obwohl wir so viel mit Pokemon zu tun haben, wissen wir nicht alles \u00FCber sie.");
			break;
		case 9:
			write("Stattdessen gibts es viele Geheimnisse ueber Pokemon.");
			break;
		case 10:
			write("Ich versuche die Pokemon zu erforschen um die letzten Geheimnisse \u00FCber sie aufzudecken.");
			break;
		case 11:
			write("Das bin ich. Und wer bist du?");
			break;
		case 12:
			write("Bist du ein Junge oder ein M\u00E4dchen?");
			junge.zeichneButton();
			maedchen.zeichneButton();
			break;
		case 13:
			write("Du bist also ein "+geschlecht);
			break;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		Input in = container.getInput();
		if ((in.isMousePressed(0)) && (fort < 12)) {
			fort++;
			wait(50);
			return;
		}
		
		if(fort==12)
		{
			junge.update(in.getMouseX(), in.getMouseY(), in.isMousePressed(0));
			maedchen.update(in.getMouseX(), in.getMouseY(), in.isMousePressed(0));
		}

	}

	@Override
	public int getID() {
		return Main.gameStartID;
	}

	void wait(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void write(String s) {
		autoWrite(s, 40, 40, 500);
		
	}

	/**
	 * Nach einer bestimmten Breite kommt ein Zeilenumsprung oder bei dem
	 * Newline Zeichen "\n"
	 * 
	 * @param line
	 * @param x
	 * @param y
	 * @param width
	 */
	private void autoWrite(String line, int x, int y, int width) {
		String[] words = line.split(" ");
		int nx = x;
		int ny = y;
		for (String s : words) {
			if (s.equals("\n")) {
				nx = x;
				ny += t.getLineHeight();
			} else {
				s += " ";
				if (nx + t.getWidth(s) > width) {
					nx = x;
					ny += t.getLineHeight();
				}
				t.drawString(nx, ny, s);
				nx += t.getWidth(s);
			}
		}
	}

}
