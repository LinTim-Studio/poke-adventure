package pokeAdventure.util;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.GroupObject;
import org.newdawn.slick.tiled.ObjectGroup;
import org.newdawn.slick.tiled.TiledMapPlus;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import pokeAdventure.game.KartenManager;
import pokeAdventure.game.entities.EntityManager;
import pokeAdventure.mob.Mob;
import pokeAdventure.mob.spieler.Spieler;
import pokeAdventure.util.error.Fehlermelder;

public class Karte extends TiledMapPlus implements TileBasedMap {

	private static int defaultLayer = 0;
	private float wolke1, wolke2, wolkenSpeed = 0.2f;
	private EntityManager entityManager;

	public Karte(String ref) throws SlickException {
		super(ref);
		wolke1 = 0;
		wolke2 = -SpriteManager.wolken.getWidth();
		entityManager = new EntityManager();
	}

	@Override
	public int getWidthInTiles() {
		return getWidth();
	}

	@Override
	public int getHeightInTiles() {
		return getHeight();
	}

	public int getTotalWidth() {
		return getWidth() * getTileWidth();
	}

	public int getTotalHeight() {
		return getHeight() * getTileHeight();
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

	/**
	 * 
	 * @param mob
	 * @param groupName
	 * @return Das erstgefundene Object
	 */
	public GroupObject getObject(Mob mob, String groupName, String type) {
		try {
			ObjectGroup objGroup = getObjectGroup(groupName);
			ArrayList<Vector2i> mobTiles = containsTiles(mob);
			for (GroupObject obj : objGroup.getObjectsOfType(type)) {
				for (Vector2i vec : containsTiles(obj.x, obj.y, obj.width, obj.height)) {
					if (mobTiles.contains(vec)) {
						return obj;
					}
				}
			}
		} catch (NullPointerException e) {
			// Object nicht gefunden, einfach weitermachen ...
		}
		return null;

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
		ArrayList<Vector2i> tiles = containsTiles(center.x - mob.getBox().links, center.y - mob.getBox().oben, mob.getWidth(), mob.getHeight());

		for (Vector2i tile : tiles) {
			if (isSolid(tile.x, tile.y)) {
				return true;
			}
		}

		return false;
	}

	private ArrayList<Vector2i> containsTiles(Mob mob) {
		return containsTiles(mob.getPosition().x - mob.getBox().links, mob.getPosition().y + mob.getBox().oben, mob.getWidth(), mob.getHeight());
	}

	private ArrayList<Vector2i> containsTiles(float x, float y, float width, float height) {
		int leftTile = getTileX(x);
		int rightTile = getTileX(x + width);
		int topTile = getTileY(y);
		int bottomTile = getTileY(y + height);

		ArrayList<Vector2i> tiles = new ArrayList<Vector2i>();
		for (int xT = leftTile; xT <= rightTile; xT++) {
			for (int yT = topTile; yT <= bottomTile; yT++) {
				tiles.add(new Vector2i(xT, yT));
			}
		}
		return tiles;

	}

	private boolean isSolid(int xT, int yT) {
		return getProperty(xT, yT, "solid").equals("true");
	}
	
	public void render(GameContainer container, Graphics g, Vector2f offset) {
		// Wolken:
		wolke1 += wolkenSpeed;
		wolke2 += wolkenSpeed;
		SpriteManager.wolken.draw((offset.x + wolke1), offset.y);
		SpriteManager.wolken.draw((offset.x + wolke2 + 1), offset.y); // +1 für Überlappen
		if (wolke1 > getTotalWidth()) {
			wolke1 = wolke2 - SpriteManager.wolken.getWidth();
		}
		if (wolke2 > getTotalWidth()) {
			wolke2 = wolke1 - SpriteManager.wolken.getWidth();
		}
		super.render((int) offset.x, (int) offset.y);
		
		// Entities
		entityManager.render(container, g, offset);
	}

	public void update(KartenManager kartenManager, GameContainer container, int delta) {
		updateWegpunkte(Spieler.getInstance(), kartenManager);
		entityManager.update(container, delta, this);
	}
	
	private void updateWegpunkte(Spieler spieler, KartenManager kartenManager) {
		GroupObject obj = getObject(spieler, "Wege", "Wegpunkt");
		if (obj != null) {
			try {
				int geheNach = Integer.parseInt(obj.name);
				kartenManager.wechselKarte(geheNach);
			} catch (NumberFormatException e) {
				Fehlermelder.melde(e, "Falsche Objectname!");
			}
		}
	}

	public Vector2i sucheAnkunftVon(int id) {
		try {
			for (GroupObject obj : getObjectGroup("Wege").getObjectsOfType("Ankunft")) {
				if (Integer.parseInt(obj.name) == id) {
					return new Vector2i(obj.x, obj.y);
				}
			}
		} catch (NullPointerException e) {
			// nicht gefunden ... 
			System.err.println("Keine Verbindung!");
		}
		return null;
	}

}
