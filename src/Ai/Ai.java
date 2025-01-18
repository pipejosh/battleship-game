package Ai;

import Board.*;
import Ship.PlaceShips;
import Display.*;

import java.util.*;

public class Ai 
{
    private static Board board;
    private static Board playerBoard;
    private PlaceShips shipPlacer = new PlaceShips(board);
    private ArrayList<ArrayList<Integer>> playerShipsCoordinates = new ArrayList<ArrayList<Integer>>();

    private int hitChange = 10;

    private Random random = new Random();
    

    private int randomX = 0;
    private int randomY = 0;
    private boolean randomHorizontal = false;

    public Ai(Board board, Board playeBoard)  
    {
        this.board = board;
        this.playerBoard = playeBoard;
    }

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

    public static void main(String[] args)
    {
        Ai testAi = new Ai(board, playerBoard); 

        testAi.setShips();

        gridViewTest = new DisplayViewGrid(board);
    }
}