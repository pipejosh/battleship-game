package Board;

import java.util.Arrays;

public class Board 
{
    private final int size;
    private final int[][] board; 

    /*  BOARD ARRAY CAN CONTAIN 5 VALUES; 
        0: DEFAULT / NOT DISCOVER
        1: USER || AI SHIP 
        2: USER || AI SHOOT
        3: USER || AI SHIP SHOT
        4: USER || AI SHIP DISTROYED
    */
    
    public Board() 
    {
        this.size = 10;
        this.board = new int[size][size];
    }
    
    public int getSize() 
    {
        return size;
    }
    
    public int[][] getBoard() 
    {
        return board;
    }

    public void setBoardPosition(int x, int y, int value) 
    {
        if (!isInBounds(x, y)) return;

        board[x][y] = value;
    }

    public void shot(int x, int y) 
    {
        if (!isInBounds(x, y)) return;

        switch (board[x][y]) 
        {
            case 0 -> board[x][y] = 3;
            case 1 -> board[x][y] = 4;
        }
    }

    public boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

}
