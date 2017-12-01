import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class CreatePanel extends JPanel
{
	
//Initializing the Jcomponents and JPanels
 private JButton submit;
 private JButton browse;
 private JButton help;
 private JButton average;
 SubmitListener subListen;
 BrowseListener browseListen;
 HelpListener helpListen;
 AverageListener avgListen;
 JTextArea fileInformation;
 JScrollPane scroll;
 JTextArea fileHistory;
 JPanel listPane;
 JPanel buttonPane;
 JPanel helpPane;
 JPanel textFieldPane;
 JFrame frame;
 JLabel browseFilePath;
 JLabel analysisLabel, fileHistoryLabel;
 ArrayList<readTxtFile> history = new ArrayList();
 private ArrayList<String> McWords= new ArrayList();
 int TotalFiles;
 
 public CreatePanel()
 {
	//initializing the frame
	 frame = new JFrame("Text Analzyer");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 //initializing all the components below
	 
	 browseFilePath = new JLabel(); //where the file path of the inputed file is displayed
	 
	 
	 browse = new JButton("browse"); // allows the user to browse for a file
	 browseListen = new BrowseListener();
	 browse.addActionListener(browseListen);
	    
	 submit = new JButton("submit"); //this button determines all the information about the file 
	 subListen = new SubmitListener();
	 submit.addActionListener(subListen);
	 
	 average = new JButton("Calculate Average");//alows the user to calculate the averages of the files inputted
	 avgListen = new AverageListener();
	 average.addActionListener(avgListen);
	 
	 help = new JButton("help"); //this button displays the help page
	 helpListen = new HelpListener();
	 help.addActionListener(helpListen);
	 
	 fileInformation = new JTextArea("No Files"); //displays the information (items a-g) about the file 
	 fileInformation.setSize(50,70);
	 
	 
	 fileHistory = new JTextArea("No File History"); //displays the previous files that were inputted into the program
	 fileHistory.setSize(50,70);
	 
	
	 //declaring and setting up the panels
	 
	 //adding the components and then formatting the buttonPane
	 buttonPane = new JPanel();
	 buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
	 buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	 buttonPane.add(browse);
	 buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
	 buttonPane.add(submit);
	 buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
	 buttonPane.add(average);
	 buttonPane.add(Box.createHorizontalGlue());
	 
	 //adding the components and then formatting the helpPane
	 helpPane = new JPanel();
	 helpPane.setLayout(new BoxLayout(helpPane, BoxLayout.LINE_AXIS));
	 helpPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
	 helpPane.add(help);
	 
	 //adding the components and then formatting the listPane
	 listPane = new JPanel();
	 listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
	 listPane.add(buttonPane);
	 listPane.add(fileInformation);
	 listPane.add(new JLabel(" "));
	 listPane.add(fileHistory);
	 listPane.add(new JLabel(" "));
	 
	 //adding the rest of the components and panes to the default pane
	 frame.setSize(500, 500);
	 frame.setLayout(new BorderLayout());
	 frame.add(browseFilePath, BorderLayout.NORTH);
	 frame.add(new JLabel("           "), BorderLayout.EAST);
	 frame.add(new JLabel("           "), BorderLayout.WEST);
	 frame.add(listPane,BorderLayout.CENTER); //adding listPane to the default Panel
	 frame.add(helpPane, BorderLayout.SOUTH);//adding the helpPane to the default Panel
	 frame.setVisible(true);
	 //add(frame);
 }
 
 int choice;
 JFileChooser chooser;
 
 //Needs to be written
 private class AverageListener implements ActionListener
 {
	 public void actionPerformed(ActionEvent event){
		 int Tfiles= TotalFiles-1;
		 int Iter =0;
		 double averageL = 0;
		 double averageB = 0;
		 double averageS = 0;
		 double averageW = 0;
		 double averageLc = 0;
		 double averageWc = 0;
		 while(Iter <= Tfiles) {
			 readTxtFile ReaderAve = history.get(Iter); 
			 averageL += ReaderAve.getLines();
			 averageB += ReaderAve.getBlanks();
			 averageS += ReaderAve.getSpaces();
			 averageW += ReaderAve.getWords();
			 averageLc += ReaderAve.getLineCharAvg();
			 averageWc += ReaderAve.getWordCharAvg();
			 McWords.add(ReaderAve.getMostCommon());
			 Iter++;
		 }
		 fileInformation.setText("");
		 fileInformation.append("Total lines: " + Math.floor((averageL/TotalFiles)*100)/100+"\n");
		 fileInformation.append("Blanks: " + Math.floor((averageB/TotalFiles)*100)/100+"\n");
		 fileInformation.append("Spaces: " + Math.floor((averageS/TotalFiles)*100)/100+"\n");
		 fileInformation.append("Words: " + Math.floor((averageW/TotalFiles)*100)/100+"\n");
		 fileInformation.append("Average line character length: " + Math.floor((averageLc/TotalFiles)*100)/100+"\n");
		 fileInformation.append("Average word character length: " + Math.floor((averageWc/TotalFiles)*100)/100+"\n"); 
		for(int i=0; i< (int)McWords.size(); i++){
			for(int j=i+1;j<i;j++) {
				if(McWords.get(i).equals(McWords.get(j))) {
					McWords.remove(j);
				}
			}
		}
		String McString=McWords.get(0);
		for(int i=1; i< (int)McWords.size(); i++){
		McString= McString +","+ McWords.get(i);
		}
		fileInformation.append("Most Common Word(s): " +McWords +"\n");
		McWords.clear();
	 }
 }
 
 private class BrowseListener implements ActionListener
 {
	 public void actionPerformed(ActionEvent event)
	 {
		 chooser = new JFileChooser();//file chooser created
	     choice = chooser.showOpenDialog(frame);//opens and lets user select a file
	     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//makes sure they can only select a file
	 }
 }//end of BrowseListener class
 
boolean first = false; //Used to check if it is the first input
private class SubmitListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{  
		if(choice == JFileChooser.APPROVE_OPTION)
		{//loop to handle closing without a file being selected
			File f = chooser.getSelectedFile();//gets the file
    	 
			String filePathName = f.getAbsolutePath();//gets the name of the filepath
			String fileName = filePathName.substring(filePathName.lastIndexOf('\\') + 1);
			readTxtFile Reader = new readTxtFile(filePathName);//text file reader created
			
			history.add(Reader); //Add analysis to an arraylist
			TotalFiles++; //updates the current amount of files

			browseFilePath.setText("           " + filePathName); //Allows the user to view the file path
    	 
			if(first == false)
			{//check used to determine if input is the first in text field   		 
				fileHistory.setText(fileName + "\t\t" + Reader.getTime());
				first = true;
			}
			else
			{
				//Displays the files on the fileHistory text area
				String S = fileHistory.getText();
				S = S + "\n" + fileName + "\t\t" + Reader.getTime();
				fileHistory.setText(S);	
			}
			// if there is no error, add a project to project list
			// otherwise, show an error message
			
			//printing all the information
			fileInformation.setText("");
			fileInformation.append("Total lines: " + Reader.getLines()+"\n");
			fileInformation.append("Blanks: " + Reader.getBlanks()+"\n");
			fileInformation.append("Spaces: " + Reader.getSpaces()+"\n");
			fileInformation.append("Words: " + Reader.getWords()+"\n");
			fileInformation.append("Average line character length: " + Reader.getLineCharAvg()+"\n");
			fileInformation.append("Average word character length: " + Reader.getWordCharAvg()+"\n");
			fileInformation.append("Most Common Word(s): " + Reader.getMostCommon()+"\n");
			fileInformation.append("\nFile Processed: " + Reader.getTime());  
		}
	}
}//end of submitListener

private class HelpListener implements ActionListener
{
	public void actionPerformed(ActionEvent event)
	{
		JFrame fr = new JFrame("Help");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JTextArea ft = new JTextArea("1. Select a text file to be analyzed using the browse button.\n 2. When the correct file is selected, press the submit button to perform the analysis.\n 3. All previously analyzed files will have their results saved for averaging.\n\nSoftware Developed by\nMarkanday Ravi\nCallahan Stormer\nKyle Stevens\n\n For CSE360\nCalliss MW 4:30");
		fr.setSize(500, 500);
		fr.add(ft);
		fr.setVisible(true);	 
	}
}

}
//end of CreatePanel
