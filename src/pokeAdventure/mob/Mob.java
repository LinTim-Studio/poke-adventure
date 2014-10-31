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
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.Mover;

import pokeAdventure.util.Box;
import pokeAdventure.util.Karte;

public abstract class Mob implements Mover {

	protected Vector2f position;
	protected float dx, dy;
	protected Richtung laufrchtung;
	protected MehrfachBild bild;

	public Mob(Vector2f pos, MehrfachBild bild) {
		position = new Vector2f(pos);
		this.bild = bild;
		this.laufrchtung = norden;
	}

	public void render(GameContainer container, Graphics g, Vector2f offset) {
		getBild().drawCentered(position.x + offset.x, position.y + offset.y);
	}

	private Image getBild() {
		return bild.get(laufrchtung, (dx != 0 || dy != 0));
	}

	public void update(GameContainer container, int delta, Karte map) {
		updateLaufen(container.getInput(), delta, map);
		move(dx, dy, map);
		updateLaufrichtung();
	}

	protected abstract void updateLaufen(Input in, int delta, Karte map);

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

	protected void move(float dx, float dy, Karte map) {
		float precision = 2f;
		if (Math.abs(dx) > precision || Math.abs(dy) > precision) {
			move(dx/2, dy/2, map);
			move(dx/2, dy/2, map);
			return;
		}
		
		if (dx != 0 || dy != 0) {
			if (dx != 0 && map.blocked(this, dx, 0))
				dx = 0;

			if (dy != 0 && map.blocked(this, 0, dy))
				dy = 0;

			if (dx != 0 && dy != 0 && map.blocked(this, dx, dy))
				dx = dy = 0;

			this.position.x += dx;
			this.position.y += dy;
		}

	}

	public Vector2f getPosition() {
		return position.copy();
	}

	public int getWidth() {
		return bild.getBox().links + bild.getBox().rechts;
	}

	public int getHeight() {
		return bild.getBox().oben + bild.getBox().unten;
	}

	public Box getBox() {
		return bild.getBox();
	}

}
