import java.awt.Graphics;

public class GameLogic {
	

	private GameWindow mGameWindow;
	private Ship mShip;
	private AsteroidManager mGM;
	private UserInput mUserInput;
	private MissileManager mMissleManager;


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
		while (true) {
			Update();
			mGameWindow.Paint();

		}
	}
	
	private void Update() {
		mShip.Update();
		mUserInput.Update();
		mGM.Update();
	}
	
	public void Draw(Graphics g) {
		// rendering logic
        mShip.Draw(g);
		mGM.Draw(g);
		mMissleManager.Draw(g);
		
	}
	
	
	
	
}
