import java.awt.Graphics;

public class GameLogic {

	private GameWindow mGameWindow;
	private UserInput mUserInput;
	private PhysicsEngine mPhysics;
    private double mDeltaTime = 0D;

	public GameLogic(GameWindow window) {
		mGameWindow = window;
		mPhysics = new PhysicsEngine();
		mUserInput = new UserInput(mPhysics.getShip(), mPhysics.getMissileManager());
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
	
	public void Update(double deltaTime) {
		mUserInput.Update(deltaTime);
		mPhysics.Update(deltaTime);
	}
	
	public void Draw(Graphics g) {
		mPhysics.Draw(g);
	}

}
