package Ship;

import Board.*;

public class PlaceShips 
{
    private Board board;
    private Ship[] ships;
    
    public String currentShipName = "";
    public int shipsLeft = 0;
    public boolean isHorizontal = false;
    public Ship currentShip;

    public PlaceShips(Board board) 
    {
        this.board = board;
        this.ships = new Ship[6];
        this.ships[0] = new Ship(Ship.ShipType.CARRIER);
        this.ships[1] = new Ship(Ship.ShipType.BATTLESHIP);
        this.ships[2] = new Ship(Ship.ShipType.CRUISER);
        this.ships[3] = new Ship(Ship.ShipType.SUBMARINE);
        this.ships[4] = new Ship(Ship.ShipType.DESTROYER);
        this.ships[5] = new Ship(Ship.ShipType.BOAT);
        this.shipsLeft = ships.length;
        this.currentShipName = ships[ships.length - shipsLeft].getShipName();
        this.currentShip = ships[ships.length - shipsLeft];
    }

    public void placeShips(int x, int y) 
    {
        if (shipsLeft == 0) 
        {
            System.out.println("NO MORE SHIPS LEFT");
            return;
        }

        currentShip = ships[ships.length - shipsLeft];

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

                currentShipName = currentShip.getShipName();
            }
        }

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

    public Ship getCurrentShip()
    {
        return currentShip;
    }
}
