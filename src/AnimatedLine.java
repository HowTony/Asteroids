import java.awt.*;

/**
 * Created by Tony Howarth on 6/3/2016.
 */
public class AnimatedLine{

    private Ship mShip;
    private double move;
    private double SPEED = 0.0005D;
    private int counter = 0;


    public AnimatedLine(Ship ship){
        mShip = ship;
        move = 0;
    }

    public void Draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawLine(mShip.GetRenderPoints(0).x + (int)move, mShip.GetRenderPoints(0).y +(int) move
                , mShip.GetRenderPoints(1).x + (int)move, mShip.GetRenderPoints(1).y + (int)move);
        g.drawLine(mShip.GetRenderPoints(1).x - (int) move, mShip.GetRenderPoints(1).y - (int) move
                , mShip.GetRenderPoints(2).x - (int) move, mShip.GetRenderPoints(2).y - (int) move);
        g.drawLine(mShip.GetRenderPoints(2).x - (int) move, mShip.GetRenderPoints(2).y + (int) move
                , mShip.GetRenderPoints(3).x - (int) move, mShip.GetRenderPoints(3).y + (int) move);
        g.drawLine(mShip.GetRenderPoints(3).x + (int) move, mShip.GetRenderPoints(3).y - (int) move
                , mShip.GetRenderPoints(0).x + (int) move, mShip.GetRenderPoints(0).y - (int) move);
        Update();
    }

    public void Update(){
        if(counter < 50000) {
            Move();
        }
        counter++;
    }

    public void Move(){
        move += 1 * SPEED;
    }

}
