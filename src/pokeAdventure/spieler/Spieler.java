package pokeAdventure.spieler;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;



public class Spieler {

	public String name;
	public Geschlecht geschlecht;
	
	public Vector2f Koordinaten;
	
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
		//TODO update
	}

	public void render(GameContainer container, Graphics g) {
		//TODO render
	}
}
