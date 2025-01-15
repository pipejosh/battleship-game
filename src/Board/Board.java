package Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Board 
{
    private int size;
    private String[][] board; 
    private ArrayList<ArrayList<Integer>> shipsCoordinatesOnBoard = new ArrayList<ArrayList<Integer>>();
    private int shipsLeft = 6;

    /*  BOARD ARRAY CAN CONTAIN 5 VALUES; 
        DEFAULT: empty space
        SHIP: ship space
        MISS: shot miss
        HIT: ship hit
        DESTROYED: ship destroyed */

    public Board() 
    {
        this.size = 10;
        this.board = new String[size][size];
        initializeBoard("DEFAULT");
    }
    
    private void initializeBoard(String defaultValue) 
    {
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                board[i][j] = defaultValue;
            }
        }
    } 


    public boolean attack(int x, int y) 
    {
        if (board[x][y].equals("SHIP") && isInBounds(x, y)) 
        {
            setHit(x, y);
            ArrayList<int[]> shipCoordinates = getShipCoordinates(x, y);

            if (isShipDestroyed(shipCoordinates))
            {
                updateShipToDestroyed(shipCoordinates);
            }
            return true; 
        } 

        else 
        {
            setMiss(x, y);
            return false;
        }
    }

    public boolean isShipDestroyed(ArrayList<int[]> shipCoordinates)
    {
        for (int[] coord : shipCoordinates)
        {
            int x = coord[0];
            int y = coord[1];
            if (!board[x][y].equals("HIT"))
            {
                return false;
            }
        }

        return true;
    }

    public void updateShipToDestroyed(ArrayList<int[]> shipCoordinates)
    {
        for (int[] coord : shipCoordinates)
        {
            int x = coord[0];
            int y = coord[1];
            setBoardPosition(x, y, "DESTROYED");

        }
        shipsLeft --;
        checkWin();
    }

    public void checkWin()
    {
        if (shipsLeft <= 0)
        {
            System.out.println("WIN");
            return; 
        }
        System.out.println("NO WIN");

    }

    public ArrayList<int[]> getShipCoordinates(int x, int y)
    {
        ArrayList<int[]> shipCoordinates = new ArrayList<>();
        for (ArrayList<Integer> ship : shipsCoordinatesOnBoard)
        {
            for (int i = 0; i < ship.size(); i += 2)
            {
                if (ship.get(i) == x && ship.get(i + 1) == y)
                {
                    for (int j = 0; j < ship.size(); j += 2)
                    {
                        shipCoordinates.add(new int[]{ship.get(j), ship.get(j + 1)});
                    }
                    return shipCoordinates;
                }
            }
        }
        return shipCoordinates;
    }
    public int getSize() 
    {
        return size;
    }

    public String getBoardPosition(int x, int y)
    {
        return board[x][y];
    }

    public String[][] getBoard() 
    {
        return board;
    }

    public void setBoardPosition(int x, int y, String value) 
    {
        if (isInBounds(x, y)) 
        {
            board[x][y] = value;
        }
    }

    public boolean isInBounds(int x, int y) 
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public void setShip(int x, int y)
    {
        setBoardPosition(x, y, "SHIP");
    }

    public void setHit(int x, int y)
    {
        setBoardPosition(x, y, "HIT");
    }

    public void setMiss(int x, int y)
    {
        setBoardPosition(x, y, "MISS");
    }

    public void setDestroyed(int x, int y)
    {
        setBoardPosition(x, y, "DESTROYED");
    }

    public void setCoordinates(ArrayList<Integer> shipCoordinates)
    {
        shipsCoordinatesOnBoard.add(shipCoordinates);
    }
}