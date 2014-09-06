package pokeAdventure.state.menu;

import org.newdawn.slick.Image;

import pokeAdventure.interfaces.Action;

public class MenuButton 
{
	public int y ;
	public int x ;
	public Image Bild;
	
	public Action event;
	//public Image Bild2;
	
	
	public MenuButton(int koorX, int koorY,Image pic, Action aktion)
	{
		x=koorX;
		y=koorY;
		Bild=pic;
		event=aktion;
	}
	
	public void zeichneButton()
	{
		Bild.draw(x,y);
	}
	
	public void buttonClick(int mx,int my)
	{
		if(this.clicked(mx,my))
			this.event.action();
	}
	
	public boolean clicked(int mx, int my)
	{
		if((mx>x)&&(mx<x+ Bild.getHeight())&&(my>y)&&(my<y+ Bild.getWidth()))
			return true;
		else
			return false;
	}
}
