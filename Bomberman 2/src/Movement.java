
public class Movement {
	
	private boolean finished;
	private long elapsedTime;
	private int stepTime, compWayX, compWayY, compTimeX, compTimeY;
	private Point start, dest;
	
	public Movement(Point s, Point d, int v) {
		finished = false;
		elapsedTime = 0;
		stepTime = v;
		start = s;
		dest = d;
		compWayX = start.getX() - dest.getX();
		compWayY = start.getY() - dest.getY();
		compTimeX = (compWayX) * stepTime;
		compTimeY = (compWayY) * stepTime;
		if (compTimeX<0) compTimeX *= -1;
		if (compTimeY<0) compTimeY *= -1;
	}
	
	public Point getOffset() {
		int x, y;
		if ( isFinished() ) return new Point(0,0);
		if (compTimeX==0) {
			x = 0;
		} else {
			x = (int) (-1*(compWayX*32-(elapsedTime/compTimeX) * compWayX*32));
		}
		if (compTimeY==0) {
			y = 0;
		} else {
			y = (int) (-1*(compWayY*32-(elapsedTime/compTimeY) * compWayY*32));
		}
		return new Point(x,y);
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished() {
		finished = true;
	}
	
	public void getOlder(long delta) {
		elapsedTime += delta;
	}
}
