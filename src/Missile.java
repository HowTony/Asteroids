import java.awt.*;

/**
 * Created by Tony Howarth on 5/19/2016.
 */
public class Missile extends Polygon implements Collidable {


    private final double SPEED_BUFFER = 250D;
    private Point.Double mPosition;
    private Ship mShip;
    private Point.Double mDirection;
    private Point.Double mStartPos;


    public Missile(Ship ship) {
        mShip = ship;
        mPosition = mShip.getMissleStart();
        mStartPos = mShip.getMissleStart();
        mDirection = mShip.getmForwardVector();
    }

    public void Draw(Graphics g) {
        g.setColor(Color.cyan);
        g.drawOval((int) mPosition.x, (int) mPosition.y, 5, 5);
    }

    public int GetCurrentDistanceChange() {
        return ((int) ((mStartPos.x - mPosition.x) + (mStartPos.y - mPosition.y)));
    }

    public void MissleMove(double deltaTime) {

        mPosition.x += mDirection.x * SPEED_BUFFER * deltaTime;
        mPosition.y += mDirection.y * SPEED_BUFFER * deltaTime;
    }

    public void Update(double deltaTime) {
        MissleMove(deltaTime);
    }

    @Override
    public void Collide() {

    }

    public boolean OutOfBounds(){
        if(this.mPosition.getX() > GameWindow.CANVAS_WIDTH || this.mPosition.getX() < 0 ||
                this.mPosition.getY() > GameWindow.CANVAS_HEIGHT || this.mPosition.getY() < 0){
            return true;
        }
        return false;
    }

}
