package pokeAdventure.state.menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Slider extends MenuButton {

	// 0.0 - 1.0
	float value;
	int start, end, width, height;
	int oldPos = -1;
	Image sliderImg, sliderHighlightImg;

	public Slider(int x, int y, int width, int height, int start, int def, int end) throws SlickException {
		super(x, y, new Image("res/menu/slider/sliderBack.png").getScaledCopy(width, height), new Image("res/menu/slider/sliderBack.png").getScaledCopy(width, height), null);

		sliderImg = new Image("res/menu/slider/slider.png");
		sliderImg = sliderImg.getScaledCopy(height / sliderImg.getHeight());

		sliderHighlightImg = new Image("res/menu/slider/sliderHighlight.png");
		sliderHighlightImg = sliderHighlightImg.getScaledCopy(height / sliderHighlightImg.getHeight());

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
			sliderHighlightImg.draw(x + getHorizontaleVerschiebung() + getProgressVerschiebung(), y);
		} else {
			sliderImg.draw(x + getHorizontaleVerschiebung() + getProgressVerschiebung(), y);
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
