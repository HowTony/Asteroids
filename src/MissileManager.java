import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Created by Tony Howarth on 5/19/2016.
 */
public class MissileManager {

    private int mID = 1;
    private Ship mShip;
    private List<Missile> mMissileList = Collections.synchronizedList(new ArrayList<Missile>());

    public MissileManager(Ship ship) {
        mShip = ship;
    }

    public void AddMissile() {
        synchronized (mMissileList) {
            if (mMissileList.size() < 8000) {
                mMissileList.add(new Missile("Missile " + mID, mShip));
//              System.out.println("num of missiles " + mMissileList.size());
                mID++;
            }
        }
    }

    public void Spawn() {
        AddMissile();
    }

    public void Draw(Graphics g) {
        synchronized (mMissileList) {
            for (Missile eachMissile : mMissileList) {
                eachMissile.Draw(g);
            }
        }
    }

    public void Update(double deltaTime) {
        synchronized (mMissileList) {
            for (Missile eachMissile : mMissileList) {
                eachMissile.Update(deltaTime);
            }
        }
        ManageMissileList();
    }

    public ArrayList<Collidable> GetMissiles(){
        ArrayList<Collidable> list = new ArrayList<>();
        for (Missile eachMissile:mMissileList) {
            list.add(eachMissile);
        }
        return list;
    }

    public void ManageMissileList(){
        List<Missile> list = new ArrayList<>();
        for(Missile eachMissile: mMissileList){
            if(!eachMissile.IsAlive()){
                list.add(eachMissile);
            }
        }
        for (Missile eachMissile: list) {
            mMissileList.remove(eachMissile);
        }
    }
}





