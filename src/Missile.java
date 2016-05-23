import java.awt.*;

/**
 * Created by Tony Howarth on 5/19/2016.
 */
public class Missile {


    private final double SPEED_BUFFER = 200D;
    private Point.Double mPosition;
    private Ship mShip;
    private Point.Double mDirection;
    private Point.Double mStartPos;

//    private boolean mIsShooting = false;

    public Missile(Ship ship){
        mShip = ship;
        mPosition = mShip.getMissleStart();
        mStartPos = mShip.getMissleStart();
        mDirection = mShip.getmForwardVector();
    }

    public void Draw(Graphics g){
        g.setColor(Color.cyan);
        g.drawOval((int) mPosition.x, (int) mPosition.y, 5, 5);
    }

    public int getCurrentDistanceChange(){
        return ((int)((mStartPos.x - mPosition.x ) + (mStartPos.y -mPosition.y )));
    }

    public void MissleMove(double deltaTime){
        double maxWidth = GameWindow.CANVAS_WIDTH;
        double maxHeight = GameWindow.CANVAS_HEIGHT;

        if (mPosition.x > maxWidth) {
            mPosition.x = 0;
        }
        if (mPosition.x < 0) {
            mPosition.x = (int) maxWidth;
        }

        if (mPosition.y > maxHeight) {
            mPosition.y = 0;
        }
        if (mPosition.y < 0) {
            mPosition.y = (int) maxHeight;
        }

        mPosition.x += mDirection.x * SPEED_BUFFER * deltaTime;
        mPosition.y += mDirection.y * SPEED_BUFFER * deltaTime;
    }


    public void Update(double deltaTime){
        MissleMove(deltaTime);
    }


}
