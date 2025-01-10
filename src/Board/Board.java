package Board;


public class Board 
{
    private final int size;
    private final String[][] board; 

    /*  BOARD ARRAY CAN CONTAIN 6 VALUES; 
        DEFAULT: empty space
        SHIP: ship space
        SHOT: shot done
        MISS: shot miss
        HIT: ship hit
        DESTROYED: ship destroyed
    */

    public Board() 
    {
        this.size = 10;
        this.board = new String[size][size];
        initializeBoard("DEFAULT");
    }
    
    private void initializeBoard(String defaultValue) 
    {
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                board[i][j] = defaultValue;
            }
        }
    } 

    public int getSize() 
    {
        return size;
    }

    public String getBoardPosition(int x, int y)
    {
        return board[x][y];
    }

    public String[][] getBoard() 
    {
        return board;
    }

    public void setBoardPosition(int x, int y, String value) 
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

    public void setShip(int x, int y)
    {
        setBoardPosition(x, y, "SHIP");
    }
}
