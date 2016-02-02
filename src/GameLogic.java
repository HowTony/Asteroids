
public class GameLogic {
	
	private GameWindow mGameWindow;

	public GameLogic(GameWindow window) {
		mGameWindow = window;
		StartGameLoop();
	}

	private void StartGameLoop() {
		Thread loop = new Thread() {
			public void run() {
				GameLoop();
			}
		};
		
		loop.start();
	}
	
	private void GameLoop() {
		while (true) {
			Update();
			Draw();
		}
	}
	
	private void Update() {
		// update game logic

	}
	
	private void Draw() {
		// rendering logic
	}
}
