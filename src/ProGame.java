import javax.swing.JOptionPane;


public class ProGame
{
	public static void main(String[] args)
	  {
		    ProGameFrame frame = new ProGameFrame();
		    frame.setVisible(true);
		    JOptionPane.showMessageDialog(frame, "You have 1 minute and 100 bullets...\nPress SPACE to Hit all you can\nPress ENTER+  to Start this Awesome Game");
	  }
}
