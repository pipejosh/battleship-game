package Board;

public class Board 
{
    private final int size;
    private final int[][] playerBoard;
    private final int[][] AIBoard;

    /*  BOARD ARRAY CAN CONTAIN 6 VALUES; 
        0: DEFAULT / NOT DISCOVER
        1: CLICKED BUTTON
        2: USER || AI SHIP 
        3: USER || AI SHOOT
        4: USER || AI SHIP SHOT
        5: USER || AI SHIP DISTROYED
    */
    
    public Board() 
    {
        this.size = 10;
        this.playerBoard = new int[size][size];
        this.AIBoard = new int[size][size];
    }
    
    public int getSize() 
    {
        return size;
    }
    
    public int[][] getPlayerBoard() 
    {
        return playerBoard;
    }

    public int[][] getAIBoard()
    {
        return AIBoard;
    }
    
    public void setPlayerBoardPosition(int x, int y, int value) 
    {
        playerBoard[x][y] = value;
    }

    public void setAIBoadPostion(int x, int y, int value)
    {
        playerBoard[x][y] = value;
    }

    public boolean isInBounds(int x, int y)
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

}
