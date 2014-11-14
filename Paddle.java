import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Paddle extends JLabel{

	private int height = 40;
	private int width = 10;
	private int xPos, yPos;
	
	public Paddle(int x, int y){
		xPos = x;
		yPos = y;
		super.setLocation(xPos, yPos);
		super.setSize(width, height);
		super.setBackground(Color.black);
		super.setOpaque(true);
	}
	
	public void movePaddle(int y){
		yPos += y;
		super.setLocation(xPos, yPos);
	}
}
