package pokeAdventure.mob;

import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.PathFindingContext;

public class MovingContext implements PathFindingContext {

	Mover mover;
	int x, y;
	
	public MovingContext(Mover mover, int x, int y) {
		this.mover = mover;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Mover getMover() {
		return mover;
	}

	@Override
	public int getSourceX() {
		return x;
	}

	@Override
	public int getSourceY() {
		return y;
	}

	@Override
	public int getSearchDistance() {
		return -1;
	}

}
