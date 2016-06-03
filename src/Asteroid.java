import java.awt.*;
import java.util.Arrays;
import java.util.Collections;


public class Asteroid extends Polygon implements Collidable {

    private final double SPEED_BUFFER = 50D;
    private final double mAsteroidLarge = 15D;
    private final double mAsteroidSmall = 7D;
    private double mAsteroidScale;
    private Point.Double mPosition;
    private Point.Double mLargeAsteroidPointsArray[];

    private int mRenderArrayX[];
    private int mRenderArrayY[];
    private String mName;
    private boolean mIsAlive;
    private double mDeltaX;
    private double mDeltaY;
    private int mId = 0;
    private int mLastCollision = 0;
    private final int LARGE_ASTEROID_SCORE = 100;
    private final int SMALL_ASTEROID_SCORE = 25;

    private static int sId = 0;

    public Asteroid(String name, int size, Point.Double startPoint) {
        mPosition = startPoint;
        mName = name;
        if (size == 1) {
            mAsteroidScale = mAsteroidLarge;
        } else {
            mAsteroidScale = mAsteroidSmall;
        }
        mDeltaX = (Math.random() * 2) - 1;
        mDeltaY = (Math.random() * 2) - 1;
        mIsAlive = true;

        mLargeAsteroidPointsArray = RandomAsteroid((int)(Math.random() * 5) + 1);

        for (int i = 0; i < mLargeAsteroidPointsArray.length; ++i) {
            mLargeAsteroidPointsArray[i].x *= mAsteroidScale;
            mLargeAsteroidPointsArray[i].y *= mAsteroidScale;
        }
        mRenderArrayX = new int[mLargeAsteroidPointsArray.length];
        mRenderArrayY = new int[mLargeAsteroidPointsArray.length];

        sId++;
        mId = sId;
    }

    public void Draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawPolygon(mRenderArrayX, mRenderArrayY, mLargeAsteroidPointsArray.length);
        //g.fillPolygon(mRenderArrayX, mRenderArrayY, mLargeAsteroidPointsArray.length);
        //Rectangle test = this.GetBounds();
        //g.drawRect((int)test.getX(),(int)test.getY(),test.width,test.height);
    }


    public Point.Double[] MakeAsteroids() {
        int nPoints = (1 + (int) Math.random() * 15) + 7;

        Point.Double asteroid[] = new Point.Double[nPoints];
        for (int i = 0; i < nPoints; i++) {
             asteroid[i] = new Point.Double(GetRandomNumber() , GetRandomNumber());
        }

        return asteroid;
    }

    public Double GetRandomNumber() {
        Double num = (1 +  Math.random() * 5) - 3;

        return num;
    }


    public double GetAsteroidSize() {
        return this.mAsteroidScale;
    }

    public Point.Double GetPosition() {
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

    public int GetId() {
        return mId;
    }

    public void SetLastCollision(Asteroid other) {
        mLastCollision = other.GetId();
    }

    @Override
    public void Collide(Collidable c) {
        if (mIsAlive) {
            if (c.GetName().contains("Missile")) {
                SetAlive(false);
                //System.out.println(GetName() + " Collided with " + c.GetName() + "!");
//            } else if (c.GetName().contains("Asteroid")) {
//                Asteroid a = (Asteroid)c;
//                this.ReverseDirection(a);
//                //System.out.println(GetName() + " has Collided with " + c.GetName());
            } else {
                System.out.println("Ship hit asteroid");
            }
        }
    }


    public int GetScoreValue() {
        if (mAsteroidScale == mAsteroidLarge) {
            return LARGE_ASTEROID_SCORE;
        } else {
            return SMALL_ASTEROID_SCORE;
        }
    }

    @Override
    public Rectangle GetBounds() {
        return new Polygon(mRenderArrayX, mRenderArrayY, mRenderArrayX.length).getBounds();
    }

    @Override
    public boolean IsAlive() {
        return this.mIsAlive;
    }

    @Override
    public void SetAlive(boolean b) {
        this.mIsAlive = b;
    }


    @Override
    public double GetDeltaX() {
        return this.mDeltaX;
    }

    @Override
    public double GetDeltaY() {
        return this.mDeltaY;
    }

    @Override
    public void SetDeltaX(double deltaX) {
        this.mDeltaX = deltaX;
    }

    @Override
    public void SetDeltaY(double deltaY) {
        this.mDeltaY = deltaY;
    }

    @Override
    public void ReverseDirection(Collidable a) {
        Asteroid otherAsteroid = (Asteroid) a;

        if (otherAsteroid != null) {
            if (mLastCollision != otherAsteroid.GetId()) {
                double DeltaXHolder = GetDeltaX();
                double DeltaYHolder = GetDeltaY();

                SetDeltaX(a.GetDeltaX());
                SetDeltaY(a.GetDeltaY());

                a.SetDeltaX(DeltaXHolder);
                a.SetDeltaY(DeltaYHolder);

                mLastCollision = otherAsteroid.GetId();
                otherAsteroid.SetLastCollision(this);
            }
        }
    }

    @Override
    public String GetName() {
        return this.mName;
    }


    public Point.Double[] RandomAsteroid(int num){
        System.out.println(num);


        Point.Double[] asteroid1 = new Point.Double[]{
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

        Point.Double[] asteroid2 = new Point.Double[]{
                new Point.Double(-2, 0),
                new Point.Double(-1, -0.5),
                new Point.Double(0, -2),
                new Point.Double(1, -1.5),
                new Point.Double(2, -.5),
                new Point.Double(1.5, 1),
                new Point.Double(.5, 2),
                new Point.Double(-1, 1.5)};

        Point.Double[] asteroid3 = new Point.Double[]{
                new Point.Double(-2, -1.5),
                new Point.Double(-2, -2),
                new Point.Double(-1,-2.5),
                new Point.Double(0, -2.5),
                new Point.Double(1, -2),
                new Point.Double(1.2, -1.5),
                new Point.Double(1, 0),
                new Point.Double(0, 1),
                new Point.Double(-2, 0.5),
                new Point.Double(-2.3, 0),
                new Point.Double(-2.4, -0.5),
                new Point.Double(-1, -1)};

        Point.Double[] asteroid4 = new Point.Double[]{
                new Point.Double(-1, -2.5),
                new Point.Double(0, -2),
                new Point.Double(1, -2.5),
                new Point.Double(1.5, -1),
                new Point.Double(1, -0.5),
                new Point.Double(2, 1),
                new Point.Double(0, 1.5),
                new Point.Double(-2.5, 0.5),
                new Point.Double(-2, 0),
                new Point.Double(-2.5, -0.8),
                new Point.Double(-1.8, -1.2),
                new Point.Double(-1, -2)};

        Point.Double[] asteroid5 = new Point.Double[]{
                new Point.Double(-1.8, -1),
                new Point.Double(-1, -1.8),
                new Point.Double(0, -2.1),
                new Point.Double(1, -2.2),
                new Point.Double(2, -1.65),
                new Point.Double(3, -0.8),
                new Point.Double(2, -0.95),
                new Point.Double(3, -0.2),
                new Point.Double(2, 1.7),
                new Point.Double(1.6, 1),
                new Point.Double(1, 1.2),
                new Point.Double(1.2, 1.9),
                new Point.Double(0, 1.9),
                new Point.Double(-1, 1.3),
                new Point.Double(-1.4, 1),
                new Point.Double(-1.7, 0),
                new Point.Double(-1, -0.5)};

        if(num == 1){
            return asteroid1;
        }else if(num == 2){
            return asteroid2;
        }else if(num == 3){
            return asteroid3;
        }else if(num == 4){
            return asteroid4;
        }else{
            return asteroid5;
        }
    }
}
