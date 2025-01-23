package Main;

// Imports all necesary stuff
import Board.*;

public class Main 
{
    public BoardLogic test;

    public Main()
    {
        this.test = new BoardLogic();
    }

    public void playAgain()
    {
        test.startsActualGame();
    }
    public static void main(String[] args) 
    {
        Main main = new Main();
        main.playAgain();
    }    
}
