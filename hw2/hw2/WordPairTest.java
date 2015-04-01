package hw2;

public class WordPairTest {
	public static void main(String[] args)
	{ 
		WordPair wp = new WordPair("HELLO", "OLLEH111");
		System.out.println(wp.isSolutionPossible()); 
		System.out.println(wp.checkSolution("LOLHE")); 
		 
	}
	
}
