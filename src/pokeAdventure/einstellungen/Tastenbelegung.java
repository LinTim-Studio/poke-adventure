package pokeAdventure.einstellungen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.Input;

import pokeAdventure.util.Save;

public class Tastenbelegung implements Serializable {
	private static final long serialVersionUID = 1782193130627034952L;

	private static String saveFile = "saves/Tastaturbelegung.xml";
	private static ArrayList<Tastenbelegung> tastenbelegungen = new ArrayList<Tastenbelegung>();

	private Taste taste;
	private ArrayList<Integer> belegung;

	public static boolean isDown(Input in, Taste taste) {
		for (Tastenbelegung tastenbelegung : tastenbelegungen) {
			if (tastenbelegung.taste == taste) {
				return tastenbelegung.isDown(in);
			}
		}
		return false;
	}

	public static boolean isPressed(Input in, Taste taste) {
		for (Tastenbelegung tastenbelegung : tastenbelegungen) {
			if (tastenbelegung.taste == taste) {
				return tastenbelegung.isPressed(in);
			}
		}
		return false;
	}

	public static void save() {
		HashMap<Taste, String> map = new HashMap<Taste, String>();
		for (Tastenbelegung tastenbelegung : tastenbelegungen) {
			String keys = Arrays.deepToString(tastenbelegung.belegung.toArray());
			keys = keys.replace("[", "").replace("]", "");
			map.put(tastenbelegung.taste, keys);
		}
		Save.saveXML(map, saveFile);
	}

	public static boolean load() {
		HashMap<Object, Object> map = Save.loadXML(saveFile);
		if (map == null)
			return false;

		Iterator<Entry<Object, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Object, Object> pair = it.next();
			Object key = pair.getKey();
			Object value = pair.getValue();

			if (key instanceof String) {
				Tastenbelegung tastenbelegung = new Tastenbelegung(Taste.valueOf((String) key));
				if (value instanceof String) {
					String[] strings = ((String) value).split(", ");
					int[] result = new int[strings.length];
					for (int i = 0; i < result.length; i++) {
						result[i] = Integer.parseInt(strings[i]);
					}
					tastenbelegung.belegung = new ArrayList<Integer>();
					tastenbelegung.addAll(result);
				}
				addBelegung(tastenbelegung);
			}
		}
		return true;
	}

	public static void addBelegung(Tastenbelegung belegung) {
		boolean exists = false;
		for (Tastenbelegung tastenbelegung : tastenbelegungen) {
			if (tastenbelegung.taste.equals(belegung.taste)) {
				tastenbelegung.addAll(belegung.belegung);
				exists = true;
			}
		}
		if (!exists) {
			tastenbelegungen.add(belegung);
		}

		save();
	}

	public Tastenbelegung(Taste taste) {
		this.taste = taste;
		belegung = new ArrayList<Integer>();
	}

	public Tastenbelegung add(int keyCode) {
		if (!belegung.contains(keyCode)) {
			belegung.add(keyCode);
		}
		return this;
	}

	public Tastenbelegung addAll(int... i) {
		for (int key : i) {
			add(key);
		}
		return this;
	}

	public Tastenbelegung addAll(ArrayList<Integer> i) {
		for (Integer key : i) {
			add(key);
		}
		return this;
	}

	private boolean isPressed(Input in) {
		for (Integer integer : belegung) {
			if (in.isKeyPressed(integer))
				return true;
		}
		return false;
	}

	private boolean isDown(Input in) {
		for (Integer integer : belegung) {
			if (in.isKeyDown(integer))
				return true;
		}
		return false;
	}

}
