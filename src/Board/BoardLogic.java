package Board;

public class BoardLogic 
{ 
    private final DisplayGrid playerGrid;
    private final DisplayGrid playerShips;
    private final Board playerBoard;
    private final Board aiBoard;

    public BoardLogic()
    {
        playerBoard = new Board();
        aiBoard = new Board();

        playerGrid = new DisplayGrid(aiBoard, this, DisplayGridMode.ATTACK);
        playerShips = new DisplayGrid(playerBoard, null, DisplayGridMode.VIEW);

        changeTitles();
    }

    public void changeTitles() 
    {
        playerGrid.setTitle("Player atack Grid");
        playerShips.setTitle("Player ships grid");
    }

    public static void main(String[] args) 
    {
        new BoardLogic(); 
    }
}