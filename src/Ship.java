import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ship implements Collidable {

    private final double ROTATION_BUFFER = 6D;
    private final double SPEED_BUFFER = 150D;
    private final double SHIP_SCALE = 4D;

    // SHIP
    private int mPlayerX[];
    private int mPlayerY[];
    private Point.Double mPosition;
    private Point.Double mOrigin;
    private Point.Double mForwardVector;
    private AffineTransform mTransform;
    private double mRotationDelta;
    private String mName;
    private boolean mIsAlive = true;
    private Point.Double mShipPointsArray[];
    private int mRenderArrayX[];
    private int mRenderArrayY[];

    private double mDeltaX;
    private double mDeltaY;


    public Ship() {
        mName = "Ship";
        mPlayerX = new int[0];
        mPlayerY = new int[0];
        mShipPointsArray = new Point.Double[]{
                new Point.Double(-3, 0),
                new Point.Double(2, 2),
                new Point.Double(1, 0),
                new Point.Double(2, -2)};

        // scale the ship
        for (int i = 0; i < mShipPointsArray.length; ++i) {
            mShipPointsArray[i].x *= SHIP_SCALE;
            mShipPointsArray[i].y *= SHIP_SCALE;
        }

        mRenderArrayX = new int[mShipPointsArray.length];
        mRenderArrayY = new int[mShipPointsArray.length];

        mOrigin = new Point.Double((double) GameWindow.CANVAS_WIDTH / 2, (double) GameWindow.CANVAS_HEIGHT / 2);
        mPosition = mOrigin;
        mForwardVector = new Point.Double(-10, 0);

        mTransform = new AffineTransform();
        mRotationDelta = 0D;
    }

    public void Draw(Graphics g) {
        // do all the drawing...
        g.setColor(Color.BLUE);
        g.drawPolygon(mRenderArrayX, mRenderArrayY, mShipPointsArray.length);
//        g.fillPolygon(mRenderArrayX, mRenderArrayY, mShipPointsArray.length);
//        Rectangle test = this.GetBounds();
//        g.drawRect((int) test.getX(), (int) test.getY(), test.width, test.height);
    }

    public void Update(double deltaTime) {
        mTransform.rotate(mRotationDelta, 0, 0);
        mTransform.transform(mForwardVector, mForwardVector);

        // normalize the forward vector
        double mag = mForwardVector.x * mForwardVector.x + mForwardVector.y * mForwardVector.y;
        mag = Math.sqrt(mag);

        mForwardVector.x /= mag;
        mForwardVector.y /= mag;

        // rotate render geometry also
        for (int i = 0; i < mShipPointsArray.length; i++) {
            mTransform.transform(mShipPointsArray[i], mShipPointsArray[i]);
            Point.Double currentPoint = mShipPointsArray[i];

            mRenderArrayX[i] = (int) currentPoint.x + (int) mPosition.x;
            mRenderArrayY[i] = (int) currentPoint.y + (int) mPosition.y;
        }

        // reset rotation in preparation for next draw
        mTransform.setToIdentity();
        mRotationDelta = 0D;
    }

    public Point.Double getmForwardVector() {
        return new Point.Double(mForwardVector.x, mForwardVector.y);
    }

    public Point.Double getMissleStart() {
        return new Point.Double((int) (mShipPointsArray[0].getX() + mPosition.getX()), (int) (mShipPointsArray[0].getY() + mPosition.getY()));
    }

    public void Rotate(double theta) {
        mRotationDelta += theta * ROTATION_BUFFER;
    }

    public void Move(double delta) {
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
        mPosition.x += mForwardVector.x * delta * SPEED_BUFFER;
        mPosition.y += mForwardVector.y * delta * SPEED_BUFFER;
    }

    @Override
    public void Collide(Collidable c) {
        if (c.GetName().contains("Asteroid")) {
            //System.out.println(GetName() + " Collided with " + c.GetName() + "!");
        }
    }

    @Override
    public Rectangle GetBounds() {
        return new Polygon(mRenderArrayX, mRenderArrayY, mRenderArrayX.length).getBounds();
    }


    @Override
    public String GetName() {
        return this.mName;
    }

    @Override
    public boolean IsAlive() {
        return this.mIsAlive;
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

    @Override
    public void SetAlive(boolean b){
        this.mIsAlive = b;
    }

}