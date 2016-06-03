import java.awt.*;

/**
 * Created by Tony Howarth on 5/31/2016.
 */
public class HUDManager {

    private ScoreManager mScore;
    private ShipManager mShips;
    private Font mDeadFont;

    public HUDManager(ScoreManager score, ShipManager ships){
        mScore = score;
        mShips = ships;
        mDeadFont = new Font("TimesRoman", Font.BOLD, 40);
    }

    public void Draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("Score: " + mScore.GetScore(), 5, 10);
        g.drawString("Lives: " + mShips.GetShipLives(), 5, 20);

        if(mShips.GetShipLives() < 1){
            g.setColor(Color.RED);
            g.setFont(mDeadFont);
            g.drawString("Game Over!", GameWindow.CANVAS_WIDTH * 2 / 5, GameWindow.CANVAS_HEIGHT /2);
        }
    }
}
