package spieler;

public class Daten {

	public String name;
	public Geschlecht geschlecht;
	
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
}
