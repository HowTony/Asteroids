
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony Howarth on 5/24/2016.
 */
public class PhysicsEngine {

    ArrayList<Collidable> mGameObjectList;
    private SafeSpawn mSafeZone;

    public PhysicsEngine(SafeSpawn safe) {
        mSafeZone = safe;
        mGameObjectList = new ArrayList<>();
    }

    public void AddCollidable(Collidable c) {
        if (!mGameObjectList.contains(c)) {
            mGameObjectList.add(c);
        }
    }

    public void AddCollidable(ArrayList<Collidable> list) {
        for (Collidable eachCollidable : list) {
            if (!mGameObjectList.contains(eachCollidable)) {
                mGameObjectList.add(eachCollidable);
            }
        }
    }

    public void ManageObjectList() {
        List<Collidable> list = new ArrayList<>();
        for (Collidable eachCollidable : mGameObjectList) {
            if (!eachCollidable.IsAlive()) {
                list.add(eachCollidable);
            }
        }
        for (Collidable eachCollidable : list) {
            mGameObjectList.remove(eachCollidable);
        }
    }

    public void Update(double deltaTime) {
        CheckCollision();
        ManageObjectList();
        mSafeZone.SetIsSafe(IsSafeZoneDangerous());
        mSafeZone.Update(deltaTime);
    }

    public boolean IsSafeZoneDangerous(){
        for(int i = 0; i < mGameObjectList.size(); i++){
            Collidable firstTestCase = mGameObjectList.get(i);
            if(firstTestCase.GetName().contains("Astero")){
                if (firstTestCase.GetBounds().intersects(mSafeZone.GetBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void CheckCollision() {
        for (int a = 0; a < mGameObjectList.size(); a++) {
            for (int b = a+1; b < mGameObjectList.size(); b++) {
                Collidable firstTestCase = mGameObjectList.get(a);
                Collidable secondTestCase = mGameObjectList.get(b);
                if (firstTestCase != secondTestCase) {
                    if (firstTestCase.GetBounds().intersects(secondTestCase.GetBounds())) {
                        if(firstTestCase.GetName().contains("Astero") && secondTestCase.GetName().contains("Astero")) {
                            firstTestCase.ReverseDirection(secondTestCase);
                        }else{
                            firstTestCase.Collide(secondTestCase);
                            secondTestCase.Collide(firstTestCase);
                        }
                    }
                }
            }
        }
    }
}
