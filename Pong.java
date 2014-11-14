import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JPanel gameArea = new JPanel();
	private JPanel topLeftBlock = new JPanel();
	private JPanel bottomLeftBlock = new JPanel();
	private JPanel topRightBlock = new JPanel();
	private JPanel bottomRightBlock = new JPanel();
	private JPanel rightScore = new JPanel();
	private JPanel leftScore = new JPanel();

	private JButton newGame = new JButton("Start Game");

	private JLabel playerOneLabel = new JLabel();
	private JLabel playerTwoLabel = new JLabel();

	private String playerOne = "Player 1 score: ";
	private String playerTwo = "Player 2 score: ";

	private final int FRAME_WIDTH = 525;
	private final int FRAME_HEIGHT = 490;
	private final int SPEED = 1;
	private final int paddleMove = 5;
	private int playerOneScore;
	private int playerTwoScore;
	private int ballX = SPEED;
	private int ballY = SPEED;
	private int xPos;
	private int yPos;

	private Ball ball;
	private Paddle leftPaddle, rightPaddle;

	private Timer gameTimer;

	public Pong(){
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(null);
		super.setTitle("Pong");

		mainPanel.setLayout(null);
		gameArea.setLayout(null);

		mainPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		mainPanel.setLocation(0,0);
		mainPanel.setBackground(Color.black);

		gameArea.setSize(500, 400);
		gameArea.setLocation(10,50);
		gameArea.setBackground(Color.green);

		newGame.setSize(100, 20);
		newGame.setLocation(10, 10);
		newGame.addActionListener(new newGameListener());

		playerOneLabel.setLocation(FRAME_WIDTH  - 150, 5);
		playerOneLabel.setSize(100, 15);
		playerOneLabel.setForeground(Color.white);
		playerTwoLabel.setLocation(playerOneLabel.getX(), playerOneLabel.getY() + playerOneLabel.getHeight() + 5);
		playerTwoLabel.setSize(100, 15);
		playerTwoLabel.setForeground(Color.white);

		topLeftBlock.setSize(10, 150);
		topLeftBlock.setLocation(0,0);

		bottomLeftBlock.setSize(10, topLeftBlock.getHeight());
		bottomLeftBlock.setLocation(0,gameArea.getHeight() - topLeftBlock.getHeight());

		leftScore.setSize(topLeftBlock.getWidth(), gameArea.getHeight() - topLeftBlock.getHeight() - bottomLeftBlock.getHeight());
		leftScore.setLocation(0,topLeftBlock.getHeight());

		topRightBlock.setSize(10, topLeftBlock.getHeight());
		topRightBlock.setLocation(gameArea.getWidth() - topRightBlock.getWidth(), 0);

		bottomRightBlock.setSize(10, topLeftBlock.getHeight());
		bottomRightBlock.setLocation(gameArea.getWidth() - topRightBlock.getWidth(),gameArea.getHeight() - topLeftBlock.getHeight());

		rightScore.setSize(topRightBlock.getWidth(), gameArea.getHeight() - topRightBlock.getHeight() - bottomRightBlock.getHeight());
		rightScore.setLocation(gameArea.getWidth() - rightScore.getWidth(),topRightBlock.getHeight());

		topLeftBlock.setBackground(Color.black);
		topRightBlock.setBackground(Color.black);
		bottomLeftBlock.setBackground(Color.black);
		bottomRightBlock.setBackground(Color.black);
		rightScore.setBackground(Color.white);
		leftScore.setBackground(Color.white);

		mainPanel.add(newGame);
		mainPanel.add(playerOneLabel);
		mainPanel.add(playerTwoLabel);

		gameArea.add(bottomLeftBlock);
		gameArea.add(bottomRightBlock);
		gameArea.add(topLeftBlock);
		gameArea.add(topRightBlock);
		gameArea.add(rightScore);
		gameArea.add(leftScore);

		super.add(mainPanel);
		mainPanel.add(gameArea);

		gameTimer = new Timer(5, new timerListener());

		super.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		super.setResizable(false);
		super.setVisible(true);
		super.setLocationRelativeTo(null);
	}

	private boolean bounceHorizontal(){
		boolean result = false;

		if(ball.getBounds().intersects(bottomRightBlock.getBounds())){
			result = true;
		}

		if(ball.getBounds().intersects(topRightBlock.getBounds())){
			result = true;
		}

		if(ball.getBounds().intersects(topLeftBlock.getBounds())){
			result = true;
		}

		if(ball.getBounds().intersects(bottomLeftBlock.getBounds())){
			result = true;
		}

		if(ball.getBounds().intersects(leftScore.getBounds())){
			result = true;
			playerTwoScore++;
		}

		if(ball.getBounds().intersects(rightScore.getBounds())){
			result = true;
			playerOneScore++;
		}
		
		if(ball.getBounds().intersects(leftPaddle.getBounds()))
			result = true;
		
		if(ball.getBounds().intersects(rightPaddle.getBounds()))
			result = true;

		playerOneLabel.setText(playerOne + playerOneScore);
		playerTwoLabel.setText(playerTwo + playerTwoScore);
		repaint();

		if(playerOneScore == 5){
			JOptionPane.showMessageDialog(null,"Game Over\nPlayer 1 has won");
			System.exit(0);
		}

		if(playerTwoScore == 5){
			JOptionPane.showMessageDialog(null,"Game Over\nPlayer 2 has won");
			System.exit(0);
		}

		return result;
	}

	private boolean bounceVertical(){
		boolean result = false;

		if(ball.getY() <= 0)
			result = true;
		if(ball.getY() + ball.getHeight() >= gameArea.getHeight())
			result = true;

		return result;
	}

	private class newGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			playerOneScore = 0;
			playerTwoScore = 0;
			newGame.setVisible(false);
			ball = new Ball(gameArea.getWidth() / 2, gameArea.getHeight() / 2);
			leftPaddle = new Paddle(20, gameArea.getHeight() / 2);
			rightPaddle = new Paddle(gameArea.getWidth() - 30, gameArea.getHeight() / 2);
			gameArea.addKeyListener(new leftPaddleListener());
			gameArea.addKeyListener(new rightPaddleListener());
			gameArea.add(ball);
			gameArea.add(leftPaddle);
			gameArea.add(rightPaddle);

			repaint();
			gameArea.requestFocus();
			gameTimer.start();
		}
	}

	private class rightPaddleListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 38){
				if(rightPaddle.getY() >= 5)
					rightPaddle.movePaddle(paddleMove * -1);
			}
			if(e.getKeyCode() == 40){
				if(rightPaddle.getY() + rightPaddle.getHeight() <= gameArea.getHeight() - 5)
					rightPaddle.movePaddle(paddleMove);
			}
			repaint();
			e.consume();
		}

		public void keyReleased(KeyEvent e){e.consume();}
		public void keyTyped(KeyEvent e) {e.consume();}
	}

	private class leftPaddleListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 87){
				if(leftPaddle.getY() >= 5)
					leftPaddle.movePaddle(paddleMove * -1);
			}
			if(e.getKeyCode() == 83){
				if(leftPaddle.getY() + leftPaddle.getHeight() <= gameArea.getHeight() - 5)
					leftPaddle.movePaddle(paddleMove);
			}
			repaint();
			e.consume();
		}

		public void keyReleased(KeyEvent e){e.consume();}
		public void keyTyped(KeyEvent e) {e.consume();}
	}

	private class timerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if (bounceHorizontal())
				ballX = ballX * -1;
				
			if (bounceVertical())
				ballY = ballY * -1;

			ball.moveBall(ballX, ballY);
		}
	}
}
