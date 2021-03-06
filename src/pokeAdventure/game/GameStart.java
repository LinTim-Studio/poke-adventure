package pokeAdventure.game;

import java.awt.Font;

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
import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.interfaces.Action;
import pokeAdventure.mob.spieler.Geschlecht;
import pokeAdventure.mob.spieler.Spieler;
import pokeAdventure.state.menu.ButtonArray;
import pokeAdventure.state.menu.MenuButton;
import pokeAdventure.util.SpriteManager;
import pokeAdventure.util.TextLoader;

public class GameStart extends BasicGameState {

	private static TrueTypeFont t;

	private MenuButton maedchen, junge, anders;

	private float alpha;
	private int fort;
	private String[] text;
	private TextField textField;
	private ButtonArray btns;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {

		// soll nur einmal aufgerufen werden
		if (t == null) {
			Font font = new Font("Lucida Handwriting", Font.BOLD, 20);
			t = new TrueTypeFont(font, false);
		}
		fort = 0;

		junge = new MenuButton(50, 150, SpriteManager.geschlechtJunge, SpriteManager.geschlechtJungeOver, new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().setGeschlecht(Geschlecht.maennlich);
			}
		});

		maedchen = new MenuButton(50, 220, SpriteManager.geschlechtMaedchen, SpriteManager.geschlechtMaedchenOver, new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().setGeschlecht(Geschlecht.weiblich);
			}
		});

		anders = new MenuButton(50, 290, SpriteManager.geschlechtAnders, SpriteManager.geschlechtAndersOver, new Action() {
			@Override
			public void action() {
				fort = text.length;
				Spieler.getInstance().setGeschlecht(Geschlecht.anders);
			}
		});

		btns = new ButtonArray(junge, maedchen, anders);

		textField = new TextField(container, t, 50, 150, 250, 25);

		text = (TextLoader.loadFileToStrings("res/text/einleitungstext.txt", "//"));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.drawImage(SpriteManager.papierbg, 0, 0);
		g.drawImage(SpriteManager.prof, 475, 125);

		if (fort >= 0 && fort < text.length)
			write(text[fort]);

		if (fort >= 3) {
			Image im = SpriteManager.pikachu.copy();
			if (alpha < 1.0f) {
				im.setAlpha(alpha);
			}
			g.drawImage(im, 350, 300);
		}

		if (fort == text.length - 1) {
			btns.render();
		} else if (fort == text.length) {
			write("Du bist also ein " + Spieler.getInstance().getGeschlechtsBezeichnung() + ". Und wie ist dein Name?");
			textField.render(container, g);
		} else if (fort == text.length + 1)
			write("Sch\u00F6n dich kennenzulernen " + Spieler.getInstance().getName() + ".");
		else if (fort == text.length + 2)
			write("Ah, jetzt f\u00e4llt es mir wieder ein. Du bist gerade in meine Heimatstadt Elysion gezogen. Du kannst mich gerne mal besuchen kommen.");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Main.allgemeinesUpdate(container, delta);
		Input in = container.getInput();
		// isMousePresssed darf nur einmal aufgerufen werden
		boolean mousePressed = in.isMousePressed(0);

		if (fort == text.length - 1) {
			btns.update(in.getMouseX(), in.getMouseY(), mousePressed, in);
		} else if (fort == text.length) {
			textField.setFocus(true);
			if (Tastenbelegung.isPressed(in, Taste.Enter) && (textField.getText() != null && textField.getText().length() > 0)) {
				Spieler.getInstance().setName(textField.getText());
				fort++;
			}
		} else if ((fort < text.length - 1) || (fort > text.length)) {
			if ((mousePressed || Tastenbelegung.isPressed(in, Taste.Enter))) {
				fort++;
			}
		}

		// algemeine
		if (fort >= 3) {
			alpha += 0.01;
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
