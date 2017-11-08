import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//need to add a helpListener 
//need to allow the history to be interactive and show the information about each particular file
//need to handle errors

public class CreatePanel extends JPanel
 {
//Initializing the Jcomponents and JPanels
 private JButton submit;
 private JButton browse;
 private JButton help;
 SubmitListener subListen;
 BrowseListener browseListen;
 JTextArea fileInformation;
 JScrollPane scroll;
 JTextArea fileHistory;
 JPanel listPane;
 JPanel buttonPane;
 JPanel helpPane;
 JFrame frame;
 JTextField browseFilePath;
 
 public CreatePanel(){
	 //initializing all the buttons, text areas
	 browseFilePath = new JTextField("file path"); //where the file path of the inputed file is displayed
	 browseFilePath.setBounds(0, 0, this.getWidth(), 2);
	 
	 browse = new JButton("browse"); // allows the user to browse for a file
	 browseListen = new BrowseListener();
	 browse.addActionListener(browseListen);
	    
	 submit = new JButton("submit"); //this button determines all the information about the file 
	 subListen = new SubmitListener();
	 submit.addActionListener(subListen);
	 
	 help = new JButton("help"); //this button displays the help page
	 //listen = new ButtonListener();
	 //help.addActionListener(listen);
	 
	 fileInformation = new JTextArea("No Files"); //displays the information (items a-g) about the file 
	 fileInformation.setSize(50,80);
	 
	 scroll = new JScrollPane(fileInformation); 
	 
	 fileHistory = new JTextArea("No File History"); //displays the previous files that were inputted into the program
	 fileHistory.setSize(50,80);
	 
	
	 //declaring/setting up the panels
	 buttonPane = new JPanel();
	 buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
	 buttonPane.add(browse);
	 buttonPane.add(submit);
	 
	 listPane = new JPanel();
	 listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
	 listPane.add(buttonPane);
	 listPane.add(fileInformation);
	 listPane.add(new JLabel(" "));
	 listPane.add(fileHistory);
	 listPane.add(new JLabel(" "));
	 
	 helpPane = new JPanel();
	 helpPane.setLayout(new BoxLayout(helpPane, BoxLayout.LINE_AXIS));
	 helpPane.add(help);
	 
	 //adding all the elements to the default pane
	 setLayout(new BorderLayout());
	 add(browseFilePath,BorderLayout.NORTH);
	 add(listPane,BorderLayout.CENTER); //adding listPane to the default Panel
	 add(helpPane, BorderLayout.SOUTH);//adding the helpPane to the default Panel
 }
 
 int choice;
 JFileChooser chooser;
 private class BrowseListener implements ActionListener
 {
	 public void actionPerformed(ActionEvent event){
		 chooser = new JFileChooser();//file chooser created
	     choice =chooser.showOpenDialog(frame);//opens and lets user select a file
	     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//makes sure they can only select a file
	 }
 }//end of BrowseListener class
 
boolean first = false; //Used to check if it is the first input
private class SubmitListener implements ActionListener
{
 public void actionPerformed(ActionEvent event)
  {  
     if(choice==JFileChooser.APPROVE_OPTION) {//loop to handle closing without a file being selected
    	 File f=chooser.getSelectedFile();//gets the file
    	 
    	 String filePathName=f.getAbsolutePath();//gets the name of the filepath
    	 String fileName = filePathName.substring(filePathName.lastIndexOf('\\') + 1);
    	 readTxtFile Reader =new readTxtFile(filePathName);//text file reader created
 
    	 browseFilePath.setText(filePathName); //Allows the user to view the file path
    	 
    	 if(first == false){//check used to determine if input is the first in text field
    		 
    	   fileHistory.setText(fileName);
    	   first = true;
     }
    	 else {
    		//Displays the files on the fileHistory text area
    		String S = fileHistory.getText();
    		S = S + "\n" + fileName;
    		fileHistory.setText(S);
    		
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
  }//end of submitListener
    
 	// if there is no error, add a project to project list
    // otherwise, show an error message

  } //end of actionPerformed method
 }//end of CreatePanel
