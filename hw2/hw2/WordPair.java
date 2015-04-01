package hw2;

import util.PermutationGenerator;

/**
 * A WordPair encapsulates two representations
 * of a given word - its "real" form, which does not change,
 * and a "scrambled" form in which the letters are rearranged.
 * The scrambled form of the word may be updated by calls to the
 * methods <code>moveLeft</code>, <code>moveRight</code>,
 * <code>doLetterHint</code>, and <code>rescramble</code>.
 * 
 * @author Gabby Ortman: cute as heck.  
 * 
 */
public class WordPair
{
	/**
	 * Holds the value of the unscrambled word
	 */
	private String realWord; 
	/** 
	 * Holds the value of the scrambled word
	 */
	private String scrambledWord; 
	/**
	 * Holds the amount of hints used
	 */
	private int letterHintCount; 
	
  /**
   * Constructs an instance of WordPair with the given strings.
   * This constructor does
   * not check whether the given scrambled word is a valid
   * rearrangement of the given real word, but this can be
   * checked by a subsequent call to the method
   * <code>isSolutionPossible</code>.
   * @param givenRealWord
   *   the real word
   * @param givenScrambledWord
   *   the word with characters rearranged
   */
  public WordPair(String givenRealWord, String givenScrambledWord) 
  {
    realWord = givenRealWord.toUpperCase(); 
    scrambledWord = givenScrambledWord.toUpperCase(); 
    letterHintCount = 0; 
  }
  
  /**
   * Returns the current scrambled form of the word
   * in upper case.
   * @return
   *   scrambled form of the word.
   */
  public String getScrambledWord()
  {
    return scrambledWord;
  }
  
  /**
   * Returns the real word in upper case.
   * @return
   *   the real word
   */
  public String getRealWord()
  {
    return realWord;
  }
  
  /**
   * 
   * A helper method used to check that the index, i, is within range. 
   * 
   * @param i
   * 	the parameter being tested 
   * @param a
   * 	lower bound
   * @param b
   * 	upper bound
   * @return
   * 	boolean value of true if if a < i < b
   * 	false otherwise
   */
  private boolean in_range(int i, int a, int b)
  { 
	  //if a <= i < b, return true
	  if (i >= a && i < b)
	  { 
		  return true; 
	  }
	  else  
	  { 
		  return false; 
	  }
  }
  
  /**
   * Modifies the scrambled word by moving the character at position 
   * <code>index</code> to the right the given number of spaces. 
   * Intervening characters are shifted left.  For example, if the
   * scrambled word is "ABCDEFG", then after moveRight(2, 3) the
   * scrambled word should be "ABDEFCG".  This method should do 
   * nothing if the index is out of range, if <code>howFar</code>
   * is negative, if the index plus <code>howFar</code> is out of range,
   * or if the index is less than <code>getNumLetterHints()</code>.
   * @param index
   *   zero-based index of the character to be moved
   * @param howFar
   *   number of spaces to the right to move the character
   */
  public void moveRight(int index, int howFar)
  { 
	  if(in_range(index, getNumLetterHints(), scrambledWord.length()) && (howFar > 0 && howFar < scrambledWord.length()))
	  { 
		  //substring will be the characters before the index
		  String before = scrambledWord.substring(0, index); 
		  //substring will be characters near the index
		  String mid = scrambledWord.substring(index + 1, index+howFar + 1);
		  //substring will be characters after index
		  String after = scrambledWord.substring(index+howFar + 1, scrambledWord.length());
		  scrambledWord = before + mid + scrambledWord.charAt(index) + after; 
	  }
	  else
	  { 
		  getScrambledWord(); 
	  }
  } 
 
  /**
   * Modifies the scrambled word by moving the character at position 
   * <code>index</code> to the left the given number of spaces. 
   * Intervening characters are shifted right.  For example, if the
   * scrambled word is "ABCDEFG", then after moveLeft(5, 3) the
   * scrambled word should be "ABFCDEG".  This method should do 
   * nothing if the index is out of range, if <code>howFar</code>
   * is negative, if the index minus <code>howFar</code> is out of range,
   * or if the index index minus <code>howFar</code> is less than 
   * <code>getNumLetterHints()</code>.
   * @param index
   *   zero-based index of the character to be moved
   * @param howFar
   *   number of spaces to the left to move the character
   */
  public void moveLeft(int index, int howFar)
  {
	  //if the index is in range, and howFar is in range, rearrange the word
	 if(in_range(index, getNumLetterHints(), scrambledWord.length()) && (howFar > 0 && howFar < scrambledWord.length()))
	 { 
		 //substring will be the characters before the character at the index
		 String before = scrambledWord.substring(0, index-howFar); 
		 //substring will be the characters near the index
		 String mid = scrambledWord.substring(index-howFar, index); 
		 //substring will be the characters after the index
		 String after = scrambledWord.substring(index+1, scrambledWord.length()); 
		 scrambledWord = before + scrambledWord.charAt(index) + mid + after; 
	 }
	 else
	 { 
		 getScrambledWord(); 
	 }
	 
  }

  /**
   * Returns the number of letters at the beginning of the scrambled word whose
   * positions are currently fixed by previous calls to <code>letterHint()</code>.
   * @return
   *   number of fixed characters at the beginning of the scrambled word
   */
  public int getNumLetterHints()
  {
    return letterHintCount;
  }
  
  /**
   * Applies a hint by placing a correct character at the position
   * <code>p</code>, where <code>p = getNumLetterHints()</code> is the number
   * of characters that have already been placed in the correct position
   * by previous calls to this method.
   * Specifically, this method searches to the right starting at index <code>p</code>
   * until it finds the first occurrence of the correct character, 
   * and then swaps that character into position <code>p</code>.
   * After this method has been called n times, the first n letters
   * of the scrambled word are correct and are not moved again by any
   * subsequent method calls. 
   * <p>
   * For example: if the real word is "AARDVARK" and the scrambled word
   * is "DVRAKARA", then after one call to <code>doLetterHint</code>,
   * the scrambled word is "AVRDKARA"  (D is swapped with first A).
   * After two calls to <code>doLetterHint</code>, the scrambled word is "AARDKVRA"
   * (V is swapped with the second A).  After three calls to <code>doLetterHint</code>,
   * the scrambled word is still "AARDKVRA" (R is swapped with itself).  However
   * <code>getNumLetterHints</code> will now return 3, and the characters
   * at indices 0, 1, and 2 will be not be moved.
   */
  public void doLetterHint()
  {
	  if (letterHintCount > scrambledWord.length() - 1)
	  { 
		  getRealWord(); 
	  }
	  else { 
	  //create array to put scrambled word into 
	  char[] scrambledArray = new char[scrambledWord.length()];
	  
	  //add each character to the array.
	  for (int i = 0; i < scrambledArray.length; i += 1)
	  { 
		  scrambledArray[i] = scrambledWord.charAt(i);
	  }
	  
	  //get the real letter to be placed in the array
	  char letterSwap = realWord.charAt(letterHintCount); 
	  
	  //temporarily store the character in the array that is currently there and is going to be replaced 
	  char temp = scrambledArray[letterHintCount]; 
	  
	  //find an instance of letterSwap that does not include anything already placed 
	  int firstInstance = letterHintCount; 
	  for (int i = letterHintCount; i < scrambledArray.length; i += 1)
	  { 
		  if (scrambledArray[i] == letterSwap)
		  { 
			  firstInstance = i;  
		  }
		  
	  }
	  
	  //do the first swap. the character that is in letterHintCount will be replaced with the correct word
	  scrambledArray[letterHintCount] = letterSwap; 
	  
	  //do the second swap. the character in firstInstance will be replaced the the stored character 
	  scrambledArray[firstInstance] = temp;
	  
	  String temporaryString = ""; 
	  //put the array into the a string so it's useful to us. 
	  for (int i = 0; i < scrambledArray.length; i += 1)
	  { 
		  temporaryString = temporaryString + scrambledArray[i]; 
	  }
	  
	  //update scrambled word. 
	  scrambledWord = temporaryString; 
	  
	  //Increment letterHintCount
	  letterHintCount += 1;  
	  } 
  }
  
  /**
   * Rescrambles the non-fixed letters in the scrambled using the 
   * given permutation generator.  Letters to the left of the index
   * <code>getNumLetterHints()</code> are not moved. This method should
   * update the scrambled word with the value of
   * <pre>
   * WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen);
   * </pre>
   * )
   * @param gen
   *   permutation generator for scrambling the non-fixed letters
   */
  public void rescramble(PermutationGenerator gen)
  { 
    scrambledWord = WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen); 
  }

  /**
   * Determines whether the scrambled word is a valid rearrangement
   * of the real word (disregarding case).
   * @return
   *   true if it is possible to rearrange the characters in the
   *   scrambled word to obtain the real word
   */
  public boolean isSolutionPossible()
  {
	//if the scrambledWord is, for some reason, any larger or smaller than the actual word, automatically return false. 
	if (scrambledWord.length() > realWord.length()) 
	{ 
		return false; 
	}
	//saves the condition of scrambledWord and letterHintCount so they can be fixed every time doLetterHint() is called
	int tempCount = letterHintCount; 
	String temp = scrambledWord; 
	//call doLetterHint the length of the scrambledWord, and then call check solution
	for (int i = 0; i < scrambledWord.length(); i += 1)
	{ 
		doLetterHint(); 
	}
	//if after doing doLetterHint() the length of the word, the actual word and scrambled word are equivalent, return true, else, return false. 
	if (checkSolution(scrambledWord)) 
	{  
		//resets the state of letterHintCount and scrambledWord
		letterHintCount = tempCount; 
		scrambledWord = temp;
		return true; 	
	}
	else 
	{ 
		return false; 
	}
	
  }

  /**
   * Determines whether the current scrambled word is 
   * equal to the real word (disregarding case).
   * @return
   *   true if scrambled word and real word are the same,
   *   false otherwise
   */
  public boolean checkSolution()
  {
    if (realWord.equals(scrambledWord))
    { 
    	return true; 
    }
    else { 
    	return false;
    }
}
  
  /**
   * Determines whether the given string is 
   * equal to the real word (disregarding case).
   * Does not modify the scrambled word.
   * @return
   *   true if given word and real word are the same,
   *   false otherwise
   */
  public boolean checkSolution(String solution)
  {
    if(realWord.equalsIgnoreCase(solution))
    { 
    	return true; 
    }
    else
    { 
    	return false; 
    }
  }
}
