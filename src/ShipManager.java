import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tony Howarth on 6/1/2016.
 */
public class ShipManager {

    private float mDeathTimer = 0;
    private AnimatedLine mDeathAnimation;
    private boolean mCurrentShipDied = false;
    private AudioPlayer mSoundFX;
    private boolean mSafeSpawn = false;

    private List<Ship> mShipList = Collections.synchronizedList(new ArrayList<Ship>());

    public ShipManager() {
        AddShips();
        mSoundFX = new AudioPlayer("Resources/SFX/shipDeath.wav");

    }

    public void AddShips() {
        while (mShipList.size() < 40) {
            mShipList.add(new Ship());
        }
    }

    public void Update(double deltaTime) {
        synchronized (mShipList) {
            if (mShipList.size() > 0) {
                mShipList.get(0).Update(deltaTime);
            }
            if (!mShipList.get(0).IsAlive()) {
                ManageShipList(deltaTime);
            }
        }
    }

    public void Draw(Graphics g) {
        if (mShipList.size() > 0) {
            mShipList.get(0).Draw(g);
        }

        if (mCurrentShipDied) {
            mDeathAnimation.Draw(g);
        }
    }

    public int GetShipLives() {
        return mShipList.size();
    }

    public Ship GetCurrentShip() {
        if (mShipList.size() > 0) {
            return mShipList.get(0);
        } else {
            return null;
        }
    }

    public void SetSafeSpawn(boolean b){
        mSafeSpawn = b;
    }

    public void ManageShipList(double deltatime) {
        mDeathTimer += deltatime;
        Ship currentShip = mShipList.get(0);
        if (mShipList.size() > 0) {
            if (!mCurrentShipDied) {
                mDeathAnimation = new AnimatedLine(currentShip);
                mCurrentShipDied = true;
                mSoundFX.Play();
            }
            mDeathAnimation.Update(deltatime);
            if (mDeathTimer >= 4.0f) {
                if(!mSafeSpawn) {
                    mShipList.remove(currentShip);
                    mDeathTimer = 0;
                    mCurrentShipDied = false;
                }
            }
        }
    }
}
