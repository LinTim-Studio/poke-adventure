package pokeAdventure.game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.SelectTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;
import org.newdawn.slick.TrueTypeFont;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;

public class gameStart extends BasicGameState {



	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException 
	{
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		Font font= new Font("Verdana", Font.BOLD, 32);
		TrueTypeFont t= new TrueTypeFont(font ,false);
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		
		t.drawString(40, 40, "Willkommen zu Pokemon ... ");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		
	}

	@Override
	public int getID() {
		return Main.gameStartID;
	}

}

