import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Ball extends Thread
{
	 private JPanel box;
	 int x , y ;
	 int velX = 4, velY = 4;
	 int ang;
	 Dimension d;
	 boolean dead=false;
	 Color c=Color.white;
	 Obstruct o;
	 boolean finished;

	 public Ball(JPanel b,Obstruct o1)
	 {
		 	finished = false;
		    box = b;
		    d = box.getSize();
		    o=o1;
	}
	 public void draw()
	 {
		 Graphics g = box.getGraphics();
		 g.setColor(c);
		 g.setXORMode(box.getBackground());
		 g.fillOval(x,y,10,10);
		 g.dispose();
	 }
	 public void move()
	 {
		 Graphics g = box.getGraphics();
		 g.setColor(c);
		 g.setXORMode(box.getBackground());
		 g.fillOval(x,y,10,10);
		 x = x + velX*10;
		 y = y + (int)(velY*10*Math.tan(Math.toRadians((double)ang)));

		 g.fillOval(x,y,10,10);
		 g.dispose();
	 }
	 public void run()
	 {
		 try
		   {
		      draw();
		      for (int i = 1; i <= 500; i++)
		      {
		    	   move();
		    	  if((x>o.getXPos()) && (x < (o.getXPos()+o.width)) && (y > (o.getYPos()+5)) && (y< (o.getYPos() + o.height-5 )))
		  		{
		  			  hit();
		    		  Graphics g =box.getGraphics();
		    		  g.setColor(Color.white);
		    		  g.fillOval(x,y,10,10);
		    		  g.clearRect(x, y, 10, 10);
		    		  g.setColor(Color.orange);
		    		  g.fillRect(x, y, 10, 10);
		    		  g.dispose();
		  			  c=Color.red;

		    		 break;
		    	  }

		        sleep(30);

		      }

		    }
		      catch (InterruptedException e) {}
		 }
	 public void hit()
	 {
		 dead=true;
		 ProGameFrame.gameScore++;
		 ProGameFrame.score.setText(Integer.toString( ProGameFrame.gameScore));
		 musicHit();
	 }
	 public void musicHit()
	 {

		try
		{
			InputStream test = new FileInputStream("hit.wav");
			ProGameFrame.BGM2 = new AudioStream(test);
			AudioPlayer.player.start(ProGameFrame.BGM2);


		}catch(IOException error)
		{
			System.out.print(error.toString());
		}

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
	 public int getAng()
	 {
		 return ang;
	 }
	 public void setAng(int angle)
	 {
		 ang = angle;
	 }
}
