package pokeAdventure.util.error;

/**
 * Nimmt alle Fehlermeldungen auf und verarbeitet sie.
 */
public abstract class Fehlermelder {

	/**
	 * Zeigt den Fehler und eine Nachricht.
	 * @param e Der Fehler
	 * @param msg Die Nachricht
	 */
	public static void melde(Exception e, String msg) {
		melde(e.getMessage() + "; " + msg);
	}
	
	/**
	 * Zeigt eine Fehlermeldung
	 * @param e Der Fehler
	 */
	public static void melde(Exception e) {
		melde(e.getMessage());
	}

	/**
	 * Zeigt eine Nachricht als Fehle
	 * @param msg Die Nachricht
	 */
	public static void melde(String msg) {
		System.err.println(msg);
	}
	
	/**
	 * Stoppt alle Prozesse
	 */
	public static void crash() {
		System.exit(-1);
	}
	
	
}
