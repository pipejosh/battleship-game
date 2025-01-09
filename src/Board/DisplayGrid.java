package Board;

import javax.swing.*;
import java.awt.*;

public class DisplayGrid extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color CLICKEDCOLOR = Color.ORANGE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color MISSCOLOR = Color.RED;
    private static final Color HITCOLOR = Color.YELLOW;
    private static final Color UNKNOWNCOLOR = Color.BLACK;

    public JButton[][] buttonsInGrid = new JButton[10][10];
    public int[][] currentBoard;
    public Board board;
    public BoardLogic logic;

    public DisplayGrid(Board board, BoardLogic logic) 
    {
        this.board = board;
        this.currentBoard = board.getBoard();
        this.logic = logic;
        initButtons();
        initFrame();
    }
    
    public void initFrame()
    {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        updateGUI();
    }

    public void initButtons()
    {
        setLayout(new GridLayout(board.getSize(), board.getSize()));

        int counter = 1;

        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                buttonsInGrid[i][j] = new JButton();
                add(buttonsInGrid[i][j]);
                buttonsInGrid[i][j].setName("btn" + counter);
                counter ++;
            }
        }

        System.out.println("BUTTONS CORRECTLY INITIALIZED");

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
                buttonsInGrid[row][col].addActionListener(e -> {buttonsAction(row, col);});
            }
        }
    }

    public void buttonsAction(int x, int y)
    {
        board.shot(x, y);

        updateGUI();
    }

    public void updateGUI()
    {
        for (int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                switch (currentBoard[i][j])
                {
                    case 0 -> buttonsInGrid[i][j].setBackground(DEFAULTCOLOR);
                    case 1 -> buttonsInGrid[i][j].setBackground(SHIPCOLOR);
                    case 2 -> buttonsInGrid[i][j].setBackground(CLICKEDCOLOR);
                    case 3 -> buttonsInGrid[i][j].setBackground(MISSCOLOR);
                    case 4 -> buttonsInGrid[i][j].setBackground(HITCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }
            } 
        }   
    } 

    public void deactivateButtons()
    {
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                buttonsInGrid[i][j].setEnabled(false);     
            } 
        }
    }

    public void activateButtons()
    {
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                buttonsInGrid[i][j].setEnabled(true);     
            } 
        }
    }
}
