package Board;

import javax.swing.*;
import java.awt.*;

public class GameGrid extends JFrame 
{
    public JButton[][] buttonsInGrid = new JButton[10][10];
    Board boardObject = new Board();

    public GameGrid() 
    {
        initFrame();
        initButtons();
        updateButtons();    
    }
    
    public void initFrame()
    {

        setTitle("Battleships Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void initButtons()
    {
        setLayout(new GridLayout(boardObject.getSize(), boardObject.getSize()));

        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                buttonsInGrid[i][j] = new JButton();
                add(buttonsInGrid[i][j]);
            }
        }
    }

    public void updateButtons()
    {
        boardObject.setPosition(0, 0, 1);
        int[][] board = this.boardObject.getBoard();
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                if (board[i][j] == 0) 
                {
                    buttonsInGrid[i][j].setBackground(Color.BLUE);
                } 
                else if (board[i][j] == 1) 
                {
                    buttonsInGrid[i][j].setBackground(Color.RED);
                }
            }
        }
    }
    public static void main(String[] args) 
    {
        new GameGrid();
    }
}
