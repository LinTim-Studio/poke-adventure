package pokeAdventure.util;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import pokeAdventure.mob.Mob;

public class Karte extends TiledMapPlus implements TileBasedMap {

	private static int defaultLayer = 0;

	public Karte(String ref) throws SlickException {
		super(ref);
	}

	@Override
	public int getWidthInTiles() {
		return getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return getHeight();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// hier muss nichts gemacht werden
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return isSolid(tx, ty);
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return getIntProperty(tx, ty, "cost");
	}

	public String getProperty(int x, int y, String name) {
		try {
			return getTileProperty(getTileId(x, y, defaultLayer), name, "Null");
		} catch (Exception e) {
			return "Null";
		}
	}

	public float getIntProperty(int x, int y, String name) {
		try {
			return Integer.parseInt(getTileProperty(getTileId(x, y, defaultLayer), name, "0"));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public Vector2f getTile(Vector2f pos) {
		return new Vector2f(getTileX(pos.x), getTileY(pos.y));
	}

	public int getTileX(float x) {
		return (int) (x / getTileWidth());
	}

	public int getTileY(float y) {
		return (int) (y / getTileHeight());
	}

	public boolean blocked(Mob mob, float dx, float dy) {

		Vector2f center = mob.getPosition().add(new Vector2f(dx, dy));

		int leftTile = getTileX(center.x - mob.getBox().links);
		int rightTile = getTileX(center.x + mob.getBox().rechts);
		int topTile = getTileY(center.y - mob.getBox().oben);
		int bottomTile = getTileY(center.y + mob.getBox().unten);

		for (int xT = leftTile; xT <= rightTile; xT++) {
			for (int yT = topTile; yT <= bottomTile; yT++) {
				if (isSolid(xT, yT)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isSolid(int xT, int yT) {
		return getProperty(xT, yT, "solid").equals("true");
	}

}
