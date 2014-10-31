package pokeAdventure.mob.person;

import static pokeAdventure.Main.rand;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import pokeAdventure.mob.MehrfachBild;
import pokeAdventure.mob.Mob;
import pokeAdventure.util.Karte;

public class Person extends Mob {

	private Path path;
	private float index;

	public Person(Vector2f pos, MehrfachBild bild) {
		super(pos, bild);
	}

	@Override
	protected void updateLaufen(Input in, int delta, Karte map) {

		if (path != null) {
			if (path.getLength() == (int) index) {
				path = null;
			} else {
				float a = 0.1f;
				dx = path.getX((int) index) * map.getWidth() - position.x;
				dy = path.getY((int) index) * map.getHeight() - position.y;
				dx *= a;
				dy *= a;
				index += a;
			}
		} else {
			AStarPathFinder pathfinder = new AStarPathFinder(map, map.getHeight() * map.getWidth(), false);
			path = pathfinder.findPath(this, map.getTileX(position.x), map.getTileY(position.y), rand.nextInt(map.getWidth()), rand.nextInt(map.getHeight()));
			index = 0;
		}

	}
}
