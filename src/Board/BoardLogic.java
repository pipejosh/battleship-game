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
        System.out.println("PLAYER TURN");
        playerGrid.deactivateButtons();
        AIGrid.activateButtons();
    }

    public void AITurn()
    {
        System.out.println("AI TURN");
        AIGrid.deactivateButtons();
        playerGrid.activateButtons();
    }

    public void addTurns()
    {
        System.out.println("CURRENT TURNS " + turns);
        determineTurn();
        turns += 1;
    }
}