package Board;

public class BoardLogic 
{ 


    static DisplayGrid playerGrid = new DisplayGrid();
    static DisplayGrid AIGrid = new DisplayGrid();
    
    public int turns = 0;
    public static void main(String[] args) 
    {
        playerGrid.setTitle("PLAYER GRID");

        AIGrid.setTitle("AI GRID");
    }

    public void determineTurn()
    {
        int currentTurn = turns % 2;

        switch (currentTurn)
        {
            case 0 -> playerTurn();
            case 1 -> AITurn();
        }
    }

    public void playerTurn()
    {
        playerGrid.deactivateButtons();
        AIGrid.activateButtons();
    }

    public void AITurn()
    {
        AIGrid.deactivateButtons();
        playerGrid.activateButtons();
    }
    public void addTurns()
    {
        turns += 1;

        determineTurn();
    }
}