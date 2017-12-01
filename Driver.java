import javax.swing.*;

import java.awt.Panel;
import java.util.*;

public class Driver extends JApplet
{
	private int APPLET_WIDTH = 900, APPLET_HEIGHT = 350;
	private CreatePanel createPanel;
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CreatePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void init()
	{
		 createPanel = new CreatePanel();
		 getContentPane().add(createPanel);
		 
		 setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
	}
}
