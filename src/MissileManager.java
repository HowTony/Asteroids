import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tony Howarth on 5/19/2016.
 */
public class MissileManager {


    private Ship mShip;
    private ArrayList<Missile> mMissileList;

    public MissileManager(Ship ship) {
        mShip = ship;
        mMissileList = new ArrayList<Missile>();
    }

    public void addMissile() {
        if(mMissileList.size() < 100) {
            mMissileList.add(new Missile(mShip));
            System.out.println("num of missiles " + mMissileList.size());
        }
    }

    public void Spawn() {
        addMissile();
    }

    public void Draw(Graphics g) {
        for (Missile eachMissile : mMissileList) {
            eachMissile.Draw(g);
        }
    }

    public void Update() {
        for (Missile eachMissile : mMissileList) {
            eachMissile.Update();
        }
    }
}
