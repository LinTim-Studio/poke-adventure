package pokeAdventure.util;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
*		speichert/laedt alle images
*
*/
public abstract class SpriteManager {


  public static Image btnNeuesSpiel;
  
  public static void init() throws SlickException {
  
    btnNeuesSpiel = new Image("res/menu/buttons/neuesSpiel.png");
    
  }


}
