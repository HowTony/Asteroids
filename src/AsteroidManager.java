import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * Created by Tony Howarth on 5/18/2016.
 */
public class AsteroidManager {
    private List<Asteroid> mAsteroidList = Collections.synchronizedList(new ArrayList<>());

    public AsteroidManager() {
        addAsteroid();
    }

    public void addAsteroid() {
        synchronized (mAsteroidList) {
            while (mAsteroidList.size() < 2) {
                mAsteroidList.add(new Asteroid((new Point.Double(Math.random() * GameWindow.CANVAS_WIDTH, Math.random() * GameWindow.CANVAS_HEIGHT))));
            }
        }
    }

    public void Draw(Graphics g) {
        synchronized (mAsteroidList) {
            for (Asteroid eachAsteroid : mAsteroidList) {
                eachAsteroid.Draw(g);
            }
        }
    }

    public void Update(double deltaTime) {
        synchronized (mAsteroidList) {
            for (Asteroid eachAsteroid : mAsteroidList) {
                eachAsteroid.Update(deltaTime);
            }
        }
    }

    public ArrayList<Asteroid> getAsteroids(){
        ArrayList<Asteroid> list = new ArrayList<>();
        for (Asteroid eachAsteroid:mAsteroidList) {
            list.add(eachAsteroid);
        }
        return list;
    }


}
