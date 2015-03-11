/**
 * A utility class dealing with tally marks and stars. 
 * An opportunity to get used to using the Scanner, loops, and other fun things. 
 * More information on this assignment can be found: http://www.cs.iastate.edu/~cs227/homework/mini2/doc/mini2/TallyString.html
 */
package mini2;

import java.util.Scanner;

public class TallyString {	
	public static final char LINE = '|'; 
	public static final char STAR = '*'; 
	public static final int STAR_VALUE = 5; 

	public static int evaluateGroup(String group)
	{
		int total = 0; 
		if (!isValidGroup(group))
		{ 
			total = -1; 
		}
		else 
		{ 
			for (int i = 0; i < group.length(); i += 1)
			{ 
				if (group.charAt(i) == STAR)
				{ 
					total += STAR_VALUE; 
				}
				if (group.charAt(i) == LINE)
				{ 
					total += 1; 
				}
			}
		} 
		return total; 
	}
	
	public static int evaluateString(String s)
	{
		Scanner stringParse = new Scanner(s); 
//		String tallyGroup = stringParse.next(); 
		int total = 0; 
		if (stringParse.hasNext()){ 
			while (stringParse.hasNext())
			{ 
				String tallyGroup = stringParse.next();
				if (isValidGroup(tallyGroup))
				{ 
					total = (total * 10) + evaluateGroup(tallyGroup); 
				}
				else 
				{
					stringParse.close();
					return total = -1; 
				}
				
			}
		} 
		else { 
			total = -1; 
		}
		stringParse.close();
		return total; 
	}
	
	public static boolean isValidGroup(String s)
	{
		boolean answer = true; 
		
		if (s.equals("0"))
		{ 
			answer = true; 
			return answer; 
		} 
		
		for (int index = 0; index < s.length(); index +=1)
		{  
			char x = s.charAt(index); 
			if (x == LINE || x == STAR)
			{ 
				 answer = true; 
			}
			else 
			{ 
				answer = false; 
				return answer; 
			}
		} 
		return answer; 
	}
	
	public static String makeGroup(int givenValue)
	{
		String result = ""; 
		int numStars = givenValue / STAR_VALUE; 
		int numLines = givenValue % STAR_VALUE; 
		if (givenValue < 0){
			return null; 
		}
		if (givenValue == 0){ 
			return "0"; 
		}
		for (int i = 0; i < numStars; i += 1)
		{ 
			result += STAR; 
		}
		for (int i = 0; i < numLines; i +=1)
		{ 
			result += LINE; 
		}
		return result; 
	}
	
	public static String makeNormalizedString(int givenValue)
	{
		if (givenValue < 0)
		{ 
			return null; 
		}
		
		if (givenValue == 0)
		{ 
			return "0"; 
		}
		
		String result = "";
		int value = givenValue; 
		while (value > 0)
		{ 
			int makeValue = value % 10; 
			value = value / 10; 
			String makeString = makeGroup(makeValue); 
			result = makeString + " " + result; 
		}
		//using substring to get rid of an extra space at the end. 
		return result.substring(0, result.length()-1);  
	}
}
