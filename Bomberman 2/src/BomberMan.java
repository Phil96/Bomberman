import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;

public class BomberMan {
	
	private Window window;
	private GameState activeGameState;
	public GameState stateMainMenu, stateSettingsMenu, stateGaming, statePauseMenu;
	
	public BomberMan() {
		window = new Window("Bomberman by Christoph & Philip", 400, 400); 
		loadGameStates();
		// Map erstellen
		Map m1 = new Map(15,15);
		Map.autoFillMap( m1 );
		// Spiel erstellen
		Game g1 = new Game(window,this,m1);
		// PfeiltastenController erstellen
		Controller c1 = new KeyboardController(Keyboard.KEY_UP,Keyboard.KEY_RIGHT,Keyboard.KEY_DOWN,Keyboard.KEY_LEFT,Keyboard.KEY_RCONTROL);
		Controller c2 = new KeyboardController(Keyboard.KEY_W,Keyboard.KEY_D,Keyboard.KEY_S,Keyboard.KEY_A,Keyboard.KEY_TAB);
		// Spieler erstellen der mit diesem Controller steuerbar ist 
		Player p1 = new Player(m1, c1);
		p1.setColor( new Color(1.0f, 0.0f, 0.0f) );
		Player p2 = new Player(m1, c2);
		p2.setColor( new Color(0.0f, 0.0f, 1.0f) );
		// Spieler "ins spiel stecken"
		g1.addPlayer(p1);
		g1.addPlayer(p2);
		// Spiel testweise starten
		stateGaming = g1;
		activeGameState = stateGaming;
		stateGaming.enter();
	}
	
	public void mainLoop() {
		while (!Display.isCloseRequested()) {
			activeGameState.update();
			window.renderGameState(activeGameState);
		}
		Display.destroy();
	}
	
	public void changeGameState(GameState g) {
		activeGameState.exit();
		activeGameState = g;
		activeGameState.enter();
	}
	
	private void loadGameStates() {
//		stateMainMenu = new MainMenu(window,this); // Hauptmenü
//		stateSettingsMenu = new SettingsMenu(window,this); // Einstellungen
//		statePauseMenu = new PauseMenu(window,this); // Pausen menü
//		//...
//		activeGameState = stateMainMenu; // Zu Beginn ist das Hauptmenü aktiv
//		activeGameState.enter();
	}
	
	public static void main(String[] args) {
		BomberMan spiel = new BomberMan();
		spiel.mainLoop();
	}

}