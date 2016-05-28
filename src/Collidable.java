import java.awt.*;

/**
 * Created by Tony Howarth on 5/24/2016.
 */
public interface Collidable {

    void Collide(Collidable c);
    Rectangle GetBounds();
    String GetName();
    boolean IsAlive();
    void SetAlive(boolean b);
    double GetDeltaX();
    double GetDeltaY();
    void SetDeltaX(double d);
    void SetDeltaY(double d);
    void ReverseDirection(Collidable a);


}
