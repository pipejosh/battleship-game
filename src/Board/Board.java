package Board;

public class Board 
{
    private final int[][] board;
    private final int size;
    
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
    
    public void setPosition(int x, int y, int value) 
    {
        board[x][y] = value;
    }

}
