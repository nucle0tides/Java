package edu.iastate.cs228.hw1;

/**
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 * @author Gabby Ortman, *insert witty phrase here*. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 
	
	public Animal(World w, int r, int c, int age)
	{ 
		//Animal inherits from Living
		super(w, r, c); 
		this.age = age;  
	}
	
	/**
	 * Method to stringify this cute little Animal. This time it includes the age. 
	 * @return a String that represents the first letter of the animal and its age. 
	 */
	@Override
	public String livingToString() 
	{ 
		String toReturn = this.who().toString().charAt(0) + ""; 
		toReturn = toReturn + myAge() + ""; 
		return toReturn; 
	}
	
	@Override
	/**
	 * 
	 * @return age of the animal 
	 */
	public int myAge()
	{
		return age;  
	}
}
