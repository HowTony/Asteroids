import java.awt.Graphics;

public class GameLogic {

    private GameWindow mGameWindow;
    private UserInput mUserInput;
    private PhysicsEngine mPhysics;
    private AsteroidManager mAsteroidManager;
    private MissileManager mMissleManager;
    private HUDManager mHUD;
    private ScoreManager mScore;
    private ShipManager mShipList;
    private AudioPlayer mGameMusic;


    public GameLogic(GameWindow window) {
        mGameWindow = window;
        mPhysics = new PhysicsEngine();
        mScore = new ScoreManager();
        mShipList = new ShipManager();
        mAsteroidManager = new AsteroidManager(mScore);
        mMissleManager = new MissileManager(mShipList);
        mUserInput = new UserInput(mShipList, mMissleManager);
        mGameWindow.RegisterKeyListener(mUserInput);
        mHUD = new HUDManager(mScore, mShipList);
        mGameMusic = new AudioPlayer("Resources/Music/Plasma.wav");
        mGameMusic.Play();


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
        mAsteroidManager.Update(deltaTime);
        mMissleManager.Update(deltaTime);
        if(mShipList.GetShipLives() > 0) {
            mPhysics.AddCollidable(mShipList.GetCurrentShip());
        }
        mPhysics.AddCollidable(mAsteroidManager.GetAsteroids());
        mPhysics.AddCollidable(mMissleManager.GetMissiles());
        mShipList.Update(deltaTime);

    }

    public void Draw(Graphics g) {
        mMissleManager.Draw(g);
        mAsteroidManager.Draw(g);
        mHUD.Draw(g);
        mShipList.Draw(g);
    }
}
