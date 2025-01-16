package Board;

import Display.*;

public class BoardLogic 
{ 
    private DisplayShipPlace shipPlace;
    private DisplayViewGrid viewShips;
    private DisplayAttackGrid attackShips;

    private DisplayShipPlace aiShipPlace;
    private DisplayViewGrid aiViewShips;
    private DisplayAttackGrid aiAttackShips;

    private Board playerBoard = new Board();
    private Board aiBoard = new Board();

    public BoardLogic()
    {
        shipPlace = new DisplayShipPlace(playerBoard);
        shipPlace.setOnShipsPlacedCallback(this::onShipsPlaced);

        aiShipPlace = new DisplayShipPlace(aiBoard);
        aiShipPlace.setOnShipsPlacedCallback(this::aiOnShipsPlaced);

        changeTitles();
    }

    public void changeTitles() 
    {
        shipPlace.setTitle("TEST");
        aiShipPlace.setTitle("OTHER TEST");
    }

    public void changeOtherTitle()
    {
        viewShips.setTitle("TEST!!!!");
        attackShips.setTitle("TEST!!!!!!!!!!");
    }

    public void changeOtherOtherTitle()
    {
        aiViewShips.setTitle("CHEST!!!!");
        aiAttackShips.setTitle("CHEST!!!!!!!!!!");
    }

    public void onShipsPlaced()
    {
        viewShips = new DisplayViewGrid(playerBoard);
        attackShips = new DisplayAttackGrid(aiBoard, null);
        changeOtherTitle();
    }
    
    public void aiOnShipsPlaced()
    {
        aiViewShips = new DisplayViewGrid(aiBoard);
        aiAttackShips = new DisplayAttackGrid(playerBoard, null);
        changeOtherOtherTitle();

    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}