import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Nozzle 
{
	 private JPanel box;
	 private int x , y ;
	 private int velY = 4;
	 private int ang=0, angVel;

	 		 
	 public Nozzle(JPanel b)
		{
		 	 box = b;
		
			 x = box.getX();
			 y = box.getY();
		}
	 public void draw()
	 {
		 Graphics g = box.getGraphics();
		 g.setColor(Color.orange);
		 g.setXORMode(box.getBackground());
		 int[] xArray = {x,x + 40, x + 40, x};
		 int[] yArray = {y,y + (int)(40*Math.tan(Math.toRadians((double)ang))), y + (int)(40*Math.tan(Math.toRadians((double)ang)))-10, y-10};
		 g.fillPolygon(xArray, yArray, 4);
		 g.dispose();

	 }
	 public void move()
	 {
		Graphics g = box.getGraphics();
		g.setColor(Color.orange);
		g.setXORMode(box.getBackground());
		 
		int[] xArray = {x,x + 40, x + 40, x};
		int[] yArray = {y,y + (int)(40*Math.tan(Math.toRadians((double)ang))), y + (int)(40*Math.tan(Math.toRadians((double)ang)))-10, y-10};
		g.fillPolygon(xArray, yArray, 4);

		 
		y = y + velY;
		 
		if(y < 30 || y > box.getHeight())
			velY = -velY;
		
		 ang = ang + angVel;
		if (ang>45)
			ang=45;
		if (ang<-45)
			ang=-45;
	
		 int[] xArray1 = {x,x + 40, x + 40, x};
		 int[] yArray1 = {y,y + (int)(40*Math.tan(Math.toRadians((double)ang))), y + (int)(40*Math.tan(Math.toRadians((double)ang)))-10, y-10};
		 g.fillPolygon(xArray1, yArray1, 4);
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
		 y = ypos ;
	 }
	 public int getAng()
	 {
		 return ang;
	 }
	 public void setAng(int angle)
	 {
		 ang = angle;
	 }
	 public int getAngVel()
	 {
		 return angVel;
	 }
	 public void setAngVel(int angleVel)
	 {
		 angVel = angleVel;
	 }
}
