
import java.io.*;
import java.util.*;


public class Lex
{
	public static void main(String[] args) throws IOException
	{
		 if(args.length == 0 || args.length == 1 || args.length > 2)
		    {
		        System.err.println("Proper Usage is 2 Strings: inputFile outputFile");
		        System.exit(0);
		    }		 	
				
		String inputFileName = new String(args[0]);
		FileWriter outputFileName = new FileWriter(args[1]);
		
		//count lines of input file for length
		File fileInput = new File(inputFileName);
		Scanner in = new Scanner(fileInput); 
		
		int lineCount = 0;
		while(in.hasNextLine())
		{
			in.nextLine();
			lineCount++;
		}
		in.close();
		
		//input file into string array					
		int length = lineCount;
		String[] stringArray = new String[length];
		
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		String line;
		
		while((line = br.readLine()) != null)
		{
			for(int i = 0; i < stringArray.length; i++)
			{
				stringArray[i] = line;
				line = br.readLine();
			}
		}
	
		//sort in lex order
		List l = new List();
		if(l.isEmpty())
			 l.append(0);
		 
		int i;
		
		for(i = 1; i < stringArray.length; i++)
		{
			String s = stringArray[i];
		 	int j = i - 1;
			l.moveTo(j);
	
			while( j > -1 && s.compareTo(stringArray[l.getElement()]) < 0)
			{
				l.movePrev();
				j--;
			}
		
			if(l.getIndex() == -1)
				l.prepend(i);
			
			else
				l.insertAfter(i);
		}

		//write to output file				
		PrintWriter outFile = new PrintWriter(outputFileName);
		
		for(l.moveTo(0); l.getIndex() >= 0; l.moveNext())		
			outFile.println(stringArray[l.getElement()]);

		outFile.close();		
	}
	
}
