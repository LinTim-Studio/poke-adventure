package pokeAdventure.util;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.tiled.TiledMapPlus;

public abstract class MapUtils {

	private static int defaultLayer = 0;

	public static String getProperty(TiledMap map, int x, int y, String name) {
		try {
			return map.getTileProperty(map.getTileId(x, y, defaultLayer), name, "Null");
		} catch (Exception e) {
			return "Null";
		}
	}

	public static float getIntProperty(TiledMapPlus map, int x, int y, String name) {
		try {
			return Integer.parseInt(map.getTileProperty(map.getTileId(x, y, defaultLayer), name, "0"));
		} catch (Exception e) {
			return 0;
		}
	}

	public static int getTileX(float x, TiledMap map) {
		return (int) (x / map.getTileWidth());
	}

	public static int getTileY(float y, TiledMap map) {
		return (int) (y / map.getTileHeight());
	}
}
