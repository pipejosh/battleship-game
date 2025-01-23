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

    public void placeShips(int x, int y) 
    {
        ArrayList<Integer> currentShipCoordinate = new ArrayList<Integer>();

        if (shipsLeft == 0) 
        {
            System.out.println("NO MORE SHIPS LEFT");
            return;
        }

        currentShip = ships[shipsLeft - 1];

        if (isHorizontal)
        {
            if (canPlaceShip(x, y))
            {

                for (int i = 0; i < currentShip.getShipSize(); i++) 
                {
                    board.setShip(x + i, y);
    
                    currentShipCoordinate.add(x + i);
                    currentShipCoordinate.add(y);
                    
                    currentShipName = currentShip.getShipName();
                }
            }

            else
            {
                return;
            }
            
        } 

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

        board.setCoordinates(currentShipCoordinate);
        shipsLeft--;
    }

    public boolean canPlaceShip(int x, int y)
    {
        if (isHorizontal)
        {
            if (x + currentShip.getShipSize() > board.getSize())
            {
                return false;
            }

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
            if (y + currentShip.getShipSize() > board.getSize())
            {
                return false;
            }

            for (int i = 0; i < currentShip.getShipSize(); i++)
            {
                if (board.getBoardPosition(x, y + i).equals("SHIP"))
                {
                    return false;
                }
            }
        }
        return true;
    }

    Sets is horizontal to a new value
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
