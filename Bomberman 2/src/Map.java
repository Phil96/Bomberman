import java.util.ArrayList;
import java.util.Random;


public class Map {
	
	private int width, height ,players;
	private ArrayList<GameObject> objects, toAdd, toDelete;
	private boolean iterating;
	
	public Map(int w, int h) {
		setWidth(w);
		setHeight(h); 
		objects = new ArrayList<GameObject>();
		toAdd = new ArrayList<GameObject>();
		toDelete = new ArrayList<GameObject>(); 
		iterating = false;
		players = 0;
	}
	
	public void addGameObject(GameObject g) {
		if ( !objects.contains(g) ) {
				if (iterating) {
					toAdd.add(g);
				} else {
					objects.add(g);
				}
		}
	}
	
	public void deleteGameObject(GameObject g) {
		if (iterating) {
			toDelete.add(g);
		} else {
			objects.remove(g);
		}
	}
	
	public ArrayList<GameObject> getGameObjectsAtPos(Point p) {
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		if ( !pointOnMap(p) ) return result;
		for (GameObject go : objects) {
			if ( go.getPos().equals(p) ) result.add(go);
		}
		return result;
	}
	
	public void addPlayer(GameObject p) {
		if (players<4) {
			players++;
			switch (players) {
				case 1:
					p.setPos(new Point(0,0));
					break;
				case 2:
					p.setPos(new Point(width-1,height-1));
					break;
				case 3:
					p.setPos(new Point(width-1,0));
					break;
				case 4:
					p.setPos(new Point(0,height-1));
					break;
				default:
					return;
			}
			addGameObject(p);
		}
	}
	
	public void updateAll(long delta) {
		pushList();
		for (GameObject go : objects) {
			go.update(delta);
			if ( go.isDestroyed() ) deleteGameObject(go);
		}
		popList();
	}
	
	public void renderAll() { // hier wär noch platz für ne art offset vector den man in der draw() auf die endgültigen koordinaten rechnet um einen rand zu ermöglichen...
		for (GameObject go : objects) {
			go.draw();
		}
	}
	
	public boolean isWalkable(Point p) {
		ArrayList<GameObject> list = getGameObjectsAtPos(p);
		if ( !pointOnMap(p) ) return false;
		for (GameObject go : list) {
			if ( !go.isWalkable() ) return false;
		}
		return true;
	}
	
	public boolean pointOnMap(Point p) {
		return ( (p.getX() >= 0 && p.getX() < width) && (p.getY() >= 0 && p.getY() < height) );
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		if (width > 0) {
			this.width = width;
		} else {
			this.width = 1;
		}
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		if (height > 0) {
			this.height = height;
		} else {
			this.height = 1;
		}
	}
	
	private void pushList() {
		iterating = true;
	}
	
	private void popList() {
		iterating = false;
		for (GameObject go : toAdd) {
			addGameObject(go);
		}
		for (GameObject go : toDelete) {
			deleteGameObject(go);
		}
		toAdd.clear();
		toDelete.clear();
	}
	
	public static Map loadMapFromFile(String file) {
		//...
		return new Map(1,1);
	}

	public static void saveMapToFile(String file) {
		//...
	}
	
	public static void autoFillMap(Map m) { // Algo muss nochverbessert werden
		Random generator = new Random();
		GameObject g;
		for (int x=1;x<m.getWidth()-1;x++) {
			for (int y=1;y<m.getHeight()-1;y++) {
				if (generator.nextInt(3)==2) {
					g =new HardWall(m,x,y);
				} else {
					g = new SoftWall(m,x,y);
				}		
				m.addGameObject(g);
			}
		}
	}
	
}
