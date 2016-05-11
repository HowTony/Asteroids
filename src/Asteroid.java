import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Asteroid {
	
	private double mXAxis;
	private double mYAxis;
	private int mWidth;
	private int mHeight;
	private final float SPEED_BUFFER = 0.000001f;
	
	
	
	private Polygon mFirstLargeAsteroid;

	private int mLargeFirstX[] = {68, 71, 56, 56, 67, 79, 93, 107, 92};
	private int mLargeFirstY[] = {12, 24, 27, 53, 46, 61, 50, 54, 7};
	private int mLargeFirstNpoints = 9;
	
	private Polygon mSecondLargeAsteroid;
	
	private int xpoints1[] = {118, 121, 106, 106, 114, 118, 128, 143, 157, 153, 142};
	private int largeSecondY[] = {42, 54, 57, 83, 76, 86, 92, 80, 84, 59, 37};
	private int largeSecondNpoints = 11;
	
	

	
	public Asteroid(double xAxis, double yAxis, int width, int height){
		mXAxis = xAxis;
		mYAxis = yAxis;
		mWidth = width;
		mHeight = height;
		mFirstLargeAsteroid = new Polygon(mLargeFirstX, mLargeFirstY, mLargeFirstNpoints);
		mSecondLargeAsteroid = new Polygon(xpoints1, largeSecondY, largeSecondNpoints);
		
		
		
	}
	
	public void Draw(Graphics g){
//		g.setColor(Color.RED);
//		
//		g.drawPolygon(mFirstLargeAsteroid);
//		
//		g.setColor(Color.GREEN);
//		
//		g.drawPolygon(mSecondLargeAsteroid);
		
		//g.drawOval((int)mXAxis, (int)mYAxis, mWidth, mHeight);
		
		
	}
	
	public double getXAxis() {
		return mXAxis;
	}
	
	public double getYAxis() {
		return mYAxis;
	}
	
	
	public void AsteroidMove(){
	
	}
	
	
	
	
	
	public void Update(){
		
	}


	

}
