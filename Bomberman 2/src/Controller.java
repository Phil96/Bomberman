
interface Controller { 
	
	public Direction getMoveRequest();
	public boolean getDropRequest();
	
	public static enum Direction {
		UP, RIGHT, DOWN, LEFT, STAY;
	}
	
}
