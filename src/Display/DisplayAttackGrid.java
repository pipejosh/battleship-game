package Display;

import javax.swing.*;

import Board.Board;
import Board.BoardLogic;

import java.awt.*;

public class DisplayAttackGrid extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color MISSCOLOR = Color.YELLOW;
    private static final Color HITCOLOR = Color.ORANGE;
    private static final Color DESTROYCOLOR = Color.RED;
    private static final Color UNKNOWNCOLOR = Color.BLACK;
    
    public JButton[][] buttonsInGrid = new JButton[10][10];
    public String[][] currentBoard;
    public Board board;
    public BoardLogic logic;

    public DisplayAttackGrid(Board board, BoardLogic logic) 
    {
        this.board = board;
        this.currentBoard = board.getBoard();
        initButtons();
        initFrame();
    }
    
    public void initFrame()
    {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
                buttonsInGrid[i][j].setBackground(DEFAULTCOLOR);
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
        System.out.println("BUTTONS ACTION");
        checkAction(x, y);
        updateGUI(x, y);
    }

    public void updateGUI(int x, int y)
    {
        switch (currentBoard[x][y])
        {
            case "DEFAULT" -> buttonsInGrid[x][y].setBackground(DEFAULTCOLOR);
            case "SHIP" -> buttonsInGrid[x][y].setBackground(SHIPCOLOR);
            case "MISS" -> buttonsInGrid[x][y].setBackground(MISSCOLOR);
            case "HIT" -> buttonsInGrid[x][y].setBackground(HITCOLOR);
            case "DESTROYED" ->  buttonsInGrid[x][y].setBackground(DESTROYCOLOR);
            default -> buttonsInGrid[x][y].setBackground(UNKNOWNCOLOR);
        }

        buttonsInGrid[x][y].setEnabled(false);
    } 

    public void checkAction(int x, int y)
    {
        board.attack(x, y);
    }
}

