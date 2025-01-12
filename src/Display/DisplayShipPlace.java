package Display;


import Board.*;
import Ship.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplayShipPlace extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color UNKNOWNCOLOR = Color.BLACK;
    private int counter = 0;
    
    public JButton btnChangeOrientation;
    public JLabel lblShipsLeft;
    public JLabel lblOrientation;
    public JButton[][] buttonsInGrid = new JButton[10][10];
    public String[][] currentBoard;
    public Board board;
    public PlaceShips shipPlacer;

    public DisplayShipPlace(Board board, BoardLogic logic) 
    {
        this.board = board;
        this.currentBoard = board.getBoard();
        this.shipPlacer = new PlaceShips(board);
        initComponents();
        initFrame();
    }
    
    public void initFrame()
    {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        updateGUI();
    }

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

    public void setShipsLeftText()
    {
        lblShipsLeft.setText("Ships Left: " + shipPlacer.getShipsLeft());
    }

    public void setOrientationText()
    {
        lblOrientation.setText("Current Orientation: " + (shipPlacer.getIsHorizontal() ? "Vertical" : "Horizontal") );
    }

    public void buttonsInGridAction(int x, int y)
    {
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

    public void buttonChangeOrientationAction()
    {
        counter ++;

        switch (counter % 2)
        {
            case 1 -> shipPlacer.setIsHoriazontal(true);
            case 0 -> shipPlacer.setIsHoriazontal(false);
        }

        System.out.println("SE CAMBIO LA HORINETACION " + shipPlacer.getIsHorizontal());

        setOrientationText();
    }

    public void updateGUI()
    {
        for (int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                switch (currentBoard[i][j])
                {
                    case "DEFAULT" -> buttonsInGrid[i][j].setBackground(DEFAULTCOLOR);
                    case "SHIP" -> buttonsInGrid[i][j].setBackground(SHIPCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }
            } 
        }   
    } 

    public void addButtonsMouseListener()
    {
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                final int row = i;
                final int col = j;
                buttonsInGrid[row][col].addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseEntered(MouseEvent e) 
                    {
                        previewShipPlaced(row, col, shipPlacer.getIsHorizontal());
                    }

                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                        updateGUI();
                    }
                });
            }
        }

    }

    public void previewShipPlaced(int x, int y, boolean isHorizontal) {
        
        Ship nextShip = shipPlacer.getNextShip();
        if (nextShip == null) 
        {
            System.out.println("No current ship to preview.");
            return;
        }

        int shipSize = nextShip.getShipSize();

        if (isHorizontal) {
            if (x + shipSize > board.getSize()) 
            {
                System.out.println("Preview out of bounds horizontally.");
                return;
            }

            for (int i = 0; i < shipSize; i++) 
            {
                if (board.getBoardPosition(x + i, y).equals("SHIP")) 
                {
                    System.out.println("Cannot preview here: collision detected.");
                    return;
                }
            }

            for (int i = 0; i < shipSize; i++) 
            {
                buttonsInGrid[x + i][y].setBackground(SHIPCOLOR);
            }
        } 

        else 
        {
            if (y + shipSize > board.getSize()) 
            {
                System.out.println("Preview out of bounds vertically.");
                return;
            }

            for (int i = 0; i < shipSize; i++) 
            {
                if (board.getBoardPosition(x, y + i).equals("SHIP")) 
                {
                    System.out.println("Cannot preview here: collision detected.");
                    return;
                }
            }

            for (int i = 0; i < shipSize; i++) 
            {
                buttonsInGrid[x][y + i].setBackground(SHIPCOLOR);
            }
        }
    }

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

    public void finishShipPlacement()
    {
        disabelButtons();
        this.dispose();
    }
}

