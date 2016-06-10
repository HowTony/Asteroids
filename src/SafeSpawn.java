import java.awt.*;

/**
 * Created by Tony Howarth on 6/8/2016.
 */
public class SafeSpawn{

    private int mWidth;
    private int mHeight;
    private Rectangle mSafeZone;
    private boolean mIsSafe = false;
    private ShipManager mShips;

    public SafeSpawn(ShipManager ships, int posX, int posY, int width, int height){
        mWidth = width;
        mHeight = height;
        mShips = ships;
        mSafeZone = new Rectangle(posX, posY, mWidth, mHeight);
    }

    public Rectangle GetBounds(){
        return mSafeZone.getBounds();
    }

    public boolean IsSafe(){
        return this.mIsSafe;
    }

    public void SetIsSafe(boolean b){
        mIsSafe = b;
    }

    public void SetSMIsSafe(){
        mShips.SetSafeSpawn(IsSafe());
    }

    public void Update(double deltaTime){
        SetSMIsSafe();
    }








}
