package Ship;

import Board.Board;

public class Ship 
{
    public int shipSize = 0;    
    public boolean isVertical;
    public int shipCounts = 0;

    
    private Board currentBoard;
    private String[][] board;

    public Ship(int shipSize, boolean isVertical, Board board, int shipCounts)
    {
        this.shipCounts = shipCounts;
        this.currentBoard = board;
        this.board = currentBoard.getBoard();
        this.shipSize = shipSize;
        this.isVertical = isVertical;
    }

    public int getShipSize()
    {
        return this.shipSize;
    }

    public void placeShips(int x, int y)
    {
        currentBoard.setShips(x, y);
    }

    public void setShipSize(int newSize)
    {
        this.shipSize = newSize;
    }
}
