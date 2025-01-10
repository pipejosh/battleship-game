package Board;

import Display.*;

public class BoardLogic 
{ 
    private final DisplayGrid playerGrid;
    private final DisplayShipPlace playerShips;
    private final Board playerBoard;
    private final Board aiBoard;


    public BoardLogic()
    {
        playerBoard = new Board();
        aiBoard = new Board();

        playerGrid = new DisplayGrid(aiBoard, this);
        playerShips = new DisplayShipPlace(playerBoard, null);

        // placeShipsGrid = new DisplayShipPlace(aiBoard, null);

        changeTitles();
    }

    public void changeTitles() 
    {
        playerGrid.setTitle("Player atack Grid");
        playerShips.setTitle("PONER BARQUITOS CHE");
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}