package Board;

import Display.*;

public class BoardLogic 
{ 
    private final DisplayAttackGrid playerAttackGrid;
    private final DisplayShipPlace playerShips;
    private final Board playerBoard;


    public BoardLogic()
    {
        playerBoard = new Board();
        
        playerShips = new DisplayShipPlace(playerBoard, null);
        
        playerAttackGrid = new DisplayAttackGrid(playerBoard, this);

        changeTitles();
    }

    public void changeTitles() 
    {
        playerShips.setTitle("PONER BARQUITOS CHE");
        playerAttackGrid.setTitle("TESTOOOO");
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}