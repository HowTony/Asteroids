import java.awt.*;

/**
 * Created by Tony Howarth on 6/3/2016.
 */
public class AnimatedLine {

    private Ship mShip;
    private double move;
    private double SPEED_BUFFER = 15D;
    private float mDrawTimeLimit = 0;
    private boolean mDrawable = true;


    public AnimatedLine(Ship ship) {
        mShip = ship;
        move = 0;
    }

    public void Draw(Graphics g) {
        if (mDrawable) {
            g.setColor(Color.WHITE);
            g.drawLine(mShip.GetRenderPoints(0).x + (int) move, mShip.GetRenderPoints(0).y + (int) move
                    , mShip.GetRenderPoints(1).x + (int) move, mShip.GetRenderPoints(1).y + (int) move);
            g.drawLine(mShip.GetRenderPoints(1).x - (int) move, mShip.GetRenderPoints(1).y - (int) move
                    , mShip.GetRenderPoints(2).x - (int) move, mShip.GetRenderPoints(2).y - (int) move);
            g.drawLine(mShip.GetRenderPoints(2).x - (int) move, mShip.GetRenderPoints(2).y + (int) move
                    , mShip.GetRenderPoints(3).x - (int) move, mShip.GetRenderPoints(3).y + (int) move);
            g.drawLine(mShip.GetRenderPoints(3).x + (int) move, mShip.GetRenderPoints(3).y - (int) move
                    , mShip.GetRenderPoints(0).x + (int) move, mShip.GetRenderPoints(0).y - (int) move);
        }
    }

    public void Update(double deltaTime) {
        Move(deltaTime);
        mDrawTimeLimit += deltaTime;
        if(mDrawTimeLimit >= 2.0f){
            mDrawable = false;
        }
    }

    public void Move(double deltaTime) {
        move += 1 * deltaTime * SPEED_BUFFER;
    }

}
