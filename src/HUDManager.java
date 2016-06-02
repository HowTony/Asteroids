import java.awt.*;

/**
 * Created by Tony Howarth on 5/31/2016.
 */
public class HUDManager {

    private ScoreManager mScore;
    private ShipManager mShips;

    public HUDManager(ScoreManager score, ShipManager ships){
        mScore = score;
        mShips = ships;
    }

    public void Draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Score: " + mScore.GetScore(), 5, 10);
        g.drawString("Lives: " + mShips.GetShipLives(), 5, 20);
    }

}
