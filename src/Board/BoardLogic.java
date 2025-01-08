package Board;

public class BoardLogic 
{ 
    static Board playerBoard = new Board();
    static Board aiBoard = new Board();

    public static void main(String[] args) 
    {
        DisplayGrid displayGrid = new DisplayGrid(playerBoard);

        displayGrid.setTitle("TEST");
    }

    public void buttonAction(int row, int column)
    {
        playerBoard.setBoardPosition(row, column, 2);    
    }
}