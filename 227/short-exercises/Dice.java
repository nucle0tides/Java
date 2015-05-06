package smallExercises;
import java.util.*;
//A poor dice rolling simulator. It prints out a random number from 1-6.

public class Dice {
	public static void main(String[] args){ 
		Random generator = new Random(); 
		int value = generator.nextInt(6) + 1; 
		//okay, but what about a range that doesn't include 0? 
		System.out.println("The random number is " + value + "."); 
	}
}
