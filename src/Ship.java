import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

@SuppressWarnings("serial")
public class Ship extends Polygon {

	// private final double SPEED_BUFFER = 0.00001;

	private double mXVelocity = 0;
	private double mYVelocity = 0;
	private int mGWWidth = GameWindow.CANVAS_WIDTH;
	private int mGWHeight = GameWindow.CANVAS_HEIGHT;
	private double mCenterX = mGWWidth / 2;
	private double mCenterY = mGWHeight / 2;
	private int mShipWidth = 27;
	private int mShipHeight = 30;

	public static int[] mPolyXArray = { -13, 14, -13, -5, -13 };
	public static int[] mPolyYArray = { -15, 0, 15, 0, -15 };

	private double mScreenXPos = getXCenter() + this.mPolyXArray[0];
	private double mScreenYPos = getYCenter() + this.mPolyYArray[0];

	private double mRotationAngle;
	private double mMovingAngle;

	public Ship() {
		super(mPolyXArray, mPolyYArray, mPolyXArray.length);
	}

	public void Draw(Graphics g) {
		Graphics2D graphicSettings = (Graphics2D) g;
		if (UserInput.isPressed() && UserInput.getkeyHeldCode() == 68) {
			this.increaseRotationAngle();
		} else {
			if (UserInput.isPressed() && UserInput.getkeyHeldCode() == 65) {
				this.decreaseRotationAngle();
			}
		}

		if (UserInput.isPressed() && UserInput.getkeyHeldCode() == 87) {
			System.out.println("Thrust");
			setmMoveAngle(mRotationAngle);
			increaseXVelocity(shipXMoveAngle(mMovingAngle) * 1);
			increaseYVelocity(shipYMoveAngle(mMovingAngle) * 1);
			move();
		}
		
		

		AffineTransform af = new AffineTransform();

		graphicSettings.setTransform(af);
		graphicSettings.translate(GameWindow.CANVAS_WIDTH / 2, GameWindow.CANVAS_HEIGHT / 2);
		graphicSettings.rotate(Math.toRadians(mRotationAngle));

		graphicSettings.setColor(Color.BLUE);
		graphicSettings.drawPolygon(this);
	}

	public void Update() {

	}

	public double getYCenter() {
		return mCenterY;
	}

	public double getXCenter() {
		return mCenterX;
	}

	public void setYCenter(double yCenter) {
		this.mCenterY = yCenter;
	}

	public void setXCenter(double xCenter) {
		this.mCenterX = xCenter;
	}

	public void increaseXPos(double incAmt) {
		this.mCenterX += incAmt;
	}

	public void increaseYPos(double incAmt) {
		this.mCenterY += incAmt;
	}

	public double getScreenXPos() {
		return mScreenXPos;
	}

	public double getScreenYPos() {
		return mScreenYPos;
	}

	public void setScreenXPos(double mScreenXPos) {
		this.mScreenXPos = mScreenXPos;
	}

	public void setScreenYPos(double mScreenYPos) {
		this.mScreenYPos = mScreenYPos;
	}

	public int getShipWidth() {
		return mShipWidth;
	}

	public int getShipHeight() {
		return mShipHeight;
	}

	public double getXVelocity() {
		return mXVelocity;
	}

	public double getYVelocity() {
		return mYVelocity;
	}

	public void setXVelocity(double mXVelocity) {
		this.mXVelocity = mXVelocity;
	}

	public void setYVelocity(double mYVelocity) {
		this.mYVelocity = mYVelocity;
	}

	public void increaseXVelocity(double xVel) {
		this.mXVelocity += xVel;
	}

	public void increaseYVelocity(double yVel) {
		this.mYVelocity += yVel;
	}

	public void decreaseXVelocity(double xVel) {
		this.mXVelocity -= xVel;
	}

	public void decreaseYVelocity(double yVel) {
		this.mYVelocity -= yVel;
	}

	public double getmMoveAngle() {
		return mMovingAngle;
	}

	public void setmMoveAngle(double moveAngle) {
		this.mMovingAngle = moveAngle;
	}

	public void increaseMovingAngle(double moveAngle) {
		this.mMovingAngle += moveAngle;
	}

	public void decreaseMovingAngle(double moveAngle) {
		this.mMovingAngle -= moveAngle;
	}

	public double shipXMoveAngle(double xMoveAngle) {
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
	}

	public double shipYMoveAngle(double yMoveAngle) {
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
	}

	public double getRotationAngle() {
		return mRotationAngle;
	}

	public void increaseRotationAngle() {
		if (getRotationAngle() >= 355) {
			mRotationAngle = 0;
		} else {
			mRotationAngle += 0.05;
		}
	}

	public void decreaseRotationAngle() {
		if (getRotationAngle() <= 0) {
			mRotationAngle = 355;
		} else {
			mRotationAngle -= 0.05;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(getShipWidth() - 14, getShipHeight() - 15, getShipWidth(), getShipHeight());
	}

	public void move() {
		this.increaseXPos(this.getXVelocity());

		if (this.getXCenter() < 0) {
			this.setXCenter(mGWWidth);
		} else {
			if (this.getXCenter() > mGWWidth) {
				this.setXCenter(0);
			}
		}

		this.increaseYPos(this.getYVelocity());

		if (this.getYCenter() < 0) {
			this.setYCenter(mGWHeight);
		} else {
			if (this.getYCenter() > mGWHeight) {
				this.setYCenter(0);
			}
		}
	}

}
