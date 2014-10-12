package pokeAdventure.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.shader.ShaderProgram;
import org.newdawn.slick.tiled.TiledMap;

import pokeAdventure.Main;
import pokeAdventure.interfaces.Action;
import pokeAdventure.state.menu.ButtonArray;
import pokeAdventure.state.menu.MenuButton;

public class GUI {

	// Blur effect
	private ShaderProgram blurHoriz, blurVert;
	private Graphics postGraphicsA, postGraphicsB;
	private Image postImageA, postImageB;
	private float radius = 1.1f;

	// Menu
	private Color bg;
	private float border;
	ButtonArray btns;

	public GUI(GameContainer container) {
		initShaders(container);
		initMenu();
	}

	private void initShaders(GameContainer container) {
		try {
			// first we will try to create our offscreen image
			// this may fail in very very rare cases if the user has no
			// FBO/PBuffer support
			postImageA = Image.createOffscreenImage(container.getWidth(), container.getHeight());
			postGraphicsA = postImageA.getGraphics();
			postImageB = Image.createOffscreenImage(container.getWidth(), container.getHeight());
			postGraphicsB = postImageB.getGraphics();

			String h = "res/shaders/hblur.frag";
			String v = "res/shaders/vblur.frag";
			String vert = "res/shaders/blur.vert";

			blurHoriz = ShaderProgram.loadProgram(vert, h);
			blurVert = ShaderProgram.loadProgram(vert, v);

			// note that strict mode is ENABLED so these uniforms must be active
			// in shader

			// set up our uniforms for horizontal blur...
			blurHoriz.bind();
			blurHoriz.setUniform1i("tex0", 0); // texture 0
			blurHoriz.setUniform1f("resolution", container.getWidth()); // width
																		// of
																		// img
			blurHoriz.setUniform1f("radius", radius);

			// set up our uniforms for vertical blur...
			blurVert.bind();
			blurVert.setUniform1i("tex0", 0); // texture 0
			blurVert.setUniform1f("resolution", container.getHeight()); // height
																		// of
																		// img
			blurVert.setUniform1f("radius", radius);

			ShaderProgram.unbindAll();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private void initMenu() {
		bg = new Color(170, 170, 170, 200); // leicht grau
		border = 0.1f;

		try {
			int z = 4;
			MenuButton test1 = new MenuButton(Main.getWidth() / 2, Main.getHeight() / z * 1, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
				@Override
				public void action() {
					System.out.println("1");
				}
			});
			
			MenuButton test2 = new MenuButton(Main.getWidth() / 2, Main.getHeight() / z * 2, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
				@Override
				public void action() {
					System.out.println("2");
				}
			});
			
			MenuButton test3 = new MenuButton(Main.getWidth() / 2, Main.getHeight() / z * 3, "res/menu/buttons/neuesSpiel.png", "res/menu/buttons/neuesSpielHighlight.png", new Action() {
				@Override
				public void action() {
					System.out.println("3");
					Main.getMainGame().enterState(0x0);
				}
			});

			test1.setHorizontalZentriert(true);
			test2.setHorizontalZentriert(true);
			test3.setHorizontalZentriert(true);
			
			btns = new ButtonArray(test1, test2, test3);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, Graphics g) {
		renderBlurredBackground(container, g);
		renderMenuBack(container, g);
		btns.render();
	}

	private void renderMenuBack(GameContainer container, Graphics g) {
		g.setColor(bg);
		g.fillRoundRect(border * Main.getWidth(), border * Main.getHeight(), (1 - 2 * border) * Main.getWidth(), (1 - 2 * border) * Main.getHeight(), 10);
	}

	private void renderBlurredBackground(GameContainer container, Graphics g) {
		// 1. first we render our scene without blur into an off-screen buffer

		// this is just to be safe when using multiple contexts
		Graphics.setCurrent(postGraphicsA);

		postGraphicsA.clear();

		Main.getGameState().renderScene(container, Main.getMainGame(), g);

		// flush it after drawing
		postGraphicsA.flush();

		// if we were using a single pass (i.e. only horizontal blur) then we
		// could render
		// directly into the screen at this point. but since we are using two
		// passes, we first need to
		// sample from the normal texture, then blur it horizontally onto to
		// another texture, then sample
		// from the blurred texture as we blur it again (vertically) onto the
		// screen.

		// 2. enable our first shader, the horizontal blur
		blurHoriz.bind();
		blurHoriz.setUniform1f("radius", radius);

		// 3. sample from A, render to B
		Graphics.setCurrent(postGraphicsB);
		postGraphicsB.clear();
		postGraphicsB.fillRect(0, 0, 800, 600);
		postGraphicsB.drawImage(postImageA, 0f, 0f);
		postGraphicsB.flush();

		blurHoriz.unbind();

		// 4. enable our second shader, the vertical blur
		blurVert.bind();
		blurVert.setUniform1f("radius", radius);

		// 5. sample from B, render to screen
		Graphics.setCurrent(g);
		g.drawImage(postImageB, 0, 0);
		// flushing the screen graphics doesn't do anything, so it's unnecessary
		// screenGraphics.flush();

		// stop using shaders
		ShaderProgram.unbindAll();
	}

	public void update(GameContainer container, int delta, TiledMap map) {
		Input in = container.getInput();
		btns.update(in);
	}

}
