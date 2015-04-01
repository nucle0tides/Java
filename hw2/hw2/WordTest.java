package hw2;

import java.io.FileNotFoundException;
import java.util.Random; 

public class WordTest 
{
	public static void main(String[] args) throws FileNotFoundException
	{ 
		 Words wordList = new Words("words.txt");
		 Random rand = new Random();
		 String aWord = wordList.getWord(rand);
		 System.out.println(aWord);
		 	
	}
}
