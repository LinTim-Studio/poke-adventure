package pokeAdventure.mob.spieler;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.Main;
import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;
import pokeAdventure.mob.MehrfachBild;
import pokeAdventure.mob.Mob;
import pokeAdventure.util.SpriteManager;

public class Spieler extends Mob {


	private String name;
	private Geschlecht geschlecht;

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

	public Spieler() {
		super(new Vector2f(), new MehrfachBild(SpriteManager.spielerSheet, 5));
	}
	
	public void render(GameContainer container, Graphics g, Vector2f offset) {
		bild.get(laufrchtung).drawCentered(Main.getWidth()/2 + offset.x, Main.getHeight()/2 + offset.y);
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
		updateLaufrichtung();
	}

	private void updateLaufen(Input in, int delta, TiledMap map) {
		dx = dy = 0;
		
		float tempSpeed = 0.2f;

		if (Tastenbelegung.isDown(in, Taste.Hoch)) {
			dy -= delta * tempSpeed;
		}
		if (Tastenbelegung.isDown(in, Taste.Runter))
			dy += delta * tempSpeed;
		if (Tastenbelegung.isDown(in, Taste.Links))
			dx -= delta * tempSpeed;
		if (Tastenbelegung.isDown(in, Taste.Rechts))
			dx += delta * tempSpeed;
		
		move(dx, dy);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}
}
