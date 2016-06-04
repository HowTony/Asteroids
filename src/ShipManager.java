import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tony Howarth on 6/1/2016.
 */
public class ShipManager {

    private List<Ship> mShipList = Collections.synchronizedList(new ArrayList<Ship>());

    public ShipManager() {
        AddShips();

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
            ManageShipList();
        }
    }

    public void Draw(Graphics g) {
        if (mShipList.size() > 0) {
            mShipList.get(0).Draw(g);
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

    public void ManageShipList() {
        List<Ship> list = new ArrayList<Ship>();
        synchronized (mShipList) {
            for (Ship eachShip : mShipList) {
                if (!eachShip.IsAlive()) {
                    list.add(eachShip);
                }
            }
            for (Ship eachShip : list) {
                if (mShipList.size() > 0) {
                    mShipList.remove(eachShip);
                }
            }
        }
    }
}
