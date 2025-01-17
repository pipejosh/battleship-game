/*
Cristopher Rosas
9/12/2024 - 30/12/2024
This class is responsible for the game music
*/
package MusicScript;

// Imort all the necesary stuff
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class PlayMusic 
{
    // Initialize all the necesary variables
    public Clip clip;
    public FloatControl volumeControler;
    
    // Create the defaul constructor
    public PlayMusic() 
    {
    }

    // This method starts to play the song
    public void startSong(String soundKey, int loopTimes) 
    {

        String soundPath = "MainTheme.wav";
        

        // Try to exceture the action 
        try 
        {
            // Create the sound url
            URL soundUrl = getClass().getResource(soundPath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundUrl);

            // Start the song
            clip = AudioSystem.getClip();
            clip.open(audioInput);

            // Create the volume controler
            volumeControler = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            
            // Loop the song the timer it wants to loop
            clip.loop(loopTimes);
            // Set the song to a certain volume
        }

        // If theres a exception
        catch (Exception e)
        {
            // Print the error + message
            e.printStackTrace();
            System.out.println("AN ERROR OCCUR WHILE PLAYING THE SONG");
        }
    }

    // This method stops the song
    public void stopSong() 
    {
        // If the clip is different from null
        if (clip != null)
        {
            // We stop
            clip.stop();
        }
        // If the clip is null
        else
        {
            // We display that there are no songs currently playing
            System.out.println("NO SONGS CURRENTLY PLAYING");
        }
    }
    // This method changes the volume


    public static void main(String[] args) {
        
        PlayMusic test = new PlayMusic();


        test.startSong(null, 0);
        JOptionPane.showMessageDialog(null, "TEST", null, 0);
    }
}
