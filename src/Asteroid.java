import java.awt.*;

public class Asteroid extends Polygon implements Collidable {

    private final double SPEED_BUFFER = 50D;
    private final double ASTEROID_SCALE = 15D;
    private Point.Double mPosition;
    private Point.Double mLargeAsteroidPointsArray[];
    private int mRenderArrayX[];
    private int mRenderArrayY[];
    private String mName;
    private boolean mIsAlive;
    private double mDeltaX;
    private double mDeltaY;

    public Asteroid(String name, Point.Double startPoint) {
        mPosition = startPoint;
        mName = name;
        mDeltaX = (Math.random() * 2) - 1;
        mDeltaY = (Math.random() * 2) - 1;
        mIsAlive = true;
        mLargeAsteroidPointsArray = new Point.Double[]{
                new Point.Double(-2, -1),
                new Point.Double(0, -2),
                new Point.Double(0, -1),
                new Point.Double(1, -2),
                new Point.Double(2, -1),
                new Point.Double(2, 1),
                new Point.Double(1, 1),
                new Point.Double(0, 2),
                new Point.Double(-1, 1),
                new Point.Double(-2, 0)};
        for (int i = 0; i < mLargeAsteroidPointsArray.length; ++i) {
            mLargeAsteroidPointsArray[i].x *= ASTEROID_SCALE;
            mLargeAsteroidPointsArray[i].y *= ASTEROID_SCALE;
        }
        mRenderArrayX = new int[mLargeAsteroidPointsArray.length];
        mRenderArrayY = new int[mLargeAsteroidPointsArray.length];
    }

    public void Draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawPolygon(mRenderArrayX, mRenderArrayY, mLargeAsteroidPointsArray.length);
        //g.fillPolygon(mRenderArrayX, mRenderArrayY, mLargeAsteroidPointsArray.length);
        //Rectangle test = this.GetBounds();
        //g.drawRect((int)test.getX(),(int)test.getY(),test.width,test.height);
    }

    @Override
    public String GetName(){
        return this.mName;
    }

    public void ReverseDirection() {
        mDeltaX -= (mDeltaX * 2);
        mDeltaY -= (mDeltaY * 2);
    }

    public double getDeltaX() {
        return this.mDeltaX;
    }

    public double getDeltaY() {
        return this.mDeltaY;
    }

    public void setDeltaX(double deltaX) {
        this.mDeltaX = deltaX;
    }

    public void setDeltaY(double deltaY) {
        this.mDeltaY = deltaY;
    }

    public Point.Double getPosition() {
        return this.mPosition;
    }

    public void AsteroidMove(double deltaTime) {
        double maxWidth = GameWindow.CANVAS_WIDTH;
        double maxHeight = GameWindow.CANVAS_HEIGHT;
        if (mPosition.x > maxWidth) {
            mPosition.x = 0;
        }
        if (mPosition.x < 0) {
            mPosition.x = maxWidth;
        }
        if (mPosition.y > maxHeight) {
            mPosition.y = 0;
        }
        if (mPosition.y < 0) {
            mPosition.y = maxHeight;
        }
        mPosition.x += mDeltaX * SPEED_BUFFER * deltaTime;
        mPosition.y += mDeltaY * SPEED_BUFFER * deltaTime;
    }

    public void Update(double deltaTime) {
        for (int i = 0; i < mLargeAsteroidPointsArray.length; i++) {
            Point.Double currentPoint = mLargeAsteroidPointsArray[i];
            mRenderArrayX[i] = (int) currentPoint.x + (int) mPosition.x;
            mRenderArrayY[i] = (int) currentPoint.y + (int) mPosition.y;
        }
        AsteroidMove(deltaTime);
    }

    @Override
    public void Collide(String name) {
        if(name.contains("Missile")) {
            if (mIsAlive) {
                SetAlive(false);
            }
            System.out.println( GetName() +  " Collided with " + name + "!");
        }
        else if(name.contains("Asteroid")) {
            ReverseDirection();
            System.out.println( GetName() +  " Collided with " + name + "!");
        }
    }

    @Override
    public Rectangle GetBounds(){
        return new Polygon(mRenderArrayX, mRenderArrayY, mRenderArrayX.length).getBounds();
    }

    public boolean IsAlive(){
        return this.mIsAlive;
    }

    public void SetAlive(boolean b){
        this.mIsAlive = b;
    }
}
