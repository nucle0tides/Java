package hw2;

import util.PermutationGenerator;

/**
 * Utility class for rearranging the characters in a string
 * using a <code>PermutationGenerator</code>.  The two 
 * <code>scramble</code> methods are required to use the
 * given <code>PermutationGenerator</code> exactly as described.
 * <p>
 * The methods are static.  This class should not be instantiated.
 * 
 * @author Gabby Ortman: once took an extra side to have with her meal bundle. 
 */
public class WordScrambler
{

  // private constructor prevents accidentally constructing instances
  private WordScrambler(){}
  
  /**
   * Permutes the characters of the given word according 
   * to the next n-element permutation produced by the given
   * permutation generator, where n is the length of the word.
   * <p>
   * For example, if the word is "CDEFG" and the next 5-element
   * permutation is [2, 0, 4, 1, 3], then the method returns the 
   * string "ECGDF".
   * @param word
   *   word to be scrambled
   * @return
   *   scrambled word arranged according to the next appropriately sized permutation
   */
  public static String scramble(String word, PermutationGenerator gen)
  {
	  
	String result = ""; 
	int[] newPermutation = gen.generate(word.length());

	/*
	 * Prints out each value of the new permutation, for fun debugging and testing purposes
	for (int i = 0; i < newPermutation.length; i +=1)
	{ 
		System.out.print(newPermutation[i]);
		
	}
	System.out.println("");
	*/ 
	
	
	//given a permutation, for each i in permutation, get value stored in i, add charAt(i) to result
	//This works, technically, but doesn't match test results?
	for (int i = 0; i < newPermutation.length; i += 1)
	{ 
		int letterSwap = newPermutation[i]; 
		result = result + word.charAt(letterSwap); 
	}
    return result;
  }
  
  
   /**
   * Permutes the characters of the given word according 
   * to the next n-element permutation produced by the given
   * permutation generator, where n is the length of the word
   * minus <code>fixedCount</code>.  Characters with
   * indices less than <code>fixedCount</code> are not moved.
   * <p>
   * For example,
   * if the word is "ABCDEFG" and <code>fixedCount</code> is 2
   * and the generator returns the five-element permutation
   * [2, 0, 4, 1, 3], then the method returns the string "ABECGDF".
   * @param word
   *   word to be scrambled
   * @return
   *   scrambled word with the first <code>fixedCount</code> letters unchanged
   *   and remaining letters arranged according to the next appropriately sized 
   *   permutation
   */
  public static String scramble(String word, int fixedCount, PermutationGenerator gen)
  {
	  String result = ""; 
	  
	  //word.length - fixed count read adjusts range of permutation 
	  int permutationLength = word.length()-fixedCount; 
	  
	  //Arrays cannot be negative, if so, just return the given word
	  if (permutationLength <= 0){ 
		  return result = word; 
	  }
	  
	  //permutationLength > 0
	  int[] newPermutation = gen.generate(permutationLength);
	  
	   /*
	  //print out permutation sequence 
	  for (int i = 0; i < newPermutation.length; i +=1)
	  { 
		  System.out.print(newPermutation[i]);	
	  }
	  System.out.println("");
	  */ 
	  
	  //given a fixed count and word, append that number of characters to the result
	  for (int i = 0; i < fixedCount; i += 1)
	  { 
		  result = result + word.charAt(i); 
	  }
	  
	  //retrieve what's left of the word 
	  String remainingResult = word.substring(fixedCount, word.length()); 
	  
	  
	  for (int i = 0; i < newPermutation.length; i += 1)
	  { 
		  int letterSwap = newPermutation[i]; 
		  result = result + remainingResult.charAt(letterSwap); 
	  }
	  return result; 
  }
  
}
