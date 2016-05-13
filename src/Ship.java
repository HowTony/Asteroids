import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Ship {

	//private final double SPEED_BUFFER = 0.00001;

	// SHIP

	private Point mShipPointsArray[];
	int mRenderArrayX[];
	int mRenderArrayY[];
	static double mRotationAngle;

	public Ship() {
		mShipPointsArray = new Point[] { 
				new Point(-13, -15),
				new Point(30, 0), 
				new Point(-13, 15), 
				new Point(-5, 0),
				new Point(-13, -15) };

		mRenderArrayX = new int[mShipPointsArray.length];
		mRenderArrayY = new int[mShipPointsArray.length];
		mRotationAngle = 0;
	}

	public void Draw(Graphics g) {
	
		if (UserInput.isPressed() && UserInput.getkeyHeldCode() == 68) {
			mRotationAngle += .05;
		} else {
			if (UserInput.isPressed() && UserInput.getkeyHeldCode() == 65) {
				mRotationAngle -= .05;
			}
		}
		
		AffineTransform af = new AffineTransform();
		Graphics2D graphicSettings = (Graphics2D)g;

		graphicSettings.setTransform(af);
		graphicSettings.translate(GameWindow.CANVAS_WIDTH/ 2, GameWindow.CANVAS_HEIGHT /2);
		graphicSettings.rotate(Math.toRadians(mRotationAngle));

		graphicSettings.setColor(Color.BLUE);
		graphicSettings.drawPolygon(mRenderArrayX, mRenderArrayY, mShipPointsArray.length);
	}

	public void Update() {

	}

	public void Move(double deltaX, double deltaY) {
	
	}

}
