import java.awt.Graphics;

public class GameLogic {
	
	private Asteroid mAsteroid;
	//private Asteroid mAsteroid2;
	private GameWindow mGameWindow;
	private Ship mShip;
	private UserInput mUserInput;

	public GameLogic(GameWindow window) {
		mGameWindow = window;
		mAsteroid = new Asteroid(1, 150, 4, 4);
		mShip = new Ship(GameWindow.CANVAS_WIDTH/2, GameWindow.CANVAS_HEIGHT/2);
		mUserInput = new UserInput(mShip);
		mGameWindow.RegisterKeyListener(mUserInput);
		
		//mAsteroid2 = new Asteroid(340, 250, 8, 8);
		
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
			mUserInput.Update();

		}
	}
	
	private void Update() {
		mAsteroid.Update();
		mShip.Update();

	}
	
	public void Draw(Graphics g) {
		// rendering logic
		mAsteroid.Draw(g);
		mShip.Draw(g);
		//mAsteroid2.Draw(g);
		
	}
	
	
	
	
}
