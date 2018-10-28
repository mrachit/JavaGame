import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.audio.*;
import java.io.*;


public class ProGameFrame  extends JFrame implements KeyListener
{
	private static final long serialVersionUID = 7526472295622776147L;
	private JPanel eastPane,centerPane,southPane,northPane,westPane;
	private boolean flag = true;
	static JLabel score;
	private JLabel name;
	String playerName;
	JLabel labelScore,ballsLeft;
	Ball b;
	Tank t;
	Nozzle n;
	Obstruct o;
	GameTimer t1;
	static int count=0;
	//AudioStream BGM;
	//static AudioStream BGM1;
	//static AudioStream BGM2;
	//AudioStream BGM3;
	static int gameScore=0;

	public ProGameFrame()
	{
		count = 0;
		gameScore = 0;
		setBounds(200, 0, 1000, 700);
		setTitle("Pro Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//music1();
		Container contentPane = getContentPane();
		eastPane = new JPanel();
	    westPane = new JPanel();
	    southPane = new JPanel();
	    northPane = new JPanel();
	    centerPane = new JPanel();

	    contentPane.add(eastPane, BorderLayout.EAST);
	    contentPane.add(westPane, BorderLayout.WEST);
	    contentPane.add(southPane, BorderLayout.SOUTH);
	    contentPane.add(centerPane, BorderLayout.CENTER);
	    contentPane.add(northPane, BorderLayout.NORTH);

	    centerPane.setVisible(true);
	    westPane.setBackground(Color.BLACK);
	    westPane.add(Box.createRigidArea(new Dimension(20,500)));
	    eastPane.setBackground(Color.BLACK);
	    eastPane.add(Box.createRigidArea(new Dimension(20,500)));
	    score= new JLabel();
	    labelScore = new JLabel();
	    ballsLeft = new JLabel();
	    name = new JLabel();
	    name.setSize(30, 20);
	    //name.setText(Integer.toString(gameScore));
	    name.setForeground(Color.GREEN);
	    name.setFont(new Font("Cambria", Font.BOLD, 22));
	    score.setSize(30, 20);
	    score.setText(Integer.toString(gameScore));
	    score.setForeground(Color.GREEN);
	    score.setFont(new Font("Cambria", Font.BOLD, 22));
	    southPane.add(name);
	    southPane.add(score);
	    labelScore.setSize(30, 20);
	    labelScore.setText("SCORE : ");
	    labelScore.setForeground(Color.GREEN);
	    labelScore.setFont(new Font("Cambria", Font.BOLD, 22));
	    ballsLeft.setText("Bullets Left : " + (100 - count));
	    ballsLeft.setForeground(Color.GREEN);
	    ballsLeft.setFont(new Font("Cambria", Font.BOLD, 22));
	    southPane.add(labelScore);
	    southPane.add(score);
	    southPane.add(Box.createRigidArea(new Dimension(10,20)));
	    southPane.add(ballsLeft);
	    southPane.setBackground(Color.BLACK);
	    southPane.add(Box.createRigidArea(new Dimension(300,20)));
	    northPane.setBackground(Color.BLACK);
	    northPane.add(Box.createRigidArea(new Dimension(500,20)));
	    centerPane.setBackground(Color.RED);
	    addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}
	public void keyPressed(KeyEvent e)
	{
		int c = e.getKeyCode();
		if((c == KeyEvent.VK_SPACE)&& (!flag))
		{
			if(count<100)
			{
				b = new Ball(centerPane,o);
	        	b.setXPos(n.getXPos()+40);
	        	b.setYPos(n.getYPos()  + (int)(40*Math.tan(Math.toRadians((double)n.getAng()))));
	        	b.setAng(n.getAng());
	        	b.start();
	        	music();
	        	count++;
	        	ballsLeft.setText("Bullets Left : " + (100 - count));
			}
			else
			{
				  AudioPlayer.player.stop(BGM1);
	    		  t1.x = 0;
	    		  musicTimeUp();
	    		  showEndMessage();
	    	}
		}
		if((c == KeyEvent.VK_ENTER) && flag)
		{
			askName();
			t = new Tank(centerPane);
			n= new Nozzle(centerPane);
			o = new Obstruct(centerPane);
			n.setXPos(t.getXPos()+30);
	        n.setYPos(t.getYPos()+30);
	        t1 = new GameTimer(southPane,t,n,o);
	        southPane.add(t1.timeLeft);
	        t1.start();
	        flag = false;
		}

		if(c == KeyEvent.VK_UP)
		{
			n.setAngVel(-6);
		}
		if(c == KeyEvent.VK_DOWN)
		{
			n.setAngVel(6);
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e)
	{
		try
		{
			n.setAngVel(0);

		}
		catch(Exception a)
		{

		}
	}
	public void askName()
	{
		playerName = JOptionPane.showInputDialog("Enter Name:");
		name.setText(playerName + "'s ");
	}
	public void showEndMessage()
	{
		JOptionPane.showMessageDialog(this, "GAME OVER !!!!\nYour Score is "+ gameScore + "\nBetter luck next time :P");
		end();

	}
	public void end()
	{
		int answer = JOptionPane.showConfirmDialog(this,"Do you wanna exit?","Quit", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
		else if(answer == JOptionPane.NO_OPTION)
		{
			newGame();
		}
	}
	public void newGame()
	{
		int answer = JOptionPane.showConfirmDialog(centerPane,"Do you wanna play again?","Quit", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION)
		{
			JFrame frame = new ProGameFrame();
		    frame.setVisible(true);
		}
	}

	public void music()
	{
		try
		{
			InputStream test = new FileInputStream("bullet1.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
		}catch(IOException error)
		{
			System.out.print(error.toString());
		}

	}
	public void musicTimeUp()
	{
		System.out.println("In time's up");
		try
		{
			InputStream test = new FileInputStream("timer.wav");
			BGM3 = new AudioStream(test);
			AudioPlayer.player.start(BGM3);
		}catch(IOException error)
		{
			System.out.print(error.toString());
		}
	}
	public void music1()
	{
		try
		{
			InputStream test = new FileInputStream("bg.wav");
			BGM1 = new AudioStream(test);
			AudioPlayer.player.start(BGM1);

		}catch(IOException error)
		{
			System.out.print(error.toString());
		}

	}

}

