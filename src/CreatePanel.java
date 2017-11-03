import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CreatePanel extends JPanel
 {
 private JButton submit;
 private JButton browse;
 private JButton help;
 ButtonListener listen;
 JTextArea fileInformation;
 JScrollPane scroll;
 JTextArea fileHistory;
 JPanel listPane;
 JPanel buttonPane;
 JFrame frame;
 JTextArea browseFilePath;
 public CreatePanel(){
	 //initializing all the buttons, text areas
	 //frame = new JFrame("Text Analzyer");
	 setLayout(new GridLayout(1,1));
	 
	 
	 browse = new JButton("browse");
	 listen = new ButtonListener();
	 browse.addActionListener(listen);
	    
	 submit = new JButton("submit");
	 listen = new ButtonListener();
	 submit.addActionListener(listen);
	 
	 help = new JButton("help");
	 listen = new ButtonListener();
	 help.addActionListener(listen);
	 
	 fileInformation = new JTextArea("No Files");
	 fileInformation.setSize(50,80);
	 
	 scroll = new JScrollPane(fileInformation);
	 
	 fileHistory = new JTextArea("No File History");
	 fileHistory.setSize(50,80);
	 
	 browseFilePath = new JTextArea();
	 browseFilePath.setSize(2,5);
	 
	 listPane = new JPanel();
	 
	 listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

	 //add in textline
	 buttonPane = new JPanel();
	 buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
	 buttonPane.add(browse);
	 buttonPane.add(submit);
	 
	 listPane.add(browseFilePath);
	 listPane.add(buttonPane);
	 listPane.add(fileInformation);
	 listPane.add(fileHistory);
	 listPane.add(help);
	 
	 
	 add(listPane); //adding listPane to the default Panel
 }
private class ButtonListener implements ActionListener
{
 public void actionPerformed(ActionEvent event)
  {
     
        
    }
  
    
 	// if there is no error, add a project to project list
    // otherwise, show an error message

  } //end of actionPerformed method
} //end of ButtonListener class

