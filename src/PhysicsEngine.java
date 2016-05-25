import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tony Howarth on 5/24/2016.
 */
public class PhysicsEngine {

    private AsteroidManager mAsteroidManager;;
    private Ship mShip;
    private MissileManager mMissleManager;
    List<Collidable> mGameObjectList = Collections.synchronizedList(new ArrayList<>());

    public PhysicsEngine(){
    mShip = new Ship();
    mAsteroidManager = new AsteroidManager();
    mMissleManager = new MissileManager(mShip);
    }

    public void Draw(Graphics g) {
        mShip.Draw(g);
        mAsteroidManager.Draw(g);
        mMissleManager.Draw(g);
    }


    public void Update(double deltaTime) {
        mShip.Update(deltaTime);
        mAsteroidManager.Update(deltaTime);
        mMissleManager.Update(deltaTime);
    }

    public Ship getShip(){
        return this.mShip;
    }

    public MissileManager getMissileManager(){
        return this.mMissleManager;
    }

}
