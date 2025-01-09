package Board;

public class BoardLogic 
{ 
    private final DisplayGrid playerGrid;
    private final DisplayGrid aiGrid;
    private final Board playerBoard;
    private final Board aiBoard;

    public BoardLogic()
    {
        playerBoard = new Board();
        aiBoard = new Board();

        playerGrid = new DisplayGrid(playerBoard, this);
        aiGrid = new DisplayGrid(aiBoard, this);

        changeTitles();
        testShot();
    }

    public void testShot()
    {
        playerBoard.setBoardPosition(5, 5, 1);
    }

    public void changeTitles() 
    {
        playerGrid.setTitle("Player Grid");
        aiGrid.setTitle("AI Grid");
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}