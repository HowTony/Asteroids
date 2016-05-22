import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Ship extends Polygon{

	private final double ROTATION_BUFFER = 0.000006;
	private final double SPEED_BUFFER = 0.00005;
	private final double SHIP_SCALE = 5D;

	// SHIP
	private int mPlayerX[];
	private int mPlayerY[];

	private Point.Double mPosition;
	private Point.Double mOrigin;
	private Point.Double mForwardVector;
	private AffineTransform mTransform;
	private double mRotationDelta;

	private Point.Double mShipPointsArray[];

	int mRenderArrayX[];
	int mRenderArrayY[];

	public Ship() {
		mPlayerX = new int[0];
		mPlayerY = new int[0];
		mShipPointsArray = new Point.Double[] { 
				new Point.Double(-3, 0), 
				new Point.Double(2, 2), 
				new Point.Double(1, 0),
				new Point.Double(2, -2) };

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
		g.fillPolygon(mRenderArrayX, mRenderArrayY, mShipPointsArray.length);
	}

	public void Update() {
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
        return new Point.Double(mForwardVector.x,mForwardVector.y);
    }

    public Point.Double getMissleStart(){
        return new Point.Double((int)(mShipPointsArray[0].getX() + mPosition.getX()), (int)(mShipPointsArray[0].getY()+ mPosition.getY()));
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
}