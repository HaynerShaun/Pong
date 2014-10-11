import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Pong extends JFrame implements KeyListener, ActionListener
{
	private JPanel actionArea = new JPanel();
	private JPanel gameArea = new JPanel();
	private JButton newGame = new JButton("New game");
	private final int FRAMEWIDTH = 500;
	private final int FRAMEHEIGHT = 500;
	private int paddle1x;
	private int paddle1y;
	private int paddle2x;
	private int paddle2y;
	private JPanel paddle1 = new JPanel();
	private JPanel paddle2 = new JPanel();
	private JPanel ball = new JPanel();
	private int player1score = 0;
	private int player2score = 0;
	private JLabel playerOneLabel = new JLabel();
	private JLabel playerOneScore = new JLabel();
	private JLabel playerTwoLabel = new JLabel();
	private JLabel playerTwoScore = new JLabel();
	private Dimension size;
	
	public void start()
	{
		super.setLayout(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		actionArea.setLocation(0,0);
		actionArea.setSize(FRAMEWIDTH,50);
		actionArea.setLayout(null);

		gameArea.setLocation(0,actionArea.getHeight());
		gameArea.setSize(FRAMEWIDTH,FRAMEHEIGHT - actionArea.getHeight());
		gameArea.setLayout(null);
		
		playerOneLabel.setText("Player 1 - ");
		playerOneScore.setText(player1score + "");
		playerTwoLabel.setText("Player 2 - ");
		playerTwoScore.setText(player2score + "");

		paddle1.setBackground(Color.black);
		paddle2.setBackground(Color.black);
		paddle1.addKeyListener(this);
		paddle2.addKeyListener(this);
		
		paddle1x = 40;
		paddle1y = gameArea.getHeight() / 2 - 20;
		paddle2x = FRAMEWIDTH - 50;
		paddle2y = gameArea.getHeight() / 2 - 20;
        
		setLocations();

		actionArea.add(newGame);
		actionArea.add(playerOneLabel);
		actionArea.add(playerOneScore);
		actionArea.add(playerTwoLabel);
		actionArea.add(playerTwoScore);
		
		gameArea.add(paddle1);
		gameArea.add(paddle2);
		gameArea.add(ball);
		
		super.add(actionArea);
		super.add(gameArea);
		
		super.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		super.setVisible(true);
		super.setResizable(false);
	}
	
	private void setLocations()
	{
		newGame.setLocation(10,10);
        
		size = playerOneLabel.getPreferredSize();
		playerOneLabel.setBounds(FRAMEWIDTH - 100,5, size.width, size.height);
		size = playerOneScore.getPreferredSize();
		playerOneScore.setBounds(FRAMEWIDTH - 100 + playerOneLabel.getWidth(),5, size.width, size.height);
		size = playerTwoLabel.getPreferredSize();
		playerTwoLabel.setBounds(FRAMEWIDTH - 100,25, size.width, size.height);
		size = playerTwoScore.getPreferredSize();
		playerTwoScore.setBounds(FRAMEWIDTH - 100 + playerTwoLabel.getWidth(),25, size.width, size.height);
		size = newGame.getPreferredSize();
		newGame.setBounds(10,10, size.width, size.height);

		paddle1.setBounds(paddle1x,paddle1y, 10, 40);
		paddle2.setBounds(paddle2x,paddle2y, 10, 40);
        
        ball.setBackground(Color.black);
        ball.setLocation(FRAMEWIDTH / 2, gameArea.getHeight() / 2);
        ball.setSize(20,20);
	}
	
	/** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) 
    {
    	if(e.getKeyCode() == 38)
        {
        	System.out.println("up arrow pressed");
        	paddle2y--;
        	repaint();
        }
    }
    
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) 
    {
    	if(e.getKeyCode() == 38)
    	{
    		System.out.println("up arrow pressed");
    		paddle2y--;
    		repaint();
    	}
    }
    
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) 
    {
    	if(e.getKeyCode() == 38)
    	{
    		System.out.println("up arrow pressed");
    		paddle2y--;
    		repaint();
    	}
    }
    
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
