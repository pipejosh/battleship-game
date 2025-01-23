/*
 * TEAMMATES
 * 
 *  Lizzie
 *  Yaroslav
 *  Cristopher 
 * 
*/

// START JANUARY 6 2025
// END JANUARY 23 2025


package Main;

// Imports all necesary stuff
import Board.*;

public class Main 
{
    //submites BoardLogic
    public BoardLogic test;
    //creates BoardLogic
    public Main()
    {
        this.test = new BoardLogic();
    }
    //creates a function that starts a game again
    public void playAgain()
    {
        test.startsActualGame();
    }
    public static void main(String[] args) 
    {
        //approves main
        Main main = new Main();
        main.playAgain();
    }    
}
