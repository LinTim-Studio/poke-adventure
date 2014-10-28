package pokeAdventure.util;

import org.newdawn.slick.tiled.TiledMap;

public abstract class MapUtils {

	private static int defaultLayer = 0;

	public static String getProperty(TiledMap map, float x, float y, String name) {
		try {
			return map.getTileProperty(map.getTileId((int) (x / map.getTileWidth()), (int) (y / map.getTileHeight()), defaultLayer), name, "Null");
		} catch (Exception e) {
			return "Null";
		}
	}

}
