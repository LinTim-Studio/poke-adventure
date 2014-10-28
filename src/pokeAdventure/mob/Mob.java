package pokeAdventure.mob;

import static pokeAdventure.mob.Richtung.norden;
import static pokeAdventure.mob.Richtung.nordosten;
import static pokeAdventure.mob.Richtung.nordwesten;
import static pokeAdventure.mob.Richtung.osten;
import static pokeAdventure.mob.Richtung.sueden;
import static pokeAdventure.mob.Richtung.suedosten;
import static pokeAdventure.mob.Richtung.suedwesten;
import static pokeAdventure.mob.Richtung.westen;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.util.MapUtils;

public abstract class Mob {

	private Vector2f position;
	protected int dx, dy;
	protected Richtung laufrchtung;
	protected MehrfachBild bild;

	public Mob(Vector2f pos, MehrfachBild bild) {
		position = new Vector2f(pos);
		this.bild = bild;
		this.laufrchtung = norden;
	}

	public void render(GameContainer container, Graphics g, Vector2f offset) {
		bild.get(laufrchtung, (dx != 0 || dy != 0)).drawCentered(position.x + offset.x, position.y + offset.y);
	}
	
	public void update(GameContainer container, int delta, TiledMap map) {
		updateLaufen(container.getInput(), delta, map);
		move(dx, dy, map);
		updateLaufrichtung();
	}
	
	protected abstract void updateLaufen(Input in, int delta, TiledMap map);

	protected void updateLaufrichtung() {
		if (dx < 0 && dy < 0)
			laufrchtung = nordwesten;
		else if (dx < 0 && dy == 0)
			laufrchtung = westen;
		else if (dx < 0 && dy > 0)
			laufrchtung = suedwesten;
		else if (dx == 0 && dy < 0)
			laufrchtung = norden;
		else if (dx == 0 && dy > 0)
			laufrchtung = sueden;
		else if (dx > 0 && dy < 0)
			laufrchtung = nordosten;
		else if (dx > 0 && dy == 0)
			laufrchtung = osten;
		else if (dx > 0 && dy > 0)
			laufrchtung = suedosten;

	}

	protected void move(int dx, int dy, TiledMap map) {
		// TODO Kollision
		

		if (MapUtils.getProperty(map, position.x + dx, position.y + 0 , "solid").equals("true")) {
			dx = 0;
		}
		if (MapUtils.getProperty(map, position.x + 0 , position.y + dy, "solid").equals("true")) {
			dy = 0;
		}
		if ((dx != 0 && dy !=0) && MapUtils.getProperty(map, position.x + dx, position.y + dy, "solid").equals("true")) {
			dx = 0;
			dy = 0;
		}
		
		int mapWidth = map.getWidth() * map.getTileWidth();
		int mapHeight = map.getHeight() * map.getTileHeight();
		
		if (position.x + dx < 0) {
			dx = 0;
		}
		if (position.x + dx > mapWidth) {
			dx = 0;
		}
		if (position.y + dy < 0) {
			dy = 0;
		}
		if (position.y + dy > mapHeight) {
			dy = 0;
		}
		
		this.position.x += dx;
		this.position.y += dy;
			
	}

	public Vector2f getPosition() {
		return position.copy();
	}

}
