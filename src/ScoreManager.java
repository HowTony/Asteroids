/**
 * Created by Tony Howarth on 5/31/2016.
 */
public class ScoreManager {

    private int mScore;

    public ScoreManager(){
        mScore = 0;
    }

    public void AddScore(int score){
        mScore += score;
    }

    public int GetScore(){
        return this.mScore;
    }




}
