import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.audio.AudioPlayer;

public class GameTimer extends Thread
{

	JLabel timeLeft;
	int time,x;
	Tank t;
	Obstruct o;
	Nozzle n;

	public GameTimer(JPanel south,Tank t1,Nozzle n1,Obstruct o1)
	{
		t=t1;
		n=n1;
		o=o1;
		time = 60;

		timeLeft = new JLabel("Time left   00:" + time);
		timeLeft.setFont(new Font("Cambria", Font.BOLD, 22));
		timeLeft.setBackground(Color.BLACK);
		timeLeft.setForeground(Color.GREEN);
	}
	public void reduceTimer()
	{
		time--;
		timeLeft.setText("Time left   00:" + time);
	}
	public void run()
	 {
		    try
		    {
		    	t.draw();
		    	n.draw();
		    	o.draw();
		    	reduceTimer();
		    	x=time*10;
		      for (int i = 1; i <= x; i++)
		      {
		    	  t.move();
		    	  n.move();
		    	  o.move();


		    	 if((i%10)==0)
		            reduceTimer();
		        sleep(100);
		      }
		      AudioPlayer.player.stop(ProGameFrame.BGM1);
    		  ProGameFrame.count=100;

		    }catch (Exception e) {}
	   }
}
