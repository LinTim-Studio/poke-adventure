package pokeAdventure.mob.person;

import static pokeAdventure.Main.rand;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import pokeAdventure.mob.MehrfachBild;
import pokeAdventure.mob.Mob;
import pokeAdventure.util.MapUtils;
import pokeAdventure.util.TileMap;

public class Person extends Mob {

	private Path path;
	private float index;

	public Person(Vector2f pos, MehrfachBild bild) {
		super(pos, bild);
	}

	@Override
	protected void updateLaufen(Input in, int delta, TiledMapPlus map) {


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
			AStarPathFinder pathfinder = new AStarPathFinder(new TileMap(map), map.getHeight() * map.getWidth(), true);
			path = pathfinder.findPath(this, MapUtils.getTileX(position.x, map), MapUtils.getTileY(position.y, map), (int) (rand.nextFloat() * map.getWidth()), (int) (rand.nextFloat() * map.getHeight()));
			index = 0;
		}
		
		
		// if ((dx == 0 && dy == 0) || rand.nextInt(200) == 0) {
		// if (rand.nextBoolean()) {
		// dx = rand.nextInt(2) * 2 - 1;
		// dy = 0;
		// } else {
		// dx = 0;
		// dy = rand.nextInt(2) * 2 - 1;
		// }
		// dx *= delta * 0.15;
		// dy *= delta * 0.15;
		// }

	}
}
