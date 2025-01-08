package Board;

import javax.swing.*;
import java.awt.*;

public class DisplayGrid extends JFrame 
{
    public JButton[][] buttonsInGrid = new JButton[10][10];
    int[][] currentBoard;
    Board board;
    BoardLogic logic = new BoardLogic();

    public DisplayGrid(Board board) 
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
                buttonsInGrid[row][col].addActionListener(e -> {logic.buttonAction(row, col); updateGUI();});
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
                    case 0 -> buttonsInGrid[i][j].setBackground(Color.BLUE);
                    case 1 -> buttonsInGrid[i][j].setBackground(Color.GREEN);
                    case 2 -> buttonsInGrid[i][j].setBackground(Color.ORANGE);
                    case 3 -> buttonsInGrid[i][j].setBackground(Color.RED);
                    case 4 -> buttonsInGrid[i][j].setBackground(Color.YELLOW);
                    default -> buttonsInGrid[i][j].setBackground(Color.BLACK);
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
