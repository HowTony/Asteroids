import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 * Created by Tony Howarth on 5/18/2016.
 */
public class AsteroidManager {
    private List<Asteroid> mAsteroidList = Collections.synchronizedList(new ArrayList<Asteroid>());
    private ScoreManager mScore;
    private AudioPlayer mSoundFX;

    private int mID = 1;

    public AsteroidManager(ScoreManager score) {
        AddAsteroid();
        mScore = score;
        mSoundFX = new AudioPlayer("Resources/SFX/boom.wav");
    }

    public void AddAsteroid() {
        synchronized (mAsteroidList) {
            while (mAsteroidList.size() < 4) {
                mAsteroidList.add(new Asteroid("Asteroid " + mID, 1,(new Point.Double(Math.random() * GameWindow.CANVAS_WIDTH, Math.random() * GameWindow.CANVAS_HEIGHT))));
                mID++;
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
        ManageAsteroidList();
    }

    public ArrayList<Collidable> GetAsteroids(){
        ArrayList<Collidable> list = new ArrayList<>();
        for (Asteroid eachAsteroid:mAsteroidList) {
            list.add(eachAsteroid);
        }
        return list;
    }

    public void ManageAsteroidList(){
        List<Asteroid> list = new ArrayList<>();
        for(Asteroid eachAsteroid: mAsteroidList){
            if(!eachAsteroid.IsAlive()){
                list.add(eachAsteroid);
                mScore.AddScore(eachAsteroid.GetScoreValue());
            }
        }


        for (Asteroid eachAsteroid: list) {
            mAsteroidList.remove(eachAsteroid);
            mSoundFX.Play();
            if(eachAsteroid.GetAsteroidSize() > 7) {
                mAsteroidList.add(new Asteroid("Asteroid " + mID, 2, (new Point.Double(eachAsteroid.GetPosition().getX() - 20, eachAsteroid.GetPosition().getY() - 20))));
                mAsteroidList.add(new Asteroid("Asteroid " + mID, 2, (new Point.Double(eachAsteroid.GetPosition().getX() + 20, eachAsteroid.GetPosition().getY() + 20))));
                mAsteroidList.add(new Asteroid("Asteroid " + mID, 2, (new Point.Double(eachAsteroid.GetPosition().getX() - 20, eachAsteroid.GetPosition().getY() + 20))));
                mAsteroidList.add(new Asteroid("Asteroid " + mID, 2, (new Point.Double(eachAsteroid.GetPosition().getX() + 20, eachAsteroid.GetPosition().getY() - 20))));
            }
        }
    }
}
