package Board;

public class BoardLogic 
{ 
    public static void main(String[] args) 
    {
        DisplayGrid playerGrid = new DisplayGrid();

        playerGrid.setTitle("PLAYER GRID");

        DisplayGrid AIGrid = new DisplayGrid();
        
        AIGrid.setTitle("AI GRID");
    }
}