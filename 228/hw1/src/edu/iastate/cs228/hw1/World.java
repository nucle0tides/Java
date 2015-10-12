package edu.iastate.cs228.hw1;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The world is represented as a square grid of size width X width. 
 * @author Gabby Ortman: fine, fresh, fatigued. 
 *
 */
public class World 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public World(String inputFileName) throws FileNotFoundException
	{	
		try 
		{ 
			this.grid = parseFile(inputFileName); 
		}
		catch(FileNotFoundException f)
		{ 
			System.out.println("It looks like the file you are trying to access does not exist!"); 
		}
	}	
	
	/**
	 * Constructor that builds a w X w grid without initializing it. 
	 * @param width of the grid 
	 */
	public World(int w)
	{
		width = w; 
		if (w <= 0)
		{ 
			throw new IllegalArgumentException(); 
		}
		else 
		{ 
			this.grid = new Living[width][width];
		}  
	}
	
	
	public int getWidth()
	{  
		return width; 
	}
	
	/**
	 * Initialize the world by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		this.grid = randomWorld();    
	}
	
	
	/**
	 * Output the world grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		String returnString = ""; 
		try
		{ 
			for (int i = 0; i < this.grid.length ; i++) 
			{
				for (int j = 0; j < this.grid.length; j++)
				{  
					if (grid[i][j].who() == State.RABBIT || grid[i][j].who() == State.FOX || grid[i][j].who() == State.BADGER) { 
						returnString = returnString + grid[i][j].livingToString() + " ";
					}
					else 
					{ 
						returnString = returnString + grid[i][j].livingToString() + "  "; 
					}
				}
				returnString = returnString + "\n"; 
			}
		}
		catch(NullPointerException e)
		{ 
			System.out.println("It looks like the world was not initialized correctly!"); 
		}
		//uh, remove last newline 
		returnString = returnString.substring(0, returnString.length()-1); 
		return returnString; 
	}
	

	/**
	 * Write the world grid to an output file.  Also useful for saving a randomly 
	 * generated world for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		PrintWriter fileWriter = new PrintWriter(outputFileName);
		fileWriter.write(this.toString());
		fileWriter.close();
	}	
	
	/**
	 * 
	 * @return
	 */
	public World getWorld() 
	{ 
		return this; 
	}
	
	/**
	 * Private method to populate grid using an input file. To be called by World constructor
	 * @param inputFile
	 * @return
	 * @throws FileNotFoundException
	 */
	private Living[][] parseFile(String inputFile) throws FileNotFoundException 
	{
		File fileToParse = new File(inputFile); 
		if(fileToParse.isFile())
		{ 
			Scanner fileParser = new Scanner(fileToParse); 
	        
	        while (fileParser.hasNextLine()) 
	        { 
	            width += 1; 
	            fileParser.nextLine(); 
	        }
	        fileParser.close();
	        fileParser = new Scanner(fileToParse); 
	        
	        Living newLiving[][] = new Living[width][width]; 
	        
	        for(int i = 0; i < width; i++)
	        {	
	            String current = fileParser.nextLine(); 
	            Scanner lineParse = new Scanner(current); 
	            for (int j = 0; j < width; j++)
	            { 
	            	int age = 0; 
	                String animalToken = lineParse.next();
	                
	                //if there's two characters, then we know it's an animal 
	                //since we know it's an animal, we should get the age! 
	                if (animalToken.length() == 2) 
	                { 
	                	age = Integer.parseInt(animalToken.substring(1)); 
	                }
	                
	                if (animalToken.charAt(0) == 'F') 
	                {  
	                    newLiving[i][j] = new Fox(this, i, j, age); 
	                }
	                if (animalToken.charAt(0) == 'B')
	                { 
	                    newLiving[i][j] = new Badger(this, i, j, age); 
	                }
	                if (animalToken.charAt(0) == 'R')
	                {  
	                    newLiving[i][j] = new Rabbit(this, i, j, age);  
	                }
	                if (animalToken.charAt(0) == 'G')
	                {   
	                    newLiving[i][j] = new Grass(this, i, j); ; 
	                }
	                if (animalToken.charAt(0) == 'E')
	                { 
	                    newLiving[i][j] = new Empty(this, i, j); 
	                }
	            }
		    lineParse.close();     
	        }
	        
	        fileParser.close(); 
	        return newLiving; 
		}
		else 
		{ 
			throw new FileNotFoundException(); 
		}  
	}
	
	private Living[][] randomWorld()
	{ 
		Random generator = new Random(); 
		
		//every tender newborn animal will have an age of 0 
		int newbornAge = 0; 
		 
		Living[][] newWorld = new Living[width][width]; 
		for (int i = 0; i < width; i++)
		{ 
			for (int j = 0; j < width; j++)
			{ 
				//select int from 0-4
				int cell = generator.nextInt(5);
				if(cell == State.BADGER.ordinal()) 
				{ 
					newWorld[i][j] = new Badger(this, i, j, newbornAge); 
				}
				if (cell == State.EMPTY.ordinal())
				{ 
					newWorld[i][j] = new Empty(this, i, j); 
				}
				if (cell == State.FOX.ordinal())
				{ 
					newWorld[i][j] = new Fox(this, i, j, newbornAge);
				}
				if (cell == State.GRASS.ordinal())
				{ 
					newWorld[i][j] = new Grass(this, i, j); 
				}
				if (cell == State.RABBIT.ordinal())
				{ 
					newWorld[i][j] = new Rabbit(this, i, j, newbornAge);
				}				
			}
		}
		return newWorld; 
	}
}
