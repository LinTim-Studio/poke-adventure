package pokeAdventure.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import pokeAdventure.mob.spieler.Spieler;
import pokeAdventure.util.Karte;
import pokeAdventure.util.Vector2i;
import pokeAdventure.util.error.Fehlermelder;

public class KartenManager {

	public static String kartenPfad = "res/maps/";

	Karte[] karten;
	
	private int kartenWechselCooldown;

	int aktive;

	public KartenManager() {
		try {
			karten = new Karte[] { new Karte(kartenPfad + "Anfang.tmx"), new Karte(kartenPfad + "Test.tmx"), new Karte(kartenPfad + "Test2.tmx")};
		} catch (SlickException e) {
			Fehlermelder.melde(e, "Fehler beim Laden der Karten!");
			Fehlermelder.crash();
		}
	}

	public Karte getKarte() {
		return karten[aktive];
	}

	public void update(GameContainer container, int delta) {
		getKarte().update(this, container, delta);
		
		if (kartenWechselCooldown > 0) {
			kartenWechselCooldown -= delta;
		}
	}

	public void wechselKarte(int geheNach) {
		if (kartenWechselCooldown > 0) {
			return;
		}
		if (karten.length > geheNach && geheNach >= 0) {
			Vector2i pos = karten[geheNach].sucheAnkunftVon(aktive);
			if (pos != null) {
				Spieler.getInstance().teleport(pos);
			}
			aktive = geheNach;
			kartenWechselCooldown = 500;
		}
	}

}
