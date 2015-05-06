package smallExercises;

import java.util.Scanner;

public class isEven 
{
	public static void main(String[] args) 
	{ 
		Scanner numInput = new Scanner(System.in); 
		
		System.out.print("Please enter a number: "); 
		int num = numInput.nextInt(); 
			if (num % 2 == 0) 
			{ 
				System.out.println("This number is even."); 
			}
			else 
			{ 
				System.out.println("This number is odd."); 
			}
		numInput.close(); 
	}
}
