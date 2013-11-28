import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

public class Game extends GameState {
	
	private Map map;
	private long timer;
	
	public Game(Window w, BomberMan bm, Map m) {
		super (w,bm);
		map = m;
		timer = 0;
	}
	
	public void addPlayer(Player p) {
		map.addPlayer(p);
	}
	
	public void enter() {
		window.resize(map.getWidth()*32, map.getHeight()*32);
		timer = System.currentTimeMillis();
	}
	
	public void update() {
//		if ( Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ) main.changeGameState(main.statePauseMenu);
		long delta = (System.currentTimeMillis()-timer);
		map.updateAll(delta);
		timer = System.currentTimeMillis();
	}
	
	public void render() {
		map.renderAll();
	}
}
