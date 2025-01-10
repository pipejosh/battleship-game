package Ship;

import Board.*;

public class PlaceShips 
{
    private Board board;
    private Ship[] ships;
    
    public String currenShipName = "";
    public int shipsLeft = 0;
    public boolean isHorizontal = false;

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
        this.currenShipName = ships[ships.length - shipsLeft].getShipName();
    }

    public void placeShips(int x, int y) 
    {
        if (shipsLeft == 0) 
        {
            System.out.println("NO MORE SHIPS LEFT");
            return;
        }

        Ship currentShip = ships[ships.length - shipsLeft];

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

                currenShipName = currentShip.getShipName();
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

                currenShipName = currentShip.getShipName();
            }
        }

        shipsLeft--;

    }

    public boolean changeOrientation() 
    {
        isHorizontal = !isHorizontal;
        return isHorizontal;
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
        return currenShipName;
    }
}
