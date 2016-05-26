import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Tony Howarth on 5/24/2016.
 */
public class PhysicsEngine {

    ArrayList<Collidable> mGameObjectList;

    public PhysicsEngine(){
        mGameObjectList = new ArrayList<>();
    }

    public void addCollidable(Collidable c){
        if(!mGameObjectList.contains(c)){
            mGameObjectList.add(c);
            System.out.println("Collidable added");
        }
    }

    public void addCollidable(ArrayList<Collidable> list){
        for(Collidable eachCollidable: list) {
            if (!mGameObjectList.contains(eachCollidable)) {
                mGameObjectList.add(eachCollidable);
                System.out.println("Collidable added");
            }
        }
    }
    
    public void Update(double deltaTime) {

    }

    public boolean Collided(Collidable firstObject, Collidable secondObject){
        return true;
    }
}
