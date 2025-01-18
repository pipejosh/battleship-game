package Ai;

import Board.*;
import Ship.PlaceShips;
import Display.*;

import java.util.*;

public class Ai 
{
    private static Board board = new Board();
    private PlaceShips shipPlacer = new PlaceShips(board);
    private static DisplayViewGrid gridViewTest;
    
    private Random random = new Random();

    private int randomX = 0;
    private int randomY = 0;
    private boolean randomHorizontal = false;

    // public Ai(Board board)
    // {
    //     this.board = board;
    // }

    public void setShips()
    {
        while (shipPlacer.getShipsLeft() > 0)
        {
            randomHorizontal = random.nextBoolean();
            randomX = random.nextInt(board.getSize());
            randomY = random.nextInt(board.getSize());
            shipPlacer.setIsHoriazontal(randomHorizontal);
            shipPlacer.placeShips(randomX, randomY);
        }

        System.out.println("OUT OF LOOP");

        board.printBoard();
    }

    public static void main(String[] args)
    {
        Ai testAi = new Ai(); 

        testAi.setShips();

        gridViewTest = new DisplayViewGrid(board);
    }
}