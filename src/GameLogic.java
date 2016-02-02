import java.awt.Graphics;

public class GameLogic {
	
	private Asteroid mAsteroid;
	private Asteroid mAsteroid2;
	private GameWindow mGameWindow;
	
	
	

	public GameLogic(GameWindow window) {
		mGameWindow = window;
		mAsteroid = new Asteroid(180, 150, 10, 15);
		mAsteroid2 = new Asteroid(280, 250, 20, 25);
		
		StartGameLoop();
	}

	private void StartGameLoop() {
		mGameWindow.SetGameLogic(this);
		
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
			mGameWindow.Paint();
		}
	}
	
	private void Update() {
		// update game logic

	}
	
	public void Draw(Graphics g) {
		// rendering logic
		mAsteroid.Draw(g);
		mAsteroid2.Draw(g);
		
	}
	
	
	
}
