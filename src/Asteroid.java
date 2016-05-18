import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Asteroid{

	private final float SPEED_BUFFER = 0.000007f;
	private final double ASTEROID_SCALE = 15D;
	private Point.Double mPosition;
	private Point.Double mLargeAsteroidPointsArray[];
	private int mRenderArrayX[];
	private int mRenderArrayY[];

	private double mDeltaX;
	private double mDeltaY;
	
	public Asteroid(Point.Double startPoint){
		mPosition = startPoint;
		
		 mDeltaX =(Math.random() * 2) - 1;
		 mDeltaY =(Math.random() * 2) - 1;

		mLargeAsteroidPointsArray = new Point.Double[] { 
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
	
	public void Draw(Graphics g){
		for (int i = 0; i < mLargeAsteroidPointsArray.length; i++) {
			Point.Double currentPoint = mLargeAsteroidPointsArray[i];
			mRenderArrayX[i] = (int) currentPoint.x + (int) mPosition.x;
			mRenderArrayY[i] = (int) currentPoint.y + (int) mPosition.y;
		}
		g.setColor(Color.RED);
		g.drawPolygon(mRenderArrayX, mRenderArrayY, mLargeAsteroidPointsArray.length);
	}
	
	public void StartPoint(){
		this.mPosition.x =(int)Math.random()* GameWindow.CANVAS_WIDTH;
		this.mPosition.y =(int)Math.random()* GameWindow.CANVAS_HEIGHT;
	}

	public void AsteroidMove(){
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
		mPosition.x += mDeltaX * SPEED_BUFFER;
		mPosition.y += mDeltaY * SPEED_BUFFER;
	}

	public void Update(){
		AsteroidMove();
	}

}
