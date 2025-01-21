package Board;

import Ai.*;
import Display.*;
import MusicScript.*;

import javax.swing.*;
public class BoardLogic 
{ 
    private Timer updateTimer;

    private DisplayShipPlace shipPlace;
    private DisplayViewGrid viewShips;
    private DisplayAttackGrid attackShips;

    private Board playerBoard = new Board();
    private Board aiBoard = new Board();
    
    private AdvanceAi ai = new AdvanceAi(aiBoard, playerBoard);

    private boolean isPlayerTurn = false;
    private PlayMusic musicPlayer = new PlayMusic();

    public BoardLogic()
    {
        startGame();   
    }
    
    public void changeShipPlacerTitle() 
    {
        shipPlace.setTitle("Place Your Ships");
    }
    
    public void changeGridTitles()
    {
        viewShips.setTitle("Your Grid");
        attackShips.setTitle("Enemy Grid");
    }
    
    public void onShipsPlaced()
    {
        musicPlayer.stopSong();
        ai.aiSetShips();
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(aiBoard, this);
        changeGridTitles();
        starTimer();

    }

    public void starTimer()
    {
        updateTimer = new Timer(1, e -> gameTime());
        updateTimer.start();
    }
    
    public void stopTimer()
    {
        if (updateTimer != null)
        {
            updateTimer.stop();
        }
    }

    public void startGame()
    {
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);
        musicPlayer.startSong(null, 0);

        changeShipPlacerTitle();
    }

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

    public void playerAttack()
    {
        attackShips.setButtons(true);
    }
    
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

        ai.aiAttack();
        attackShips.setButtons(false);
        isPlayerTurn = true;
    }

    public void setIsPlayerTurn(boolean newValue)
    {
        this.isPlayerTurn = newValue;
    }

    public void checkWin()
    {
        if (aiBoard.hasWon())
        {
            System.out.println("THE PLAYER WINS");
            updateTimer.stop();
        }
        
        else if (playerBoard.hasWon())
        {
            System.out.println("THE AI WINS");
            updateTimer.stop();
        }
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}