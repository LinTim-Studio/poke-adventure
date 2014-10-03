package pokeAdventure.transitions;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import pokeAdventure.util.error.Fehlermelder;

public class LightningTransition implements Transition {
	private static int frameCount = 3;

	private static Image[] frames;
	private static boolean initialized;

	private int time, x;
	private boolean done;
	private Random rand;

	public LightningTransition(int time) {
		this.time = time;
		staticInit();
	}

	@Override
	public void update(StateBasedGame game, GameContainer container, int delta) throws SlickException {
		time -= delta;

		x = rand.nextInt(container.getWidth()) - container.getWidth() / 2;

		if (time < 0)
			done = true;
	}

	@Override
	public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {

	}

	@Override
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		for (int i = 0; i < frameCount; i++) {
			frames[i].draw(x, 0, container.getWidth(), container.getHeight());
		}

	}

	@Override
	public boolean isComplete() {
		return done;
	}

	private static boolean staticInit() {
		if (initialized) {
			return false;
		}
		frames = new Image[frameCount];
		for (int i = 1; i <= frameCount; i++) {
			try {
				frames[i - 1] = new Image("lightning/lightning" + i + ".png");
			} catch (SlickException e) {
				Fehlermelder.melde(e);
			}
		}
		initialized = true;
		return true;
	}

	@Override
	public void init(GameState firstState, GameState secondState) {
		rand = new Random();
	}

}
