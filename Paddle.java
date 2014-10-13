import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.*;

class Paddle extends JButton{
		
	private int x, y;
	/**
	 * Constructor for the Ship class 
	 */
	public Paddle(int x, int y){
		
		this.x = x;
		this.y = y;
		setLocation(x,y);
		setSize(10, 40);
		setBackground(Color.black);
	}   
	
	public void move(int dy){
		y = y + dy;
		super.setLocation(x, y);
	}
}