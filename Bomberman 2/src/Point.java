
public class Point {
	private int x,y;
	
	public Point(int a, int b) {
		setX(a);
		setY(b);
	}
	
	public void setLocation(int a, int b) {
		setX(a);
		setY(b);
	}
	
	public Point add(Point p) {
		int a,b;
		a = this.getX() + p.getX();
		b = this.getY() + p.getY();
		return new Point(a,b);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public String getString() {
		String s = ""+this+"("+getX()+"x|"+getY()+"y)";
		return s;
	}
	
	public boolean equals(Point p) {
		return ( p.getX()==this.getX() && p.getY()==this.getY() );
	}
	
}
