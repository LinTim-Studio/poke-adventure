package pokeAdventure.game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import pokeAdventure.state.menu.MenuButton;
import pokeAdventure.interfaces.Action;

import pokeAdventure.Main;

public class gameStart extends BasicGameState {

	private static TrueTypeFont t;
	private static int fort;
	private static MenuButton junge;
	private static MenuButton maedchen;

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException {
		// soll nur einmal aufgerufen werden
		if (t == null) {
			Font font = new Font("Lucida Handwriting", Font.BOLD, 20);
			t = new TrueTypeFont(font, false);
		}
		
		junge = new MenuButton(50,150,new Image("res/menu/buttons/neuesSpiel.png"),new Image("res/menu/buttons/neuesSpielHighlight.png"), new Action()
		{
			@Override
			public void action()
			{
				
			}
		});
		
		fort=1;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.drawImage(new Image("res/gameStart/Papierbg.png"), 0, 0);
		g.drawImage(new Image("res/gameStart/prof.png"), 475,125);
		switch(fort)
		{
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
		//Pokemon Bild einfügen
		break;
		case 5:
		write("Unsere Welt ist bevölkert von Wesen, die als");
		writeN("Pokemon bekannnt sind.");
		break;
		case 6:
		write("Wir Menschen leben friedlich mit den Pokemon zusammen,");
		writeN("sowohl als Freunde, als auch als Arbeitspartner.");
		break;
		case 7:
		write("Und manchmal kämpfen wir mit Pokemon gegen");
		writeN("andere Menschen mit ihren Pokemon.");
		break;
		case 8:
		write("Obwohl wir so viel mit Pokemon zu tun haben, wissen");
		writeN("wir nicht alles über sie.");
		break;
		case 9:
		write("Stattdessen gibts es viele Geheimnisse über Pokemon.");
		break;
		case 10:
		write("Ich versuche die Pokemon zu erforschen um die");
		writeN("letzten Gehimnisse über sie aufzudecken.");
		break;
		case 11:
		write("Das bin ich. Und wer bist du?");
		break;
		case 12:
		write("Bist du ein Junge oder ein Mädchen?");
		junge.zeichneButton();
		break;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		Input in= container.getInput();
		if((in.isMousePressed(0))&&(fort<12))
		{
			fort++;
			wait(50);
			return;
		}
		junge.update(in.getMouseX(), in.getMouseY(), in.isMousePressed(0));

	}

	@Override
	public int getID() {
		return Main.gameStartID;
	}
	
	void wait(int x)
	{
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void write(String s)
	{
		t.drawString(40, 40, s);
	}
	
	void writeN(String s)
	{
		t.drawString(40, 80, s);
	}

}
