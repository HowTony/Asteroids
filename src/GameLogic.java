import java.awt.Graphics;

public class GameLogic {

    private GameWindow mGameWindow;
    private UserInput mUserInput;
    private PhysicsEngine mPhysics;
    private AsteroidManager mAsteroidManager;
    private Ship mShip;
    private MissileManager mMissleManager;

    public GameLogic(GameWindow window) {
        mGameWindow = window;
        mPhysics = new PhysicsEngine();
        mShip = new Ship();
        mAsteroidManager = new AsteroidManager();
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

    public void Update(double deltaTime) {
        mUserInput.Update(deltaTime);
        mPhysics.Update(deltaTime);
        mShip.Update(deltaTime);
        mAsteroidManager.Update(deltaTime);
        mMissleManager.Update(deltaTime);
        mPhysics.AddCollidable(mShip);
        mPhysics.AddCollidable(mAsteroidManager.GetAsteroids());
        mPhysics.AddCollidable(mMissleManager.GetMissiles());
    }

    public void Draw(Graphics g) {
        mShip.Draw(g);
        mMissleManager.Draw(g);
        mAsteroidManager.Draw(g);
    }
}
