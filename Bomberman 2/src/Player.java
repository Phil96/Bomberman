import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

public class Player extends GameObject {

	private Controller myController;
	private Point direction, up, right, down, left, stay;
	private boolean walking;
	private Texture img;
	private int bombRadius, maxBombs, currentBombs;
	private Color color;
	
	public Player(Map m, Controller c) {
		super(m,0,0);
		myController = c;
		up = new Point(0,-1);
		right = new Point(1,0);
		down = new Point(0,1);
		left = new Point(-1,0);
		stay = new Point(0,0);
		direction = down;
		walking = false;
		bombRadius = 2;
		maxBombs = 300;
		img = Window.loadTexture("player");
		color = new Color(1.0f, 1.0f, 1.0f);
	}
	  
	public void update(long delta) {
		getOlder(delta);
		
		// Bewegung des Spielers
		walking = true;
		switch (myController.getMoveRequest()) {
			case UP:
				direction = up;
				break;
			case RIGHT:
				direction = right;
				break;
			case DOWN:
				direction = down;
				break;
			case LEFT:
				direction = left;
				break;
			case STAY:
				walking = false;
				direction = stay;
				break;
		}
		if (walking) {
			Point newPos = position.add(direction);
			if (  map.isWalkable(newPos) ) {
				position = newPos;
				//Utils.sleep(50);
			}
		}
		// Ablegen von Bomben
		if ( myController.getDropRequest() && currentBombs < maxBombs && map.isWalkable(position) ) {
			currentBombs++;
			GameObject a = new Bomb(map, position.getX(), position.getY(), this, bombRadius);
			map.addGameObject( a );
		}
	}
	
	public void draw() {
		color.activate();
		img.bind();
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
		return true; // über Mitspieler kann man gehen 
	}

	public boolean isBreakable(){
		return true; // Spieler können zerstört werden
	}
	
	public void bombExploded() {
		currentBombs--;
	}
	
	public void setColor(Color c) {
		color = c;
	}
}
