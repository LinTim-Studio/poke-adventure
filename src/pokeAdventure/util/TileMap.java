package pokeAdventure.util;

import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class TileMap implements TileBasedMap {

	private TiledMapPlus map;
	
	public TileMap(TiledMapPlus map) {
		this.map = map;
	}

	@Override
	public int getWidthInTiles() {
		return map.getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return map.getHeight();
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return MapUtils.getProperty(map, tx, ty, "solid").equals("true");
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return MapUtils.getIntProperty(map, tx, ty, "cost");
	}

}
