package pokeAdventure/util;

import org.newdawn.slick.Image;

/**
*		speichert/laedt alle images
*
*/
public abstract class SpriteManager {


  public static Image btnNeuesSpiel;
  
  public static init() {
  
    btnNeuesSpiel = new Image("res/menu/buttons/neuesSpiel.png");
    
  }


}
