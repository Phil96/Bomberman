import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import java.util.Hashtable;

public class Window {
	
	private int width, height;
	private static Hashtable<String, Texture> textures = new Hashtable<String, Texture>();
	
	public Window(String title, int w, int h) {
		width = w;
		height = h;
		initOpenGL(title);
	}
	
	public void renderGameState(GameState gs) {
		// update resolution
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glViewport(0, 0, width, height);
			glScissor(0, 0, width, height);
			glOrtho(0,width,height,0,1,-1);
			glMatrixMode(GL_MODELVIEW); 
		// draw 
			glClear(GL_COLOR_BUFFER_BIT);
			gs.render();
		// update window
			Display.update();
			Display.sync(30);
	}
	
	public void resize(int w, int h) {
		width = w;
		height = h;
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private void initOpenGL(String t) {
		// Fenster erstellen
		try {
			resize(width,height);
			Display.setTitle(t);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		// OpenGL initialisieren
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_BLEND);
	}
	
	public Point getMousePos() {
		return new Point(Mouse.getX(), width-Mouse.getY());
	}
	
	public static Texture loadTexture(String file) { // läd Grafik in Speicher und vermeidet Speicherverschwendung durch "doppelspeichern"
		Texture t = textures.get(file);
		if ( t != null) return t;
		try {
			t = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/img/"+file+".png")));
			textures.put(file, t);
			return t;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// platz für coole methoden die alle mal brauchen können
	// zB fade in/outs; coole übergänge, hintergründe, usw.
	
}
