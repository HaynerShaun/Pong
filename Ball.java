import java.awt.Color;
import javax.swing.*;

class Ball extends JButton{
		
	private int x, y;
	/**
	 * Constructor for the Ship class 
	 */
	public Ball(int x, int y){
		
		this.x = x;
		this.y = y;
		setLocation(x,y);
		setSize(10, 10);
		setBackground(Color.black);
	}   
	
	public void bounceBall(int dx, int dy){
		x = x + dx;
		y = y + dy;
		super.setLocation(x, y);
	}
	
	public void resetBall(int dx, int dy){
		setLocation(dx, dy);
	}
}