package hw2;

import java.io.FileNotFoundException;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A Words object is used to randomly choose words from a file.
 * The file format is one word per line.
 * 
 * @author Gabby Ortman: fine, fresh, fatigued 
 */
public class Words
{
	
	/**
	 * wordList will hold an ArrayList of all of the words in our input file(words, words2, etc)
	 */
	private ArrayList<String> wordList; 
	
  /**
   * Constructs a Words object that will use the strings from 
   * the given file.
   * @param filename
   *   name of file containing words
   * @throws FileNotFoundException
   *   if the word file cannot be opened
   */
  public Words(String filename) throws FileNotFoundException
  {
	  //Open file, parse file, put all words into an array
	  wordList = fileToArray(filename); 
  }
  
  /**
   * Helper method to automatically put words located in a file into an ArrayList, cool.
   * @param 
   * 	name of the file containing the words 
   * @return 
   * 	the ArrayList wordList
   * @throws FileNotFoundException 
   * 	if the word file cannot be opened 
   */
  private ArrayList<String> fileToArray(String filename) throws FileNotFoundException
  { 
	  File wordFile = new File(filename); 
	  Scanner fileParser = new Scanner(wordFile); 
	  wordList = new ArrayList<String>(); 
	  
	  //While the file still has lines, add that line to wordList 
	  while (fileParser.hasNextLine())
	  { 
		  String nextWord = fileParser.nextLine(); 
		  wordList.add(nextWord); 
	  }
	  fileParser.close();
	  return wordList; 
  }
  
  /**
   * Get a randomly chosen word from this word list
   * using the given <code>Random</code> object as the source of randomness.
   * Specifically, the method returns the i-th word from the file, where
   * i is the value returned by <code>rand.nextInt(n)</code>, <code>n</code> is
   * total number of words, and the index i is 0-based.
   * @return
   *   randomly chosen word
   * @throws FileNotFoundException
   *   if the word file cannot be opened
   */
  public String getWord(Random rand) throws FileNotFoundException
  {   
	rand = new Random(); 
	
	//Pick random integer from 0, size of wordList
	//get the string in wordList with the position of randomInt  
	int randomInt = rand.nextInt(wordList.size()); 
	String randomWord = wordList.get(randomInt); 
	
    return randomWord; 
  }
  

}
