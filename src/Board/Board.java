package Board;


public class Board 
{
    private final int size;
    private final int[][] board; 

    /*  BOARD ARRAY CAN CONTAIN 6 VALUES; 
        0: empty space
        1: ship space
        2: shot done
        3: shot miss
        4: ship hit
        5: ship destroyed
    */

    public Board() 
    {
        this.size = 10;  // Tamaño fijo para este ejemplo
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
        if (isInBounds(x, y)) 
        {
            board[x][y] = value;
        }
    }

    public boolean isInBounds(int x, int y) 
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }
}
