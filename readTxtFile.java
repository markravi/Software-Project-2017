import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;

public class readTxtFile {
	
	private String fileName;
	private int lines;
	private int blanks;
	private int spaces;
	private int words;
	private double lineCharAvg;
	private double wordCharAvg;
	private String time;
	
	public readTxtFile(String file)
	{
		fileName = file;
		String fullText = "";
		int numLines = 0;
		int numBlankLines = 0;
		int numSpaces = 0;
		int numWords = 0;
		int lineCharSum = 0;
		double lineCharAverage = 0;
		int wordCharSum = 0;
		double wordCharAverage = 0;
		
		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
		
		try 
		{
			fullText = new String(Files.readAllBytes(Paths.get(fileName)));
		} 
		catch (IOException e) 
		{
			System.out.println("IOException");
		}
		
		String[] linesArray = fullText.split("\n");//create an array by splitting at newline characters.
		
		numLines = Array.getLength(linesArray)+1;//the total number of lines is the size of the array+1;
		
		String[][] linesWords = new String[numLines][];//create a 2d array of words.
		for(int i = 0; i <= Array.getLength(linesArray)-1; i++)//iterate line by line through the array of lines.
		{
			linesArray[i] = linesArray[i].substring(0, linesArray[i].length()); //cut off the newline character.
			String[] words = linesArray[i].split(" "); //split the current line into another array of words.
			linesWords[i] = words; //place word array in the 2d array.
			
			if(linesArray[i].length() == 0) //if the line is blank, iterate.
			{
				numBlankLines++;
			}
			
			for(int j = 0; j <= linesArray[i].length()-1; j++)//count spaces in the line.
			{
				if (linesArray[i].charAt(j) == ' ')
				{
					numSpaces++;
				}
			}
			
			for(int j = 0; j <= Array.getLength(words)-1; j++)
			{
				wordCharSum += words[j].length();
			}
			
			numWords += words.length;//add the number of words in the line to the total.
			
			lineCharSum += linesArray[i].length();//get the string length and add it to the total.
		}
		
		lineCharAverage = (double)lineCharSum/numLines;//get true average line character length.
		wordCharAverage = (double)wordCharSum/numWords;//get true average character length
		
		String dateTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		
		lines = numLines;
		blanks = numBlankLines;
		spaces = numSpaces;
		words = numWords;
		lineCharAvg = lineCharAverage;
		wordCharAvg = wordCharAverage;
		time = dateTime;
	}
	
	public int getLines()
	{
		return lines;
	}
	
	public int getBlanks()
	{
		return blanks;
	}
	
	public int getSpaces()
	{
		return spaces;
	}
	public int getWords()
	{
		return words;
	}
	public double getLineCharAvg()
	{
		return lineCharAvg;
	}
	public double getWordCharAvg()
	{
		return wordCharAvg;
	}
	public String getTime()
	{
		return time;
	}
}
