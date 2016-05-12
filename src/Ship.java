import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Ship {

	
	private double mXAxis;
	private double mYAxis;
	private final double SPEED_BUFFER = 0.00001;


	// SHIP
	 private int mPlayerX[];
	 private int mPlayerY[];
	 private int mPlayerNpoints;
	 private Polygon mShipPoly;

	public Ship(int xAxis, int yAxis) {
		mXAxis = xAxis;
		mYAxis = yAxis;
		
		mPlayerNpoints = 4;
		mPlayerX = new int[0];
		mPlayerY = new int[0];
	}


	public void Draw(Graphics g) {
		ShipUpdatePosistion();
		
		g.setColor(Color.BLUE);
		g.drawPolygon(mShipPoly);	
	}
	
	public void ShipUpdatePosistion(){
		mPlayerX = new int[] { (int)mXAxis + 5, (int)mXAxis - 15, (int)mXAxis + 5, (int)mXAxis};
		mPlayerY = new int[] { (int)mYAxis - 7, (int)mYAxis, (int)mYAxis + 7, (int)mYAxis};
		mShipPoly = new Polygon(mPlayerX, mPlayerY, mPlayerNpoints);
	
	}

	public void Update() {
		
	}

	public void Move(double deltaX, double deltaY) {
		double maxWidth = GameWindow.CANVAS_WIDTH;
		double maxHeight = GameWindow.CANVAS_HEIGHT;

		if (mXAxis > maxWidth) {
			mXAxis = 0;
		}
		if (mXAxis < 0) {
			mXAxis = (int) maxWidth;
		}

		if (mYAxis > maxHeight) {
			mYAxis = 0;
		}
		if (mYAxis < 0) {
			mYAxis = (int) maxHeight;
		}
		mXAxis += deltaX * SPEED_BUFFER;
		mYAxis += deltaY * SPEED_BUFFER;
	}

}
