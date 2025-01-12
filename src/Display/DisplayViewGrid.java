package Display;

import Board.Board;

import javax.swing.*;
import java.awt.*;

public class DisplayViewGrid extends JFrame 
{
    private static final Color DEFAULTCOLOR = Color.BLUE;
    private static final Color SHIPCOLOR = Color.GREEN;
    private static final Color MISSCOLOR = Color.YELLOW;
    private static final Color HITCOLOR = Color.ORANGE;
    private static final Color DESTROYCOLOR = Color.RED;
    private static final Color UNKNOWNCOLOR = Color.BLACK;
    
    public Timer updateTimer;
    public JButton[][] buttonsInGrid = new JButton[10][10];
    public String[][] currentBoard;
    public Board board;

    public DisplayViewGrid(Board board) 
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
        starTimer();
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

        disabelButtons();
        updateGUI();
    }

    public void starTimer()
    {
        updateTimer = new Timer(500, e -> updateGUI());
        updateTimer.start();
    }
    
    public void stopTimer()
    {
        if (updateTimer != null)
        {
            updateTimer.stop();
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
                    case "SHIP" -> buttonsInGrid[i][j].setBackground(SHIPCOLOR);
                    case "MISS" -> buttonsInGrid[i][j].setBackground(MISSCOLOR);
                    case "HIT" -> buttonsInGrid[i][j].setBackground(HITCOLOR);
                    case "DESTROYED" ->  buttonsInGrid[i][j].setBackground(DESTROYCOLOR);
                    default -> buttonsInGrid[i][j].setBackground(UNKNOWNCOLOR);
                }
            } 
        }   
    } 

    public void disabelButtons()
    {
        for (JButton[] jbuttons : buttonsInGrid)
        {
            for (JButton jbutton : jbuttons)
            {
                jbutton.setEnabled(false); 
            } 
        }
    }
}
