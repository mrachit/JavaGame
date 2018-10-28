import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tank 
{
	private JPanel box;
	private int x , y ;
	private int velY = 4;
	private int height=50,width=50;
	private Dimension d;
	
	
	public Tank(JPanel b)
	{
		 box = b;
		 d = box.getSize();
		 x = box.getX();
		 y = box.getY();
	}
	public void draw()
	{
		 Graphics g = box.getGraphics();
		 g.setColor(Color.orange);
		 g.setXORMode(box.getBackground());
		 g.fillRect(x, y,50,50);
		 g.dispose();
	}
	public void move()
	{
		 Graphics g = box.getGraphics(); 
		 g.setColor(Color.orange);
		 g.setXORMode(box.getBackground());
		 g.fillRect(x, y, 50,50);
		 
		 y = y + velY;
		 
		if(y < 0 || y > d.getHeight() - 30)
			velY = -velY;
		g.fillRect(x, y, width, height);
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
