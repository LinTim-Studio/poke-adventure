package pokeAdventure.game;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;
import pokeAdventure.spieler.Spieler;
import pokeAdventure.spieler.Geschlecht;
import pokeAdventure.state.menu.MenuButton;
import pokeAdventure.util.TextLoader;

public class GameStart extends BasicGameState {

	private static TrueTypeFont t;

	private MenuButton maedchen, junge, anders;

	private float alpha;
	private int fort;
	private String[] text;
	private TextField textField;
	private Image papierbg, prof, pika;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {

		// soll nur einmal aufgerufen werden
		if (t == null) {
			Font font = new Font("Lucida Handwriting", Font.BOLD, 20);
			t = new TrueTypeFont(font, false);
		}
		fort = 0;

		junge = new MenuButton(50, 150, new Image("res/menu/buttons/neuesSpiel.png"), new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().geschlecht = Geschlecht.maennlich;
			}
		});

		maedchen = new MenuButton(50, 200, new Image("res/menu/buttons/neuesSpiel.png"), new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().geschlecht = Geschlecht.weiblich;
			}
		});
		
		anders = new MenuButton(50, 250, new Image("res/menu/buttons/neuesSpiel.png"), new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().geschlecht = Geschlecht.anders;
			}
		});

		papierbg = new Image("res/gameStart/Papierbg.png");
		prof = new Image("res/gameStart/prof.png");
		pika = new Image("res/gameStart/025.png");

		textField = new TextField(container, t, 50, 150, 250, 25);

		text = (TextLoader.loadFileToStrings("res/text/einleitungstext.txt", "//"));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.drawImage(papierbg, 0, 0);
		g.drawImage(prof, 475, 125);

		if (fort >= 0 && fort < text.length)
			write(text[fort]);

		if (fort >= 3) {
			Image im = pika.copy();
			if (alpha < 1.0f) {
				im.setAlpha(alpha);
			}
			g.drawImage(im, 350, 300);
		}

		if (fort == text.length - 1) {
			junge.zeichneButton();
			maedchen.zeichneButton();
			anders.zeichneButton();
		} else if (fort == text.length) {
			write("Du bist also ein " + Spieler.getInstance().getGeschlechtsBezeichnung() + ". Und wie ist dein Name?");
			textField.render(container, g);
		} else if (fort == text.length + 1)
			write("Sch\u00F6n dich kennenzulernen " + Spieler.getInstance().name + ".");
		else if (fort == text.length + 2)
			write("Ah, jetzt f\u00e4llt es mir wieder ein. Du bist gerade in meine Heimatstadt Elysion gezogen. Du kannst mich gerne mal besuchen kommen.");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input in = container.getInput();
		// isMousePresssed darf nur einmal aufgerufen werden
		boolean mouseDown = in.isMousePressed(0);
		boolean enterDown = in.isKeyPressed(Keyboard.KEY_RETURN);

		if ((mouseDown||enterDown) && ((fort < text.length - 1) || (fort > text.length))) {
			fort++;
		}
		if (fort >= 3) {
			alpha += 0.01;
		}
		if (fort == text.length - 1) {
			junge.update(in.getMouseX(), in.getMouseY(), mouseDown);
			maedchen.update(in.getMouseX(), in.getMouseY(), mouseDown);
			anders.update(in.getMouseX(), in.getMouseY(), mouseDown);
		} else if (fort == text.length) {
			textField.setFocus(true);
			if (enterDown && (textField.getText() != null && textField.getText().length() > 0)) {
				Spieler.getInstance().name = textField.getText();
				fort++;
			}
		}

		if (fort > text.length + 2)
			game.enterState(Main.gameID);
	}

	@Override
	public int getID() {
		return Main.gameStartID;
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
