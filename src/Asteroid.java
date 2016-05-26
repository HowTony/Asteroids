import java.awt.*;

public class Asteroid implements Collidable {

    private final double SPEED_BUFFER = 50D;
    private final double ASTEROID_SCALE = 15D;
    private Point.Double mPosition;
    private Point.Double mLargeAsteroidPointsArray[];
    private int mRenderArrayX[];
    private int mRenderArrayY[];

    private double mDeltaX;
    private double mDeltaY;

    public Asteroid(Point.Double startPoint) {
        mPosition = startPoint;
        mDeltaX = (Math.random() * 2) - 1;
        mDeltaY = (Math.random() * 2) - 1;
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
        g.drawRect((int) mPosition.x - 30, (int) mPosition.y - 30, 55, 55);
    }


    public void changeDeltas(Asteroid otherAsteroid) {
        this.mDeltaX = otherAsteroid.getDeltaX();
        this.mDeltaY = otherAsteroid.getDeltaY();
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
    public void Collide() {

    }

    public Rectangle GetBounds() {
        return new Rectangle((int) getPosition().getX(), (int) getPosition().getY(), 5, 5);
    }

}
