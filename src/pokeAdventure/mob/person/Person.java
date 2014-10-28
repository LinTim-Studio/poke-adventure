package pokeAdventure.mob.person;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import static pokeAdventure.Main.rand;
import pokeAdventure.mob.MehrfachBild;
import pokeAdventure.mob.Mob;

public class Person extends Mob {

	public Person(Vector2f pos, MehrfachBild bild) {
		super(pos, bild);
	}

	@Override
	protected void updateLaufen(Input in, int delta, TiledMap map) {

		if ((dx == 0 && dy == 0) || rand.nextInt(200) == 0) {
			if (rand.nextBoolean()) {
				dx = rand.nextInt(2) * 2 - 1;
				dy = 0;
			} else {
				dx = 0;
				dy = rand.nextInt(2) * 2 - 1;
			}
			dx *= delta * 0.15;
			dy *= delta * 0.15;
		}

	}

}
