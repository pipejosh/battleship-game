package Ai;

import Board.*;
import Ship.PlaceShips;

import java.util.*;

public class AdvanceAi 
{
    private static Board board;
    private static Board playerBoard;
    private PlaceShips shipPlacer;
    private ArrayList<ArrayList<Integer>> playerShipsCoordinates = new ArrayList<ArrayList<Integer>>();

    private int hitChange = 10;

    private Random random = new Random();
    

    private int randomX = 0;
    private int randomY = 0;
    private boolean randomHorizontal = false;

    public AdvanceAi(Board thisBoard, Board thisPlayeBoard)  
    {
        this.board = thisBoard;
        this.playerBoard = thisPlayeBoard;
        this.shipPlacer = new PlaceShips(thisBoard);

    }

    public void aiSetShips()
    {
        while (shipPlacer.getShipsLeft() > 0)
        {
            randomHorizontal = random.nextBoolean();
            randomX = random.nextInt(board.getSize());
            randomY = random.nextInt(board.getSize());
            shipPlacer.setIsHoriazontal(randomHorizontal);
            shipPlacer.placeShips(randomX, randomY);
        }
    }

    public void aiAttack()
    {
        determineHitChange();
    }

    public void aiHit() 
    {
        playerShipsCoordinates = playerBoard.getAllShipsCoordinates();
        
        if (playerShipsCoordinates.size() > 0) 
        {
            
            int randomShipIndex = random.nextInt(playerShipsCoordinates.size());
            
            ArrayList<Integer> selectedShip = playerShipsCoordinates.get(randomShipIndex);
            
            int randomCoordinateIndex = random.nextInt(selectedShip.size() / 2) * 2; 
            
            int x = selectedShip.get(randomCoordinateIndex);
            int y = selectedShip.get(randomCoordinateIndex + 1);
            
            System.out.println("AI HIT COORDINATE: (" + x + ", " + y + ")");

            playerBoard.attack(x, y);
            
            selectedShip.remove(randomCoordinateIndex);
            selectedShip.remove(randomCoordinateIndex);  
            
            if (selectedShip.isEmpty()) 
            {
                playerShipsCoordinates.remove(randomShipIndex);
            }

            hitChange++;
        }

        else 
        {
            System.out.println("NO MORE SHIPS TO HIT");
        }
    }
    
    public void aiMiss()
    {
        randomX = random.nextInt(board.getSize());
        randomY = random.nextInt(board.getSize());

        playerBoard.attack(randomX, randomY);

        System.out.println("AI MISSED AT: (" + randomX + ", " + randomY + ")"); 

        hitChange --;
    }


    public void determineHitChange() 
    {
        int magicNumber = 1;
        int randomHitChange = random.nextInt(hitChange) + 1;

        if (randomHitChange == magicNumber)
        {
            aiHit();
        }

        else
        {
            aiMiss();
        }
    }
}