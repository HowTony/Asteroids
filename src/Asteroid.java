import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
	
	private int mXAxis;
	private int mYAxis;
	private int mWidth;
	private int mHeight;
	
	public Asteroid(int xAxis, int yAxis, int width, int height){
		mXAxis = xAxis;
		mYAxis = yAxis;
		mWidth = width;
		mHeight = height;
		
		
	}
	
	public void Draw(Graphics g){
		g.setColor(Color.RED);
		g.drawOval(mXAxis, mYAxis, mWidth, mHeight);
		
		
	}

}
