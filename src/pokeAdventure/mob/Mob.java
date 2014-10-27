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
import org.newdawn.slick.geom.Vector2f;

public class Mob {

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
		bild.get(laufrchtung).drawCentered(position.x, position.y);
	}

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

		bild.setMoving(dx != 0 || dy != 0);

	}

	protected void move(int dx, int dy) {
		// TODO Kollision
		this.position.x += dx;
		this.position.y += dy;
	}

	public Vector2f getPosition() {
		return position.copy();
	}

}
