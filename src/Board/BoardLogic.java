package Board;

import Ai.*;
import Display.*;

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
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);


        changeTitles();
    }

    public void changeTitles() 
    {
        shipPlace.setTitle("Place Your Ships");
    }

    public void changeOtherTitle()
    {
        viewShips.setTitle("Your Grid");
        attackShips.setTitle("Enemy Grid");
    }

    public void onShipsPlaced()
    {
        ai.aiSetShips();
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(aiBoard);
        changeOtherTitle();
    }
    
    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}