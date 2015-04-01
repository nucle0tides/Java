package hw2;

import util.PermutationGenerator;

import java.io.FileNotFoundException;
import java.util.Random; 

public class WordScramblerTest {
	public static void main(String[] args) throws FileNotFoundException
	{ 
		Random rand = new Random(137);
		 PermutationGenerator gen = new PermutationGenerator(rand);
		 String result = WordScrambler.scramble("ABCD", gen);
		 System.out.println(result);
		 System.out.println("Expected BDAC");
//		 Random rand = new Random(137); 
//		 Words words = new Words("words2.txt"); 
//		 PermutationGenerator gen = new PermutationGenerator(rand);
//		 String word = words.getWord(rand); 
//		 String result = WordScrambler.scramble(word, gen);
//		 System.out.println("The chosen word is:"); 
//		 System.out.println(word);
//		 System.out.println("The result is:");
//		 System.out.println(result);
	}
}
