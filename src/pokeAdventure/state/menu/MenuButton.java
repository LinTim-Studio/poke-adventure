package pokeAdventure.state.menu;

import org.newdawn.slick.Image;

import pokeAdventure.interfaces.Action;

public class MenuButton 
{
	public float X ;
	public float Y ;
	public Image Bild;
	
	public Action event;
	//public Image Bild2;
	
	
	public MenuButton(float koorX, float koorY,Image pic)
	{
		X=koorX;
		Y=koorY;
		Bild=pic;
	}
	
	public void zeichneButton()
	{
		Bild.draw(X,Y);
	}
	
	public void buttonClick(float mx,float my)
	{
		if(this.clicked(mx,my))
			this.event();
			
	}
	
	public boolean clicked(float mx, float my)
	{
		if((mx>X)&&(mx<X+(float) Bild.getHeight())&&(my>Y)&&(my<Y+(float) Bild.getWidth()))
			return true;
		else
			return false;
	}
}
