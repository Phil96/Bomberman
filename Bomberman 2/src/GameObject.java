
public abstract class GameObject {
  
	private boolean destroyed = false;
	protected Point position;
	protected long age;
	protected Map map;
	
	public GameObject(Map m,int x, int y) {
		setMap(m);
		age = 0l;
		position = new Point(x,y);
	}
	
	public void draw() { // sich selbst zeichnen
		// GameObjects müssen sich aber nicht sichtbar sein
	}
  
	public void update(long delta) { // nächsten Spielschritt berechnen
		// GameObjects müssen sich aber nicht verändern
	}

	public void destroy() { // Zerstört das GameObject
		destroyed = true;//!destroyed;
	}
	
	public boolean isDestroyed() { // wird von Game verwendet um zerstörte GOs zu identifizieren
		return destroyed;
	}
	
	public GameObject drop() {
		return null;
	}
	
	public void setMap(Map m) {
		map = m;
	}
	
	protected void getOlder(long delta) {
		age += delta;
	}
	
	public Point getPos() {
		return position;
	}
	
	public void setPos(Point p) {
		position = p;
	}
	
	public int getID() { // Wird für speichern von Maps benötigt
		return 0;  // 0 nicht speichern; 1 harte Wand; 2 weiche Wand; 3 Bombe; 4 Items...
	}
	
	public abstract boolean isWalkable();

	public abstract boolean isBreakable();

}