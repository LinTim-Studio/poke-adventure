package pokeAdventure.spieler;

import org.newdawn.slick.geom.Vector2f;



public class Daten {

	public String name;
	public Geschlecht geschlecht;
	
	public Vector2f Koordinaten;
	
	/*
	 * Singleton
	 */
	private static Daten spielerDaten;
	
	public static Daten getInstance() {
		if (spielerDaten == null) {
			spielerDaten = new Daten();
		}
		return spielerDaten;	
	}
	
	private Daten()
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
