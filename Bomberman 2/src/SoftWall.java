import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public class SoftWall extends GameObject {

	public static Texture texture = Window.loadTexture("box");
	
	public SoftWall(Map m, int x, int y) {
		super(m,x,y);
	}
	
	public void draw() {
		glColor3f(1.0f, 1.0f, 1.0f);
		texture.bind();
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
		return false; // Man kann nicht durch SoftWalls gehen 
	}

	public boolean isBreakable(){
		return true; // Softwalls halten Explosionen durch Bomben NICHT stand
	}
}
