package Board;

import Ai.*;
import Display.*;
import MusicScript.*;
import javax.swing.*;
import Forms.*;
public class BoardLogic 
{ 
    // Declares stuff
    private Timer updateTimer;

    private DisplayShipPlace shipPlace;
    private DisplayViewGrid viewShips;
    private DisplayAttackGrid attackShips;

    private Board playerBoard = new Board();
    private Board aiBoard = new Board();
    
    private AdvanceAi ai = new AdvanceAi(aiBoard, playerBoard);

    private boolean isPlayerTurn = false;
    private PlayMusic musicPlayer = new PlayMusic();

    public Lose loseForm = new Lose();
    public YouWin winForm = new YouWin();

    // Default construcor
    public BoardLogic()
    {
    }
    
    // Changes the title
    public void changeShipPlacerTitle() 
    {
        shipPlace.setTitle("Place Your Ships");
    }
    
    // Changes other titles
    public void changeGridTitles()
    {
        viewShips.setTitle("Your Grid");
        attackShips.setTitle("Enemy Grid");
    }
    
    // This runs onnce the ships are placed
    public void onShipsPlaced()
    {
        musicPlayer.stopSong();
        musicPlayer.startSong(1, 10000);
        //the music player activates when the game opens and stops after the ships are don ebeing placed
        ai.aiSetShips();
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(aiBoard, this);
        changeGridTitles();
        starTimer();

    }

    // Starts the timer and every milisecond calls gameTime
    public void starTimer()
    {
        updateTimer = new Timer(1, e -> gameTime());
        updateTimer.start();
    }
    
    // Stops the timer
    public void stopTimer()
    {
        if (updateTimer != null)
        {
            updateTimer.stop();
        }
    }

    // Call the display ships place once is all set call onshipplacer and starts the song
    public void startGame()
    {
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);
        musicPlayer.startSong(0, 0);

        changeShipPlacerTitle();
    }

    // Handle turns if is the player turn player attacks if not ai attacks and at the end checks if anyone wons
    public void gameTime()
    {
        if (isPlayerTurn)
        {
            playerAttack();
        }

        else
        {
            aiAttack();
        }

        checkWin();
    }

    // Player attack updates the buttons
    public void playerAttack()
    {
        attackShips.updateButtons();
    }
    
    // Ai attack
    public void aiAttack()
    {
        try
        {
            Thread.sleep(2 * 1000);    
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Waits 2 secons and the ai attacks, disable all butons (so they cant interfiere and changes the playerturn boolean)
        ai.aiAttack();
        attackShips.setButtons(false);
        isPlayerTurn = true;
    }

    // Sets the player turn
    public void setIsPlayerTurn(boolean newValue)
    {
        this.isPlayerTurn = newValue;
    }

    // If on any board they win it display the form that handles that + closes the windows
    public void checkWin()
    {
        if (aiBoard.hasWon())
        {
            winForm.setVisible(true);
            System.out.println("THE PLAYER WINS");
            updateTimer.stop();
            closeWindows();
            musicPlayer.stopSong();
        }
        
        else if (playerBoard.hasWon())
        {
            loseForm.setVisible(true); 
            System.out.println("THE AI WINS");
            updateTimer.stop();
            closeWindows();
            musicPlayer.stopSong();
        }
    }

    // Starst the game
    public void startsActualGame()
    {
        startGame();
    }

    // Close the windows
    public void closeWindows()
    {     
        attackShips.dispose();
        viewShips.dispose();
    }
}