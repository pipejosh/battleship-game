package Board;

// Import all necesary stuff
import java.util.ArrayList;

public class Board 
{
    // Declare initial stuff
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

    // Constructor
    public Board() 
    {
        this.size = 10;
        this.board = new String[size][size];
        initializeBoard("DEFAULT");
    }
    
    // Initizlize every square on the board to have "DEFAULT"
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


    // This method handles the attack with 2 parameters x and y
    public void attack(int x, int y) 
    {
        // If the position x, y is ship and is in bounds
        if (board[x][y].equals("SHIP") && isInBounds(x, y)) 
        {
            // Change that postion to hit and check if the ship is destroyed
            setHit(x, y);

            checkDestoyedShips();
        } 

        // If is not equal to ship we mark it as miss
        else 
        {
            setMiss(x, y);
        }
    }



    public void checkDestoyedShips()
    {
        // For every coordinate on the arraylist
        for (int i = 0; i < shipsCoordinatesOnBoard.size(); i++)
        {
            // For every ship coordinate on the arrylist of the total coordinates
            ArrayList<Integer> shipCoordinates = shipsCoordinatesOnBoard.get(i);
            
            int hitsCounter = 0;

            // For every pair of coordinates on the ship coordinates 
            for (int j = 0; j < shipCoordinates.size(); j+=2)
            {
                // Get x and y coordinate
                int x = shipCoordinates.get(j); 
                int y = shipCoordinates.get(j + 1);

                // If board postion x y is hit increment hit couner
                if (getBoardPosition(x, y).equals("HIT"))
                {
                    hitsCounter ++;
                }
            }

            // If the hit counter equal the lenght position of that ship
            if (hitsCounter == shipCoordinates.size() / 2)
            {
                // It destoyes that ship
                System.out.println("THE SHIPS ARE GETTING DESTOYED");
                for (int k = 0; k < shipCoordinates.size(); k +=2)
                {
                    int x = shipCoordinates.get(k);
                    int y = shipCoordinates.get(k + 1);

                    setDestroyed(x, y);
                }
                
                // Removes the ships left 
                shipsLeft --;
            }
        }
    }

    // Check if the ships left are less or equal than 0 it means someone wons
    public boolean hasWon()
    {
        if (shipsLeft <= 0)
        {
            return true;
        }

        return false;
    }


    // return the size of the board
    public int getSize() 
    {
        return size;
    }

    // returns a certain postion x, y of the board
    public String getBoardPosition(int x, int y)
    {
        return board[x][y];
    }

    // returns the entire board
    public String[][] getBoard() 
    {
        return board;
    }

    // returns all the ship coordinates
    public ArrayList<ArrayList<Integer>> getShipCoordinates()
    {
        return shipsCoordinatesOnBoard;
    }

    // Set a value on the position x, y to new value
    public void setBoardPosition(int x, int y, String value) 
    {
        // If is in bounds
        if (isInBounds(x, y)) 
        {
            board[x][y] = value;
        }
    }

    // Checks if a position x, y is in bounds
    public boolean isInBounds(int x, int y) 
    {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // Set a postition x, y to "SHIP"
    public void setShip(int x, int y)
    {
        setBoardPosition(x, y, "SHIP");
    }

    // Set a postition x, y to "HIT"
    public void setHit(int x, int y)
    {
        setBoardPosition(x, y, "HIT");
    }

    // Set a postition x, y to "MISS"
    public void setMiss(int x, int y)
    {
        setBoardPosition(x, y, "MISS");
    }

    // Set a postition x, y to "DESTOYED"
    public void setDestroyed(int x, int y)
    {
        setBoardPosition(x, y, "DESTROYED");
    }

    // Add the arraylist of coordinates to the original coordinates
    public void setCoordinates(ArrayList<Integer> shipCoordinates)
    {
        shipsCoordinatesOnBoard.add(shipCoordinates);
    }

    // Prints the board
    public void printBoard()
    {
        //this prints out the grid of the board
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