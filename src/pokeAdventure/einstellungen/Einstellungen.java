package pokeAdventure.einstellungen;

import java.io.Serializable;

import org.newdawn.slick.openal.SoundStore;

import pokeAdventure.util.Save;

public class Einstellungen implements Serializable {
	private static final long serialVersionUID = 2229008461068346950L;

	private static String saveFile = "saves/Einstellungen.sav";

	public static Einstellungen einstellungen;

	// werte
	private float soundVolume;
	
	private Einstellungen() {
	}

	public static void load() {
		Object obj = Save.load(saveFile);
		if (obj instanceof Einstellungen)
			einstellungen = (Einstellungen) obj;
		else
			einstellungen = new Einstellungen();
		
		//umsetzung
		setSoundVolume(einstellungen.soundVolume);
	}

	public static void save() {
		if (einstellungen != null)
			Save.save(einstellungen, saveFile);
	}

	public static float getSoundVolume() {
		return einstellungen.soundVolume;
	}

	public static void setSoundVolume(float soundVolume) {
		einstellungen.soundVolume = soundVolume;
		SoundStore.get().setSoundVolume(soundVolume);
	}

}
