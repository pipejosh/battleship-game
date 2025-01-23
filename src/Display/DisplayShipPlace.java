package Display;

// Imports all necesary stuff
import Board.*;
import Ship.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayShipPlace extends JFrame 
{
    // Declare stuff
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color UNKNOWNCOLOR = Color.BLACK;

    private int counter = 0;
    private Runnable onShipsPlacedCallback;
    
    public JButton btnChangeOrientation;
    public JLabel lblShipsLeft;
    public JLabel lblOrientation;
    public JButton[][] buttonsInGrid = new JButton[10][10];
    public String[][] currentBoard;
    public Board board;
    public PlaceShips shipPlacer;

    // Constructor
    public DisplayShipPlace(Board board)
    {
        this.board = board;
        this.currentBoard = board.getBoard();
        this.shipPlacer = new PlaceShips(board);
        initComponents();
        initFrame();
    }
    
    // Creates the Jframe
    public void initFrame()
    {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        updateGUI();
    }

    // Creates all the buttons and the labels and the buttons to change orientation
    public void initComponents() 
    {
        JPanel pnlControl = new JPanel();
        JPanel pnlMain = new JPanel(new BorderLayout());
        
        btnChangeOrientation = new JButton("Change Orientation");
        
        lblShipsLeft = new JLabel();
        lblOrientation = new JLabel();
        pnlControl.add(btnChangeOrientation);
        pnlControl.add(lblShipsLeft);
        pnlControl.add(lblOrientation);
        
        setShipsLeftText();
        setOrientationText();
        
        JPanel pnlBoard = new JPanel(new GridLayout(board.getSize(), board.getSize()));

        int coutner = 0;
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                coutner++;
                buttonsInGrid[i][j] = new JButton();
                buttonsInGrid[i][j].setName("btn" + coutner); 
                pnlBoard.add(buttonsInGrid[i][j]);
            }
        }

        pnlMain.add(pnlControl, BorderLayout.NORTH);
        pnlMain.add(pnlBoard, BorderLayout.CENTER);

        this.getContentPane().add(pnlMain);

        System.out.println("Buttons correctly initialized");

        addButtonsActionListener();
        addButtonsMouseListener();
    }

    // Adds an action listener for every button to do the same except the change orientation one that one has a different action
    public void addButtonsActionListener()
    {
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                final int row = i;
                final int col = j;
                buttonsInGrid[row][col].addActionListener(e -> {buttonsInGridAction(row, col);});
            }
        }

        btnChangeOrientation.addActionListener(e -> {buttonChangeOrientationAction();});
    }

    // Sets how many ships left you have
    public void setShipsLeftText()
    {
        lblShipsLeft.setText("Ships Left: " + shipPlacer.getShipsLeft());
    }

    // Sets the new text of that label
    public void setOrientationText()
    {
        lblOrientation.setText("Current Orientation: " + (shipPlacer.getIsHorizontal() ? "Vertical" : "Horizontal") );
    }

    // The actions of every button
    public void buttonsInGridAction(int x, int y)
    {
        // Ships left place the ship updates the GUI and if is the last ship call the runnable
        int shipsLeft = shipPlacer.getShipsLeft();

        shipPlacer.placeShips(x, y);
        setShipsLeftText();
        updateGUI();

        switch (shipsLeft)
        {
            case 1 -> lblShipsLeft.setText("ALL SHIPS PLACED CLICK ANY BUTTONS TO PLAY");
            case 0 -> finishShipPlacement();
        }
    }

    // Changes the orientation of the ship placement
    public void buttonChangeOrientationAction()
    {
        // If the counter is even is not horizontal and if is not even is horizontal 
        counter ++;

        switch (counter % 2)
        {
            case 1 -> shipPlacer.setIsHorizontal(true);
            case 0 -> shipPlacer.setIsHorizontal(false);
        }

        System.out.println("THE ORIENTATION CHANGED SUCCESFULLY " + shipPlacer.getIsHorizontal());

        setOrientationText();
    }

    // this stuf changes the colors of the butttons of every button
    public void updateGUI()
    {
        for (int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                switch (currentBoard[i][j])
                {
                    // Depends on the value changes different colors
                    case "DEFAULT" -> buttonsInGrid[i][j].setBackground(DEFAULTCOLOR);
                    case "SHIP" -> buttonsInGrid[i][j].setBackground(SHIPCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }
            } 
        }   
    } 

    //add mouse listener
    public void addButtonsMouseListener()
    {
        // For every button in the array
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                final int row = i;
                final int col = j;

                // Add a mouse listener cuz why not
                buttonsInGrid[row][col].addMouseListener(new MouseAdapter() 
                {
                    // Override mouse entered method to custom method in this case previewShipPlaced
                    @Override
                    public void mouseEntered(MouseEvent e) 
                    {
                        previewShipPlaced(row, col, shipPlacer.getIsHorizontal());
                    }

                    // Override mouse exit method to custom in this case updateGUI
                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        updateGUI();
                    }
                });
            }
        }
    }

    //This method previews the ships placed
    public void previewShipPlaced(int x, int y, boolean isHorizontal) {
        
        // Get an instance of the next ship
        Ship nextShip = shipPlacer.getNextShip();

        // If it is null message to the terminal
        if (nextShip == null) 
        {
            System.out.println("NO SHIPS LEFT");
            return;
        }

        // Get the ship size
        int shipSize = nextShip.getShipSize();

        // If it is horizontal
        if (isHorizontal) 
        {
            // Try to preview unless is out of bounds
            if (x + shipSize > board.getSize()) 
            {
                System.out.println("PREVIEW OUT OF BOUNDS");
                return;
            }

            // If it is in bounds but theres a ship
            for (int i = 0; i < shipSize; i++) 
            {
                if (board.getBoardPosition(x + i, y).equals("SHIP")) 
                {
                    System.out.println("ERROR SHIP ALREADY THERE");
                    return;
                }
            }

            // If it is done then just change the color or that buttons
            for (int i = 0; i < shipSize; i++) 
            {
                buttonsInGrid[x + i][y].setBackground(SHIPCOLOR);
            }
        } 

        // If it is not horizontal do the same but on the Y axis
        else 
        {
            if (y + shipSize > board.getSize()) 
            {
                System.out.println("PREVIEW OUT OF BOUNDS");
                return;
            }

            for (int i = 0; i < shipSize; i++) 
            {
                if (board.getBoardPosition(x, y + i).equals("SHIP")) 
                {
                    System.out.println("ERROR SHIP ALREADY THERE");
                    return;
                }
            }

            for (int i = 0; i < shipSize; i++) 
            {
                buttonsInGrid[x][y + i].setBackground(SHIPCOLOR);
            }
        }
    }

    // Creates the callback action
    public void setOnShipsPlacedCallback(Runnable callback)
    {
        this.onShipsPlacedCallback = callback;
    }

    // Disables every button of the array
    public void disabelButtons()
    {
        for (JButton[] jButtons : buttonsInGrid) 
        {
            for (JButton jButton : jButtons) 
            {
                jButton.setEnabled(false);
            }     
        }
    }

    //finishes the ship placement
    public void finishShipPlacement()
    {
        disabelButtons();
        this.dispose();

        if (onShipsPlacedCallback != null)
        {
            onShipsPlacedCallback.run();
        }
    }
}