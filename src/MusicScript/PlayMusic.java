package MusicScript;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;


public class PlayMusic {
    Long currentFrame;
    Clip clip;

    String status;
    AudioInputStream audioInputStream;
    static String play;

    public void musicPlayer(){
        URL musicUrl = getClass().getResource(play);

    }
}
