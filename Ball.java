import java.awt.Color;
import javax.swing.JButton;


public class Ball extends JButton{
	private int width = 10;
	private int height = 10;
	private int xPos, yPos;
	
	public Ball(int x, int y){
		xPos = x;
		yPos = y;
		super.setLocation(x,y);
		super.setSize(width, height);
		super.setBackground(Color.black);
	}
	
	public int returnWidth(){
		return width;
	}
	
	public int returnHeight(){
		return height;
	}
	
	public void moveBall(int x, int y){
		xPos += x;
		yPos += y;
		super.setLocation(xPos,yPos);
	}
}
