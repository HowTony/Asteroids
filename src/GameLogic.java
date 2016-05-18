import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class GameLogic {
	
	private Asteroid mAsteroid;
	private Asteroid mAsteroid1;
	private Asteroid mAsteroid2;
	//private Asteroid mAsteroid2;
	private GameWindow mGameWindow;
	private Ship mShip;
	private UserInput mUserInput;


	public GameLogic(GameWindow window) {
		mGameWindow = window;
		mAsteroid = new Asteroid(new Point.Double(-10, 50));
		mAsteroid1 = new Asteroid(new Point.Double(-10, 100));
		mAsteroid2 = new Asteroid(new Point.Double(-10, 300));
		mShip = new Ship(new Point.Double(GameWindow.CANVAS_WIDTH /2, GameWindow.CANVAS_HEIGHT /2));
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
		mAsteroid1.Update();
		mAsteroid2.Update();
		mShip.Update();

	}
	
	public void Draw(Graphics g) {
		// rendering logic

		mAsteroid.Draw(g);
		mAsteroid1.Draw(g);
		mAsteroid2.Draw(g);
		mShip.Draw(g);
		//mAsteroid2.Draw(g);
		
	}
	
	
	
	
}
