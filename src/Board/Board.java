package Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Board 
{
    private int size;
    private String[][] board; 
    private ArrayList<ArrayList<Integer>> shipsCoordinatesOnBoard = new ArrayList<ArrayList<Integer>>();
    public int shipsLeft = 6;

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


    public void attack(int x, int y) 
    {
        if (board[x][y].equals("SHIP") && isInBounds(x, y)) 
        {
            setHit(x, y);

            checkDestoyedShips();
        } 

        else 
        {
            setMiss(x, y);
        }
    }

    public void checkDestoyedShips()
    {
        for (int i = 0; i < shipsCoordinatesOnBoard.size(); i++)
        {
            ArrayList<Integer> shipCoordinates = shipsCoordinatesOnBoard.get(i);
            
            int hitsCounter = 0;

            for (int j = 0; j < shipCoordinates.size(); j+=2)
            {
                int x = shipCoordinates.get(j); 
                int y = shipCoordinates.get(j + 1);

                if (getBoardPosition(x, y).equals("HIT"))
                {
                    hitsCounter ++;
                }
            }

            if (hitsCounter == shipCoordinates.size() / 2)
            {
                for (int k = 0; k < shipCoordinates.size(); k +=2)
                {
                    System.out.println("SE ESTA DESTRUYENDO BARQUITOS");
                    int x = shipCoordinates.get(k);
                    int y = shipCoordinates.get(k + 1);

                    setDestroyed(x, y);
                }

                shipsLeft --;
            }
        }
    }


    public boolean hasWon()
    {
        if (shipsLeft <= 0)
        {
            return true;
        }

        return false;
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

    public ArrayList<ArrayList<Integer>> getShipCoordinates()
    {
        return shipsCoordinatesOnBoard;
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

    public void printBoard()
    {
        for (int i = 0; i < board.length; i++) 
        {
            for (int j = 0; j < board.length; j++)
            {
                System.out.print(board[i][j] + " ");
            }

            System.out.println();
        }
    }
}