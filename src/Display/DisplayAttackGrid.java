package Display;

import Board.Board;
import Board.BoardLogic;
import java.awt.*;
import javax.swing.*;

public class DisplayAttackGrid extends JFrame 
{
    // Delcare initial stuff
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

    // Constructor
    public DisplayAttackGrid(Board board, BoardLogic logic) 
    {
        this.board = board;
        this.logic = logic;
        this.currentBoard = board.getBoard();
        initButtons();
        initFrame();
    }
    
    // Creates the frame
    public void initFrame()
    {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        initializeButtons();
    }


    // Creates each button and add it to the grid layer
    public void initButtons()
    {
        setLayout(new GridLayout(board.getSize(), board.getSize()));

        int counter = 1;

        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                // Set it on the buttons array + give it a name
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

    // Add the action listener for each button
    public void addButtonsActionListener()
    {
        for (int i = 0; i < board.getSize(); i++) 
        {
            for (int j = 0; j < board.getSize(); j++) 
            {
                final int row = i;
                final int col = j;
                // For each button the same action is gonna happen
                buttonsInGrid[row][col].addActionListener(e -> {playerAction(row, col);});
            }
        }
    }

    // Player actions, attack, sets that button to false changes the turn and updates the gui
    public void playerAction(int x, int y)
    {
        board.attack(x, y);
        buttonsState[x][y] = false;
        logic.setIsPlayerTurn(false);
        updateGUI();
    }

    // Set all the buttons to a new state
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
    
    // For every button it checkes the postion on the array and changes the color in base of that
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

            }
        }
    } 

    // Updates if the button is enable or not
    public void updateButtons()
    {
        for (int i = 0; i < buttonsState.length; i++)
        {
            for (int j = 0; j < buttonsState[i].length; j++)
            {
                boolean currentButtonState = buttonsState[i][j];
                buttonsInGrid[i][j].setEnabled(currentButtonState);     
            } 
        }
    }

    // Set all the buttons to true
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