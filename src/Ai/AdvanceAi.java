package Ai;

import Board.*;
import Ship.PlaceShips;

import java.util.*;

public class AdvanceAi 
{
    private Board board;
    private Board playerBoard;
    private String[][] playerCurrentBoard;
    private PlaceShips shipPlacer;
    private ArrayList<ArrayList<Integer>> playerShipsCoordinates = new ArrayList<ArrayList<Integer>>();

    private int hitChange = 10;

    private Random random = new Random();
    

    private int randomX = 0;
    private int randomY = 0;
    private boolean randomHorizontal = false;

    public AdvanceAi(Board thisBoard, Board playerBoard)  
    {
        this.board = thisBoard;
        this.playerBoard = playerBoard;
        this.playerCurrentBoard = playerBoard.getBoard();
        this.shipPlacer = new PlaceShips(board);
    }

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

    public void aiAttack()
    {
        determineHitChange();
    }

    public void aiHit() 
    {
        playerShipsCoordinates = playerBoard.getShipCoordinates();
        
        if (playerShipsCoordinates.size() > 0) 
        {
            ArrayList<Integer> selectedShip;

            int x = 0;
            int y = 0;
            
            do
            {
                
                int randomShipIndex = random.nextInt(playerShipsCoordinates.size());
                
                selectedShip = playerShipsCoordinates.get(randomShipIndex);
                
                int randomCoordinateIndex = random.nextInt(selectedShip.size() / 2) * 2; 
                
                x = selectedShip.get(randomCoordinateIndex);
                y = selectedShip.get(randomCoordinateIndex + 1);
                
            }

            while (playerBoard.getBoardPosition(x, y).equals("HIT") && playerBoard.getBoardPosition(x, y).equals("DESTROYED"));
            
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

        do 
        {
            randomX = random.nextInt(board.getSize());
            randomY = random.nextInt(board.getSize());
        }

        while (!playerBoard.getBoardPosition(randomX, randomY).equals("DEFAULT"));

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
            aiHit();
        }
    }
}