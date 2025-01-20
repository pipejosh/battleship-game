package Board;

import Display.*;
import Ai.*;

public class BoardLogic 
{ 
    private DisplayShipPlace shipPlace;
    private DisplayViewGrid viewShips;
    private DisplayAttackGrid attackShips;

    private Board playerBoard = new Board();
    private Board aiBoard = new Board();
    
    private AdvanceAi ai = new AdvanceAi(aiBoard, playerBoard);


    public BoardLogic()
    {

        startGame();   
    }
    
    public void changeShipPlacerTitle() 
    {
        shipPlace.setTitle("Place Ships");
    }
    
    public void changeGridTitles()
    {
        viewShips.setTitle("Your Grid");
        attackShips.setTitle("Enemy Grid");
    }
    
    public void onShipsPlaced()
    {
        ai.aiSetShips();
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(aiBoard, this);
        changeGridTitles();

    }
    
    public void startGame()
    {
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);

        changeShipPlacerTitle();
    }


    public void playerAttack()
    {
        attackShips.setButtons(true);
    }
    
    public void aiAttack()
    {
        attackShips.setButtons(false);
        ai.aiAttack();
        attackShips.setButtons(true);
    }

    public boolean isPlayerTurn()
    {
        return attackShips.getIsPlayerTurn();
    }

    public void endPlayerTurn()
    {
        attackShips.setIsPlayerTurn(false);
        attackShips.setButtons(false);
        aiAttack();
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}