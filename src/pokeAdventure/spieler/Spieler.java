package pokeAdventure.spieler;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.einstellungen.Taste;
import pokeAdventure.einstellungen.Tastenbelegung;



public class Spieler {

	public String name;
	public Geschlecht geschlecht;
	
	private Vector2f koordinaten;
	
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
	
	private Spieler()
	{	
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
		if (Tastenbelegung.isDown(in, Taste.Hoch))
			koordinaten.y-=delta;
		if (Tastenbelegung.isDown(in, Taste.Runter))
			koordinaten.y+=delta;
		if (Tastenbelegung.isDown(in, Taste.Links))
			koordinaten.x-=delta;
		if (Tastenbelegung.isDown(in, Taste.Rechts))
			koordinaten.x+=delta;
		
	}

	public void render(GameContainer container, Graphics g) {
		//TODO render
	}
	
	public Vector2f getKoordinaten() {
		return koordinaten.copy();
	}
}
