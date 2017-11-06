
public class tempmain
{
	public static void main(String[] args)
	{
		//input argument should be a STRING of the FILENAME.
		//when using FileChooser, get the file object then use .getName();
		readTxtFile read = new readTxtFile("input.txt");
		
		System.out.println("Total lines: " + read.getLines());
		System.out.println("Blanks: " + read.getBlanks());
		System.out.println("Spaces: " + read.getSpaces());
		System.out.println("Words: " + read.getWords());
		System.out.println("Average line character length: " + read.getLineCharAvg());
		System.out.println("Average word character length: " + read.getWordCharAvg());
	}
}
