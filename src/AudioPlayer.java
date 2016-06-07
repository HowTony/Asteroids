import javax.sound.sampled.*;


/**
 * Created by Tony Howarth on 6/6/2016.
 */
public class AudioPlayer {

    private Clip mClip;

    public AudioPlayer(String s){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            mClip = AudioSystem.getClip();
            mClip.open(dais);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Play(){
        if(mClip == null){
            return;
        }
        Stop();
        mClip.setFramePosition(0);
        mClip.start();
    }

    public void Stop(){
        if(mClip.isRunning()){
            mClip.stop();
        }
    }

    public void Close(){
        Stop();
        mClip.close();
    }
}
