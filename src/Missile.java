import java.awt.*;

/**
 * Created by Tony Howarth on 5/19/2016.
 */
public class Missile implements Collidable {

    private final double SPEED_BUFFER = 350D;
    private Point.Double mPosition;
    private ShipManager mShip;
    private Point.Double mDirection;
    private String mName;
    private boolean mIsAlive;
    private double mDeltaX;
    private double mDeltaY;

    public Missile(String name, ShipManager ship) {
        mName = name;
        mShip = ship;
        mPosition = mShip.GetCurrentShip().GetMissleStart();
        mDirection = mShip.GetCurrentShip().GetmForwardVector();
        mIsAlive = true;
    }

    public void Draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval((int) mPosition.x, (int) mPosition.y, 2, 2);
    }

    public void MissleMove(double deltaTime) {
        mPosition.x += mDirection.x * SPEED_BUFFER * deltaTime;
        mPosition.y += mDirection.y * SPEED_BUFFER * deltaTime;
    }

    public void Update(double deltaTime) {
        MissleMove(deltaTime);
        OutOfBounds();
    }

    @Override
    public void Collide(Collidable c) {
        if (c.GetName().contains("Asteroid")) {
            if (mIsAlive) {
                SetAlive(false);
            }
        }
    }

    @Override
    public String GetName() {
        return this.mName;
    }

    @Override
    public Rectangle GetBounds() {
        return new Rectangle((int) mPosition.getX(), (int) mPosition.getY(), 4, 4);
    }

    @Override
    public boolean IsAlive() {
        return this.mIsAlive;
    }

    @Override
    public void SetAlive(boolean b) {
        this.mIsAlive = b;
    }

    public void OutOfBounds() {
        if (IsAlive()) {
            if (this.mPosition.getX() > GameWindow.CANVAS_WIDTH || this.mPosition.getX() < 0 || this.mPosition.getY() > GameWindow.CANVAS_HEIGHT || this.mPosition.getY() < 0) {
                SetAlive(false);
            }
        }
    }

    @Override
    public double GetDeltaX(){
        return this.mDeltaX;
    }

    @Override
    public double GetDeltaY(){
        return this.mDeltaY;
    }

    @Override
    public void SetDeltaX(double d){
        this.mDeltaX = d;
    }

    @Override
    public void SetDeltaY(double d){
        this.mDeltaY = d;
    }

    @Override
    public void ReverseDirection(Collidable a) {
    }


}
