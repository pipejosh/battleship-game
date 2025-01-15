package Ship;

import java.util.ArrayList;

import Board.*;

public class PlaceShips 
{
    private Board board;
    private Ship[] ships;
    
    public String currentShipName = "";
    public int shipsLeft = 0;
    public boolean isHorizontal = false;
    public Ship currentShip;
    public Ship nextShip;

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
            if (x + currentShip.getShipSize() > board.getSize())
            {
                return;
            }

            for (int i = 0; i < currentShip.getShipSize(); i++) 
            {
                if (board.getBoardPosition(x + i, y).equals("SHIP")) 
                {
                    return;
                }
            }
            
            for (int i = 0; i < currentShip.getShipSize(); i++) 
            {
                board.setShip(x + i, y);

                currentShipCoordinate.add(x + 1);
                currentShipCoordinate.add(y);
                
                currentShipName = currentShip.getShipName();
            }
        } 

        else 
        {
            if (y + currentShip.getShipSize() > board.getSize())
            {
                return;
            }

            for (int i = 0; i < currentShip.getShipSize(); i++) 
            {
                if (board.getBoardPosition(x, y + i).equals("SHIP")) 
                {
                    return;
                }
            }

            for (int i = 0; i < currentShip.getShipSize(); i++) 
            {
                board.setShip(x, y + i);

                currentShipCoordinate.add(x);
                currentShipCoordinate.add(y + i);
                System.out.println();

                currentShipName = currentShip.getShipName();
            }
        }

        board.setCoordinates(currentShipCoordinate);
        shipsLeft--;
    }

    public void setIsHoriazontal(boolean newOrientation)
    {
        this.isHorizontal = newOrientation;
    }

    public boolean getIsHorizontal() 
    {
        return isHorizontal;
    }

    public int getShipsLeft()
    {
        return shipsLeft;
    }

    public String getShipName()
    {
        return currentShipName;
    }

    public Ship getNextShip()
    {
        if (shipsLeft - 1 < 0)
        {
            return null;
        }
        Ship nextShip = ships[shipsLeft - 1];

        return nextShip;
    }
}
