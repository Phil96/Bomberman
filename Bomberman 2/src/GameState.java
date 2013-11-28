
public abstract class GameState {
	
	protected Window window;
	protected BomberMan main;
	
	public GameState(Window w, BomberMan bm) {
		window = w;
		main = bm;
	}
	
	public void enter() {}
	
	public void exit() {}
	
	public void update() {}
	
	public void render() {}
}
