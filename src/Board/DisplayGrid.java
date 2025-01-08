package Board;

import javax.swing.*;
import java.awt.*;

public class DisplayGrid extends JFrame 
{
    public JButton[][] buttonsInGrid = new JButton[10][10];
    public boolean buttonClicked = false;
    Board board = new Board();
    BoardLogic logic = new BoardLogic();

    public DisplayGrid() 
    {
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
                System.out.println(buttonsInGrid[i][j].getName());
                counter ++;
            }
        }

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
                buttonsInGrid[row][col].addActionListener(e -> 
                {
                    buttonAction(row, col);
                });
            }
        }
    }

    public void updateGUI()
    {
        int[][] currentBoard = board.getPlayerBoard();

        for (int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                switch (currentBoard[i][j])
                {
                    case 1 -> buttonsInGrid[i][j].setBackground(Color.ORANGE);
                    case 2 -> buttonsInGrid[i][j].setBackground(Color.GREEN);
                    default -> buttonsInGrid[i][j].setBackground(Color.BLACK);
                }
            } 
        }
    }

    public void buttonAction(int x, int y)
    {
        logic.addTurns();
        board.setPlayerBoardPosition(x, y, 1);
        buttonsInGrid[x][y].setEnabled(false);
        updateGUI();
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
