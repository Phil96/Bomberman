import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

public class Bomb extends GameObject {
	
	public static Texture[] texture = {Window.loadTexture("bomb"),Window.loadTexture("bomb-blink")};
	Player parent;
	int radius; 
	long lifetime;
	
	public Bomb(Map m, int x, int y, Player p, int r) {
		super(m, x, y);
		parent = p;
		radius = r;
		lifetime = 2500l;
	}
	
	public void update(long delta) {
		getOlder(delta);
		if (age > lifetime) {
			// time to explode
			System.out.println("KAAABOOOOOOMM!");
			parent.bombExploded();
			destroy();
		}
	}
	
	public void draw() {
		glColor3f(1.0f,1.0f,1.0f);
		texture[(int)(age/250)%2].bind(); // blinken
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(position.getX()*32, position.getY()*32);
			glTexCoord2f(1,0);
			glVertex2i(position.getX()*32+32, position.getY()*32);
			glTexCoord2f(1,1);
			glVertex2i(position.getX()*32+32, position.getY()*32+32);
			glTexCoord2f(0,1);
			glVertex2i(position.getX()*32, position.getY()*32+32);
		glEnd();
	}
	
	public boolean isWalkable() {
		return false;
	}

	public boolean isBreakable() {
		return false;
	}

	
}
