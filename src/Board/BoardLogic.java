package Board;

import Display.*;

public class BoardLogic 
{ 
    private DisplayShipPlace shipPlace;
    private DisplayViewGrid viewShips;
    private DisplayAttackGrid attackShips;

    private Board playerBoard = new Board();

    public BoardLogic()
    {
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);
    }

    public void changeTitles() 
    {
    }

    public void onShipsPlaced()
    {
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(playerBoard, null);
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}