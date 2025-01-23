//this class implements the music into the game
package MusicScript;


import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlayMusic 
{
    
    public Clip clip;
    public FloatControl volumeControler;
    
    
    public PlayMusic() 
    {
    }

    
    public void startSong(int song, int loopTimes) 
    {

        String boatPlacement = "MainTheme.wav";
        String actualGame = "GameTheme.wav";
        String soundPath = "";

        // Depends the value the sound path changes
        switch (song)
        {
            case 0 -> soundPath = boatPlacement;
            case 1 -> soundPath = actualGame;
        }

        //this is the try-catch that allows the music to play in a loop
        try 
        {
            //This creates the sound url
            URL soundUrl = getClass().getResource(soundPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);

            //This starts the song
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            volumeControler = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            //this clip.loop loops the background music
            clip.loop(loopTimes);
           
        }

        catch (Exception e)
        {
            
            e.printStackTrace();
            System.out.println("AN ERROR OCCUR WHILE PLAYING THE SONG");
        }
    }

    public void stopSong() 
    {
        //the clip stops if it comes back null
        if (clip != null)
        {
            
            clip.stop();
        }
        
        else
        {
            System.out.println("NO SONGS CURRENTLY PLAYING");
        }
    }

//this is a test to make sure the music works
}
