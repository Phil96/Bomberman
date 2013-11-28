import org.lwjgl.input.Keyboard;

public class KeyboardController implements Controller {
	
	private int keyUP, keyRIGHT, keyDOWN, keyLEFT, keyDROP;
	private Direction direction;
	
	public KeyboardController(int up, int right, int down, int left, int drop) {
		keyUP = up;
		keyRIGHT = right;
		keyDOWN = down;
		keyLEFT = left;
		keyDROP = drop;
	}
	
	public Direction getMoveRequest() {
		updateDirection();
		return direction;
	}
	
	public boolean getDropRequest() {
		return Keyboard.isKeyDown(keyDROP);
	}
	
	private void updateDirection() {
		if (Keyboard.isKeyDown(keyUP)) {
			direction = Direction.UP;
		} else if (Keyboard.isKeyDown(keyRIGHT)) {
			direction = Direction.RIGHT;
		} else if (Keyboard.isKeyDown(keyDOWN)) {
			direction = Direction.DOWN;
		} else if (Keyboard.isKeyDown(keyLEFT)) {
			direction = Direction.LEFT;
		} else {
			direction = Direction.STAY;
		}
	}
	
}
