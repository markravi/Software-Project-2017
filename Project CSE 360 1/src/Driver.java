import javax.swing.*;
import java.util.*;

public class Driver extends JApplet
{

	 int APPLET_WIDTH = 480, APPLET_HEIGHT = 560;
	 CreatePanel createPanel;
	
	public void init()
	{
		createPanel = new CreatePanel();
		getContentPane().add(createPanel);
		setSize(APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
	}
}
