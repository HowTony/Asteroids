import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Ship {



	private final double SPEED_BUFFER = 0.00001;

	// SHIP
	private int mPlayerX[];
	private int mPlayerY[];
	private Polygon mShipPoly;
	private Point.Double mPosition;
	
	private Point mShipPointsArray[];
	
	int mRenderArrayX[];
	int mRenderArrayY[];

	public Ship(Point.Double startPoint) {
		mPosition = startPoint;
		
		
		mPlayerX = new int[0];
		mPlayerY = new int[0];
		mShipPointsArray = new Point[]{
				new Point(5, -7),
				new Point(-15, 0),
				new Point(5 ,7),
				new Point(0, 0)};
		
		mRenderArrayX = new int[mShipPointsArray.length];
		mRenderArrayY = new int[mShipPointsArray.length];		
	}

	public void Draw(Graphics g) {

		for(int i = 0; i < mShipPointsArray.length; i++){
			Point currentPoint = mShipPointsArray[i];
			mRenderArrayX[i] = currentPoint.x + (int)mPosition.x;
			mRenderArrayY[i] = currentPoint.y + (int)mPosition.y;
		}
		g.setColor(Color.BLUE);
		g.drawPolygon(mRenderArrayX, mRenderArrayY, mShipPointsArray.length);		
	}

	public void Update() {

	}

	public void Move(double deltaX, double deltaY) {
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
		mPosition.x += deltaX * SPEED_BUFFER;
		mPosition.y += deltaY * SPEED_BUFFER;
	}

}
