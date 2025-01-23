package Ship;

// Imports all necesary stuff
import java.util.ArrayList;

import Board.*;

public class PlaceShips 
{
    // Declares initial stuff
    private Board board;
    private Ship[] ships;
    
    public String currentShipName = "";
    public int shipsLeft = 0;
    public boolean isHorizontal = false;
    public Ship currentShip;
    public Ship nextShip;

    // Constructor
    public PlaceShips(Board board) 
    {
        this.board = board;
        this.ships = new Ship[6];
        this.ships[0] = new Ship(Ship.ShipType.BOAT);
        this.ships[1] = new Ship(Ship.ShipType.DESTROYER);
        this.ships[2] = new Ship(Ship.ShipType.SUBMARINE);
        this.ships[3] = new Ship(Ship.ShipType.CRUISER);
        this.ships[4] = new Ship(Ship.ShipType.BATTLESHIP);
        this.ships[5] = new Ship(Ship.ShipType.CARRIER);
        this.shipsLeft = ships.length;
        this.currentShipName = ships[ships.length - shipsLeft].getShipName();
    }

    // The method that actually places the ships
    public void placeShips(int x, int y) 
    {
        // Ships coordinates        
        ArrayList<Integer> currentShipCoordinate = new ArrayList<Integer>();

        // Guard claus 
        if (shipsLeft == 0) 
        {
            System.out.println("NO MORE SHIPS LEFT");
            return;
        }

        currentShip = ships[shipsLeft - 1];

        // Horizontal or not
        if (isHorizontal)
        {
            // If can be placed 
            if (canPlaceShip(x, y))
            {
                // Place the ship
                for (int i = 0; i < currentShip.getShipSize(); i++) 
                {
                    board.setShip(x + i, y);
    
                    // Add it to the coordinates
                    currentShipCoordinate.add(x + i);
                    currentShipCoordinate.add(y);
                    
                    currentShipName = currentShip.getShipName();
                }
            }

            // If cant be place return nothing
            else
            {
                return;
            }
            
        } 

        // If is not horizontal do the same but in the Y axis
        else 
        {

            if (canPlaceShip(x, y))
            {
                for (int i = 0; i < currentShip.getShipSize(); i++) 
                {
                    board.setShip(x, y + i);
    
                    currentShipCoordinate.add(x);
                    currentShipCoordinate.add(y + i);
    
                    currentShipName = currentShip.getShipName();
                }
            }

            else
            {
                return;
            }
        }

        // Set the coordinates and rest the ships left

        board.setCoordinates(currentShipCoordinate);
        shipsLeft--;
    }

    // This method returns true or false if a ship can be placed on (x, y)
    public boolean canPlaceShip(int x, int y)
    {
        // If is horizontral
        if (isHorizontal)
        {
            // If the ship in the X axis is bigger return false
            if (x + currentShip.getShipSize() > board.getSize())
            {
                return false;
            }

            // Place the ships on the x axis
            for (int i = 0; i < currentShip.getShipSize(); i++)
            {
                if (board.getBoardPosition(x + i, y).equals("SHIP"))
                {
                    return false;
                }
            }
        }

        else 
        {
            // If the ship in the Y axis is bigger return false
            if (y + currentShip.getShipSize() > board.getSize())
            {
                return false;
            }

            // Place the ships on the y axis
            for (int i = 0; i < currentShip.getShipSize(); i++)
            {
                if (board.getBoardPosition(x, y + i).equals("SHIP"))
                {
                    return false;
                }
            }
        }

        // If reach this part means it can be placed
        return true;
    }

    // Sets is horizontal to a new value
    public void setIsHorizontal(boolean newOrientation)
    {
        this.isHorizontal = newOrientation;
    }

    // Gets is horizontal
    public boolean getIsHorizontal() 
    {
        return isHorizontal;
    }

    // Gets the ships left
    public int getShipsLeft()
    {
        return shipsLeft;
    }

    // Gets the ship name
    public String getShipName()
    {
        return currentShipName;
    }

    // Gets the next ship
    public Ship getNextShip()
    {
        if (shipsLeft - 1 < 0)
        {
            return null;
        }

        // For the array return the ships that are left - 1
        Ship nextShip = ships[shipsLeft - 1];

        return nextShip;
    }
}
