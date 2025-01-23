package Ai;

// Imports all necesary stuff
import Board.*;
import Ship.PlaceShips;

import java.util.*;

public class AdvanceAi 
{
    // Declare initial stuff
    private Board board;
    private Board playerBoard;
    private PlaceShips shipPlacer;
    private ArrayList<ArrayList<Integer>> playerShipsCoordinates = new ArrayList<ArrayList<Integer>>();

    private int hitChange = 5;

    private Random random = new Random();
    
    private int randomX = 0;
    private int randomY = 0;
    private boolean randomHorizontal = false;

    // Constructor
    public AdvanceAi(Board thisBoard, Board playerBoard)  
    {
        this.board = thisBoard;
        this.playerBoard = playerBoard;
        this.shipPlacer = new PlaceShips(board);
    }

    // Set ships while they arent already placed
    public void aiSetShips()
    {
        while (shipPlacer.getShipsLeft() > 0)
        {
            randomHorizontal = random.nextBoolean();
            randomX = random.nextInt(board.getSize());
            randomY = random.nextInt(board.getSize());

            shipPlacer.setIsHorizontal(randomHorizontal);
            shipPlacer.placeShips(randomX, randomY);
        }
    }

    // Attack
    public void aiAttack()
    {
        determineHitChange();
    }

    // Determines hit 
    public void aiHit() 
    {
        playerShipsCoordinates = playerBoard.getShipCoordinates();
        
        // Gets all the positions of the ships
        if (playerBoard.shipsLeft > 0) 
        {
            ArrayList<Integer> selectedShip;

            int x = 0;
            int y = 0;
            
            // Select a random postition
            do
            {
                int randomShipIndex = random.nextInt(playerShipsCoordinates.size());
                
                selectedShip = playerShipsCoordinates.get(randomShipIndex);
                
                int randomCoordinateIndex = random.nextInt(selectedShip.size() / 2) * 2; 
                
                x = selectedShip.get(randomCoordinateIndex);
                y = selectedShip.get(randomCoordinateIndex + 1);
                
            }

            // If the position is hit or destroyed it tries again
            while (playerBoard.getBoardPosition(x, y).equals("HIT") || playerBoard.getBoardPosition(x, y).equals("DESTROYED"));

            // Attacks 
            playerBoard.attack(x, y);
            
            System.out.println("AI HIT COORDINATE: (" + x + ", " + y + ")");

            hitChange++;
        }

        else 
        {
            System.out.println("NO MORE SHIPS TO HIT");
        }
    }
    
    public void aiMiss()
    {

        // Miss just get random coordinates and if they arent default they re run it
        do 
        {
            randomX = random.nextInt(board.getSize());
            randomY = random.nextInt(board.getSize());
        }

        while (!playerBoard.getBoardPosition(randomX, randomY).equals("DEFAULT"));

        playerBoard.attack(randomX, randomY);

        System.out.println("AI MISSED AT: (" + randomX + ", " + randomY + ")"); 

        hitChange -= 2;
    }


    public void determineHitChange() 
    {
        // If the magic random number is less or equal to the magic number it attacks if not it miss
        int magicNumber = 1;

        if (hitChange <= 0)
        {
            hitChange += 7;
        }

        int randomHitChange = random.nextInt(hitChange) + 1;

        if (randomHitChange <= magicNumber)
        {
            aiHit();
        }

        else
        {
            aiMiss();
        }
    }
}