import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tony Howarth on 5/18/2016.
 */
public class AsteroidManager {

    private ArrayList<Asteroid> mAsteroidList;

    public AsteroidManager() {
        mAsteroidList = new ArrayList<>();
        addAsteroid();
    }

    public void addAsteroid() {
        while (mAsteroidList.size() < 0) {
            mAsteroidList.add(new Asteroid((new Point.Double(Math.random() * GameWindow.CANVAS_WIDTH, Math.random() * GameWindow.CANVAS_HEIGHT))));
        }
    }

    public void Draw(Graphics g) {
        for (Asteroid eachAsteroid : mAsteroidList) {
            eachAsteroid.Draw(g);
        }
    }

    public void Update() {
        for (Asteroid eachAsteroid : mAsteroidList) {
            eachAsteroid.Update();
        }
        // Collision

//        for (Asteroid eachAsteroid:mAsteroidList) {
//            Rectangle eachRock = eachAsteroid.getBounds();
//            for (Asteroid otherAsteroid: mAsteroidList) {
//                Rectangle otherRock = otherAsteroid.getBounds();
//                if(eachAsteroid != otherAsteroid && eachRock.intersects(otherRock)){
//                    eachAsteroid.ReverseDirection();
//
//                }
    }
}
