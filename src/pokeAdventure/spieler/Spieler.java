package pokeAdventure.spieler;

import org.newdawn.slick.geom.Vector2f;



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
		return "Nichts";
	}
}
