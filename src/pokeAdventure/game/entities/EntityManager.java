package pokeAdventure.game.entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import pokeAdventure.mob.person.Person;
import pokeAdventure.util.Karte;
import pokeAdventure.util.SpriteManager;

public class EntityManager {

	private ArrayList<Person> personen;

	public EntityManager() {
		personen = new ArrayList<Person>();
		for (int i = 0; i < 100; i++) {
			personen.add(new Person(new Vector2f(100, 100), SpriteManager.person));
		}
	}

	public void update(GameContainer container, int delta, Karte map) {
		for (Person person : personen) {
			person.update(container, delta, map);
		}
	}

	public void render(GameContainer container, Graphics g, Vector2f offset) {
		for (Person person : personen) {
			person.render(container, g, offset);
		}
	}

}
