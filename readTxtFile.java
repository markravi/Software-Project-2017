import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.*;

public class readTxtFile 
{
	private String fileName;
	private int lines;
	private int blanks;
	private int spaces;
	private int words;
	private double lineCharAvg;
	private double wordCharAvg;
	private ArrayList<String> uniqueWords;
	private int[] uniqueNum;
	private String time;
	
	private int[] checkWords(ArrayList<String> words, int[] numbers, String word)
	{
		String trueWord = word.toLowerCase();
		int index = 0;
		int[] newNumbers;
		if (words.contains(trueWord))
		{
			index = words.indexOf(trueWord);
			numbers[index] = numbers[index]+1;
			return numbers;
		}
		else
		{
			words.add(trueWord);
			newNumbers = new int[words.size()];
			for(int j = 0; j < numbers.length; j++)
			{
				newNumbers[j] = numbers[j];
			}
			if (trueWord.length() >= 1)
			{
				newNumbers[newNumbers.length-1] = 1;
			}
			return newNumbers;
		}
	}
	
	private String delChars(int mode, String word)
	{
		String newStr = word;
		if (mode == 0)
		{
			newStr = newStr.replace("\n", "");
			newStr = newStr.replace("\t", "");
			newStr = newStr.replace(".", "");
			newStr = newStr.replace("/", "");
			newStr = newStr.replace("\\", "");
			newStr = newStr.replace("\r", "");
			newStr = newStr.replace(",", "");
			newStr = newStr.replace("\"", "");
			newStr = newStr.replace("\'", "");
			newStr = newStr.replace(";", "");
			newStr = newStr.replace(":", "");
			newStr = newStr.replace("-", "");
			newStr = newStr.replace("_", "");
			newStr = newStr.replace("'s", "");
			newStr = newStr.replace("?", "");
			newStr = newStr.replace("!", "");
			newStr = newStr.replace("(", "");
			newStr = newStr.replace(")", "");
			newStr = newStr.replace("+", "");
			newStr = newStr.replace("=", "");
			newStr = newStr.replace("'", "");
			return newStr;
		}
		else
		{
			newStr = newStr.replace("\n", "");
			newStr = newStr.replace("\t", "");
			newStr = newStr.replace("\r", "");
			return newStr;
		}
	}
	
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
		
		uniqueWords = new ArrayList();
		uniqueNum = new int[0];
		
		for(int i = 0; i <= Array.getLength(linesArray)-1; i++)//iterate line by line through the array of lines.
		{
			linesArray[i] = linesArray[i].substring(0, linesArray[i].length()); //cut off the newline character.
			String[] words = linesArray[i].split(" "); //split the current line into another array of words.
			for(int j = 0; j <= words.length-1; j++)
			{
				words[j] = delChars(0,words[j]);
			}
			
			linesWords[i] = words; //place word array in the 2d array.
			
			if(delChars(1,linesArray[i]).length() == 0) //if the line is blank, iterate.
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
				uniqueNum = checkWords(uniqueWords, uniqueNum, words[j]);
				wordCharSum += words[j].length();
			}
			
			numWords += words.length;//add the number of words in the line to the total.
			
			lineCharSum += linesArray[i].length();//get the string length and add it to the total.
		}
		
		lineCharAverage = (double)lineCharSum/numLines;//get true average line character length.
		wordCharAverage = (double)wordCharSum/numWords;//get true average character length
		
		String dateTime = new java.text.SimpleDateFormat("MM-dd-YYYY HH:mm:ss").format(new java.util.Date());
		
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
		return Math.floor(lineCharAvg*100)/100;
	}
	
	public double getWordCharAvg()
	{
		return Math.floor(wordCharAvg*100)/100;
	}
	
	public String getMostCommon()
	{
		int maximum = 0;
		String ret = "";
		for(int i = 0; i < (int)uniqueWords.size(); i++)
		{
			if (uniqueNum[i] > maximum && uniqueWords.get(i).length() != 0)
			{
				maximum = uniqueNum[i];
			}
		}
		
		for(int i = 0; i < (int)uniqueWords.size(); i++)
		{
			if (uniqueNum[i] == maximum)
			{
				if (ret == "")
				{
					ret = uniqueWords.get(i);
				}
				else
				{
					ret = ret + ", " + uniqueWords.get(i);
				}
			}
		}
		
		return ret;
	}
	
	public String getTime()
	{
		return time;
	}
}
