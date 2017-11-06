import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

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
	 boolean Check =false; //boolean value to check if its the first insert
	 
     JFileChooser chooser = new JFileChooser();//file chooser created
     
     int choice =chooser.showOpenDialog(frame);//opens and lets user select a file
     
     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//makes sure they can only select a file
     
     if(choice==JFileChooser.APPROVE_OPTION) {//loop to handle closing without a file being selected
    	 File f=chooser.getSelectedFile();//gets the file
    	 
    	 String FName=f.getAbsolutePath();//gets path to file
    	 

    	 readTxtFile Reader =new readTxtFile(FName);//text file reader created
    	 
    	 if(Check == false){//check used to determine if input is the first in text field
    	  fileHistory.setText(FName);
    	  fileHistory.append("\n");
    	  Check = true;
     }
    	 else {
    		fileHistory.append(FName);
    	 }
    	 //printing all the information
    	 fileInformation.setText("");
    	 fileInformation.append("Total lines: " + Reader.getLines()+"\n");
    	 fileInformation.append("Blanks: " + Reader.getBlanks()+"\n");
    	 fileInformation.append("Spaces: " + Reader.getSpaces()+"\n");
    	 fileInformation.append("Words: " + Reader.getWords()+"\n");
    	 fileInformation.append("Average line character length: " + Reader.getLineCharAvg()+"\n");
    	 fileInformation.append("Average word character length: " + Reader.getWordCharAvg()+"\n");
    
    }
  }
    
 	// if there is no error, add a project to project list
    // otherwise, show an error message

  } //end of actionPerformed method
} //end of ButtonListener class

