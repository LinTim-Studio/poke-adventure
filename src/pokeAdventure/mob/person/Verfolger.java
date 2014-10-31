package pokeAdventure.mob.person;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import pokeAdventure.mob.MehrfachBild;
import pokeAdventure.mob.spieler.Spieler;
import pokeAdventure.util.Karte;

public class Verfolger extends Person {

	Vector2f target;
	Path path;
	AStarPathFinder aStarPathFinder;
	private float index;

	public Verfolger(Vector2f pos, MehrfachBild bild) {
		super(pos, bild);
	}

	@Override
	protected void updateLaufen(Input in, int delta, Karte map) {

		dx = 0;
		dy = 0;

		Vector2f testTarget = map.getTile(Spieler.getInstance().getPosition());
		if (target == null) {
			target = testTarget;
			recalcPath(map);
		} else {
			if (!target.equals(testTarget)) {
				target = testTarget;
				recalcPath(map);
			}
		}

		if (path != null && path.getLength() > (int) index) {

			dx = path.getX((int) index) * map.getTileWidth() - position.x;
			dy = path.getY((int) index) * map.getTileHeight() - position.y;
			
			dx *= 0.1;
			dy *= 0.1;
			
			index += 0.1;
			
		} else {
			recalcPath(map);
		}
	}

	private void recalcPath(Karte map) {
		if (aStarPathFinder == null)
			aStarPathFinder = new AStarPathFinder(map, map.getWidth() * map.getHeight(), false);

		path = aStarPathFinder.findPath(this, map.getTileX(position.x), map.getTileY(position.y), (int) target.x, (int) target.y);
		index = 0;
	}

}
