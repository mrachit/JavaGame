import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Obstruct 
{
	private JPanel box;
	private int x , y ;
	private int velY = 8;
	int width = 50, height = 50;
	private Dimension d;

	
	public Obstruct(JPanel b)
	{
		// finished = false;
		 box = b;
		 d = box.getSize();
		 x = 600;
		 y = 20;
	}
	public void draw()
	{
		
		 Graphics g = box.getGraphics();
		 g.setColor(Color.orange);
		 g.setXORMode(box.getBackground());
		 g.fillRect(x , y, width, height);
		 g.dispose();

	}
	public void move()
	{
		Graphics g = box.getGraphics();
		g.setColor(Color.orange);  
		g.setXORMode(box.getBackground());
		g.fillRect(x , y, width, height);
		 if(y < 0 || y > d.getHeight() - 25)
			 velY = -velY;
		 
		 y=y+velY;
		
		 g.fillRect(x , y, width, height);
		 g.dispose();
	}
	public int getXPos()
	 {
		 return x;
	 }
	public int getYPos()
	 {
		 return y;
	 }
	public void setXPos(int xpos)
	 {
		 x = xpos;
	 }
	public void setYPos(int ypos)
	 {
		 y = ypos;
	 }
}
