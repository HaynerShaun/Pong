import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Pong implements KeyListener
{
	private JFrame window = new JFrame();
	private JPanel actionArea = new JPanel();
	private JPanel gameArea = new JPanel();
	private JButton newGame = new JButton("New game");
	private final int FRAMEWIDTH = 500;
	private final int FRAMEHEIGHT = 500;
	private final int JUMP = 5;
	private final int DELAY = 20;
	private Ball ball;
	private int player1score = 0;
	private int player2score = 0;
	private JLabel playerOneLabel = new JLabel();
	private JLabel playerOneScore = new JLabel();
	private JLabel playerTwoLabel = new JLabel();
	private JLabel playerTwoScore = new JLabel();
	private Dimension size;
	private boolean running;
	
	public void start()
	{
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(this);
		
		actionArea.setLocation(0,0);
		actionArea.setSize(FRAMEWIDTH,50);
		actionArea.setLayout(null);
		actionArea.setBackground(Color.black);

		gameArea.setLocation(0,actionArea.getHeight());
		gameArea.setSize(FRAMEWIDTH,FRAMEHEIGHT - actionArea.getHeight());
		gameArea.setLayout(null);
		
		playerOneLabel.setText("Player 1 - ");
		playerOneScore.setText(player1score + "");
		playerTwoLabel.setText("Player 2 - ");
		playerTwoScore.setText(player2score + "");
		playerOneLabel.setForeground(Color.white);
		playerOneScore.setForeground(Color.white);
		playerTwoLabel.setForeground(Color.white);
		playerTwoScore.setForeground(Color.white);

		setLocations();

		actionArea.add(newGame);
		actionArea.add(playerOneLabel);
		actionArea.add(playerOneScore);
		actionArea.add(playerTwoLabel);
		actionArea.add(playerTwoScore);
		
		
		
		window.add(actionArea);
		window.add(gameArea);
		
		window.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		gameLoop();
	}
	
	private void gameLoop(){
		running = true;

		ball = new Ball(FRAMEWIDTH / 2, gameArea.getHeight() / 2);
		gameArea.add(ball);
		gameArea.paintImmediately(gameArea.getVisibleRect());
		
		int dx = 2;
		int dy = 2;
		
		while(running){
			try{
				ball.bounceBall(dx, dy);
				gameArea.paintImmediately(gameArea.getVisibleRect());
				Thread.sleep(DELAY);
				if (bounceX()) {
					dx = dx * -1;
				}
				if (bounceY()) {
					dy = dy * -1;
				}
			}catch(InterruptedException f){}
			playerOneScore.setText(player1score + "");
			playerTwoScore.setText(player2score + "");
			actionArea.paintImmediately(actionArea.getVisibleRect());
			
			if(player1score == 5 || player2score == 5){
				running = false;
				gameArea.remove(ball);
			}
		}
	}
	
	private boolean bounceX(){
		if (ball.getX() <= 0) {
			player2score++;
			return true;
		}
		else if ((ball.getX() + ball.getWidth()) >= gameArea.getWidth()) {
			player1score++;
			return true;
		}
		return false;
	}
	
	private boolean bounceY(){
		if (ball.getY() <= 0) {
			return true;
		}
		else if ((ball.getY() + ball.getHeight() + 20) >= gameArea.getHeight()) {
			return true;
		}
		return false;
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
	}
	
    public void keyPressed(KeyEvent e) {
    	/*
    	int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_UP)
		{
			paddle2y -= JUMP;
		}
		if(keyCode == KeyEvent.VK_DOWN)
		{
			paddle2y += JUMP;
		}
		if(keyCode == KeyEvent.VK_W)
		{
			paddle1y -= JUMP;
		}
		if(keyCode == KeyEvent.VK_S)
		{
			paddle1y += JUMP;
		}
		
		e.consume();
		*/
	}

	public void keyTyped(KeyEvent e) {
		e.consume();
	}

	public void keyReleased(KeyEvent e) {
		e.consume();
	}
}
