import java.util.ArrayList;


/**
 * Created by Tony Howarth on 5/24/2016.
 */
public class PhysicsEngine {


    ArrayList<Collidable> mGameObjectList;

    public PhysicsEngine(){
        mGameObjectList = new ArrayList<>();
    }

    public void addCollidale(Collidable c){
        if(!mGameObjectList.contains(c)){
            mGameObjectList.add(c);
            System.out.println("Collidable added");
        }
    }

    public void Update(double deltaTime) {

    }

}
