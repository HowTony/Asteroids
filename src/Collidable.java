import java.awt.*;

/**
 * Created by Tony Howarth on 5/24/2016.
 */
public interface Collidable {

    void Collide(String name);
    Rectangle GetBounds();
    String GetName();
    boolean IsAlive();
}
