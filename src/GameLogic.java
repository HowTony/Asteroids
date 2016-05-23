import java.awt.Graphics;
import java.text.DecimalFormat;

public class GameLogic {
	

	private GameWindow mGameWindow;
	private Ship mShip;
	private AsteroidManager mGM;
	private UserInput mUserInput;
	private MissileManager mMissleManager;
    private double mDeltaTime = 0D;

	public GameLogic(GameWindow window) {
		mGameWindow = window;
		mGM = new AsteroidManager();
		mShip = new Ship();
		mMissleManager = new MissileManager(mShip);
		mUserInput = new UserInput(mShip, mMissleManager);
		mGameWindow.RegisterKeyListener(mUserInput);
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
        long startTime;
        long endTime;
        long deltaTime = 0;

		while (true) {
            startTime = System.nanoTime();

            double deltaTimeSeconds = (double) deltaTime;

			Update(deltaTimeSeconds / 1000000000D);
			mGameWindow.Paint();

            endTime = System.nanoTime();
            deltaTime = endTime - startTime;
		}
	}
	
	private void Update(double deltaTime) {
		mShip.Update(deltaTime);
		mUserInput.Update(deltaTime);
		mGM.Update();
		mMissleManager.Update(deltaTime);
	}
	
	public void Draw(Graphics g) {
		// rendering logic
        mShip.Draw(g);
		mGM.Draw(g);
		mMissleManager.Draw(g);
		
	}

}
