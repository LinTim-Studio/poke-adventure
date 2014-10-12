package pokeAdventure.spieler;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.util.SpriteManager;

public class Spieler {

	public String name;
	public Geschlecht geschlecht;
	public Richtung laufrichtung;

	private Vector2f koordinaten;

	private int dx;
	private int dy;

	/*
	 * Singleton
	 */
	private static Spieler spielerDaten;

	public static Spieler getInstance() {
		if (spielerDaten == null) {
			spielerDaten = new Spieler();
		}
		return spielerDaten;
	}

	private Spieler() {
		koordinaten = new Vector2f();
	}

	public String getGeschlechtsBezeichnung() {
		if (geschlecht == Geschlecht.maennlich)
			return "Junge";
		if (geschlecht == Geschlecht.weiblich)
			return "M\u00E4dchen";
		if (geschlecht == Geschlecht.anders)
			return "Sonstiges";
		return "Nichts";
	}

	public void update(GameContainer container, int delta, TiledMap map) {
		updateLaufen(container.getInput(), delta, map);
	}

	private void updateLaufen(Input in, int delta, TiledMap map) {
		dx = dy = 0;

		if (Tastenbelegung.isDown(in, Taste.Hoch)) {
			dy -= delta;
		}
		if (Tastenbelegung.isDown(in, Taste.Runter))
			dy += delta;
		if (Tastenbelegung.isDown(in, Taste.Links))
			dx -= delta;
		if (Tastenbelegung.isDown(in, Taste.Rechts))
			dx += delta;

	}

	public void render(GameContainer container, Graphics g) {
		// TODO render
		Image i;

		if ((dy > 0) && (dx == 0)) {
			i = gibPic(Richtung.norden);
		} else if ((dy < 0) && (dx == 0)) {
			i = gibPic(Richtung.sueden);
		} else if ((dx > 0) && (dy == 0)) {
			i = gibPic(Richtung.osten);
		} else if ((dx > 0) && (dy > 0)) {
			i = gibPic(Richtung.nordosten);
		} else if ((dx > 0) && (dy < 0)) {
			i = gibPic(Richtung.suedosten);
		} else if ((dx < 0) && (dy > 0)) {
			i = gibPic(Richtung.nordwesten);
		} else {
			i = gibPic(Richtung.suedwesten);
		}

		i.draw();

	}

	public Image gibPic(Richtung a) {
		return SpriteManager.pikachu;
	}

	public Vector2f getKoordinaten() {
		return koordinaten.copy();
	}
}
