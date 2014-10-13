package pokeAdventure.state.menu;

import org.newdawn.slick.SlickException;

import pokeAdventure.util.SpriteManager;

public class Slider extends MenuButton {

	// 0.0 - 1.0
	private float value;
	private int start, end, width, height;
	private int oldPos = -1;

	public Slider(int x, int y, int width, int height, int start, int def, int end) throws SlickException {
		super(x, y, SpriteManager.sliderBack.getScaledCopy(width, height), SpriteManager.sliderBack.getScaledCopy(width, height), null);

		value = (def - start) / (float) (end - start);
		this.end = end;
		this.start = start;
		this.width = width;
		this.height = height;
	}

	@Override
	public void zeichneButton() {
		super.zeichneButton();

		if (ueber) {
			SpriteManager.sliderOver.draw(x + getHorizontaleVerschiebung() + getProgressVerschiebung(), y, (SpriteManager.sliderOver.getWidth() * width) / SpriteManager.sliderBack.getWidth(), (SpriteManager.sliderOver.getHeight() * height) / SpriteManager.sliderBack.getHeight());
		} else {
			SpriteManager.slider.draw(x + getHorizontaleVerschiebung() + getProgressVerschiebung(), y, (SpriteManager.slider.getWidth() * width) / SpriteManager.sliderBack.getWidth(), (SpriteManager.slider.getHeight() * height) / SpriteManager.sliderBack.getHeight());
		}
	}

	@Override
	public void update(int mx, int my, boolean isMousePressed, boolean isMouseDown) {
		if (isMouseDown && istUeber(mx, my)) {
			ueber = true;
			int newPos = mx;
			if (oldPos != -1) {
				value += (newPos - oldPos) / (float) width;
			}
			oldPos = newPos;
			if (value < 0)
				value = 0;
			if (value > 1)
				value = 1;
		} else {
			ueber = false;
		}
	}

	private int getProgressVerschiebung() {
		return (int) (value * width);
	}

	public int getValue() {
		return (int) (value * (end - start) + start);
	}

}
