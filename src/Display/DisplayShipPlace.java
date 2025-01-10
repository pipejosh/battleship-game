package Display;


import Board.*;
import Ship.*;
import javax.swing.*;
import java.awt.*;

public class DisplayShipPlace extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color CLICKEDCOLOR = Color.ORANGE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color MISSCOLOR = Color.WHITE;
    private static final Color HITCOLOR = Color.YELLOW;
    private static final Color DESTROYCOLOR = Color.RED;
    private static final Color UNKNOWNCOLOR = Color.BLACK;
    
    public JButton btnChangeOrientation;
    public JLabel lblShipsLeft;
    public JLabel lblCurrentShip;
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
        lblCurrentShip = new JLabel();
        lblOrientation = new JLabel();
        pnlControl.add(btnChangeOrientation);
        pnlControl.add(lblShipsLeft);
        pnlControl.add(lblCurrentShip);
        pnlControl.add(lblOrientation);
        
        setShipsLeftText();
        setCurrentShipText();
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

    public void setCurrentShipText()
    {
        lblCurrentShip.setText("Current Ship: " + (shipPlacer.getShipName()));
    }

    public void buttonsInGridAction(int x, int y)
    {
        shipPlacer.placeShips(x, y);
        System.out.println("BUTTONS ACTION");
        setCurrentShipText();
        setShipsLeftText();
        updateGUI();
    }

    public void buttonChangeOrientationAction()
    {
        shipPlacer.changeOrientation();

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
                    case "CLICKED" -> buttonsInGrid[i][j].setBackground(CLICKEDCOLOR);
                    case "MISS" -> buttonsInGrid[i][j].setBackground(MISSCOLOR);
                    case "HIT" -> buttonsInGrid[i][j].setBackground(HITCOLOR);
                    case "DESTROYED" ->  buttonsInGrid[i][j].setBackground(DESTROYCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }
            } 
        }   
    } 

}

