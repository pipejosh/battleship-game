package Display;

import javax.swing.*;
import java.awt.*;

import Board.Board;
import Board.BoardLogic;

public class DisplayAttackGrid extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color MISSCOLOR = new Color(0, 0, 100);
    private static final Color HITCOLOR = Color.ORANGE;
    private static final Color DESTROYCOLOR = Color.RED;
    private static final Color UNKNOWNCOLOR = Color.BLACK;
    
    public boolean isPlayerTurn = true;

    public JButton[][] buttonsInGrid = new JButton[10][10];
    public String[][] currentBoard;
    public boolean[][] buttonsState = new boolean[10][10]; 
    public Board board;
    public BoardLogic logic;

    public DisplayAttackGrid(Board board, BoardLogic logic) 
    {
        this.board = board;
        this.logic = logic;
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
        initializeButtons();
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
                buttonsInGrid[row][col].addActionListener(e -> {playerAction(row, col);});
            }
        }
    }

    public void playerAction(int x, int y)
    {
        blackButtons();
        board.attack(x, y);
        buttonsState[x][y] = false;
        logic.setIsPlayerTurn(false);
        updateGUI();
        
    }

    public void setButtons(boolean state)
    {
        for (int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                buttonsInGrid[i][j].setEnabled(state);
            }
        }
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
                    case "SHIP" -> buttonsInGrid[i][j].setBackground(DEFAULTCOLOR);
                    case "MISS" -> buttonsInGrid[i][j].setBackground(MISSCOLOR);
                    case "HIT" -> buttonsInGrid[i][j].setBackground(HITCOLOR);
                    case "DESTROYED" ->  buttonsInGrid[i][j].setBackground(DESTROYCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }

                buttonsInGrid[i][j].setEnabled(buttonsState[i][j]);
            }
        }
    } 

    public void blackButtons()
    {
        for (JButton[] jButtons : buttonsInGrid) 
        {
            for (JButton jButton : jButtons) 
            {
                jButton.setBackground(UNKNOWNCOLOR);     
            }     
        }
    }

    public void initializeButtons()
    {
        for (int i = 0; i < buttonsInGrid.length; i++) 
        {
            for (int j = 0; j < buttonsInGrid.length; j++) 
            {
                buttonsState[i][j] = true;     
            }     
        }
    }
}