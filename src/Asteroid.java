import java.awt.*;

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

    public Asteroid(String name, int size, Point.Double startPoint) {
        mPosition = startPoint;
        mName = name;
        if(size == 1){
            mAsteroidScale = mAsteroidLarge;
        }else {
            mAsteroidScale = mAsteroidSmall;
        }
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
            mLargeAsteroidPointsArray[i].x *= mAsteroidScale;
            mLargeAsteroidPointsArray[i].y *= mAsteroidScale;
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


    public double GetAsteroidSize(){
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
            }else{
                System.out.println("SHip hit asteroid");
            }
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
        double DeltaXHolder = mDeltaX;
        double DeltaYHolder = mDeltaY;

        this.mDeltaX = a.GetDeltaX();
        this.mDeltaY = a.GetDeltaY();

        a.SetDeltaX(DeltaXHolder);
        a.SetDeltaY(DeltaYHolder);
    }

    @Override
    public String GetName() {
        return this.mName;
    }
}
