package edu.iastate.cs228.hw1;

/**
 * 
 * Living refers to the life form occupying a square in a world grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 * 
 * @author Gabby Ortman
 *
 */
public abstract class Living 
{
	protected World world; // the world in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	protected int[] pop;   // holds the population
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	public Living(World world, int row, int column) 
	{ 
		this.world = world; 
		this.row = row; 
		this.column = column; 
		pop = new int[5]; 
	}
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a world. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[ ])
	{		
		World neighborhood = getNeighborhood(this.world, row, column); 
		//for each row 
		for (int i = 0; i < 3; i++)
		{ 
			//for each column
			for (int j = 0; j < 3; j++)
			{ 
				if (neighborhood.grid[i][j] != null) // I made cells outside of the original world null. 
				{
					if (neighborhood.grid[i][j].who() == State.BADGER)
					{ 
						population[0] = population[0] + 1; 
					}
					else if (neighborhood.grid[i][j].who() == State.EMPTY)
					{ 
						population[1] = population[1] + 1; 
					}
					else if (neighborhood.grid[i][j].who() == State.FOX)
					{ 
						population[2] = population[2] + 1; 
					}
					else if (neighborhood.grid[i][j].who() == State.GRASS)
					{ 
						population[3] = population[3] + 1; 
					}
					else if (neighborhood.grid[i][j].who() == State.RABBIT)
					{ 
						population[4] = population[4] + 1; 
					}
				}
			}
		}
	}
	
	/**
	 * Method to stringify the living object. To be overridden in Animal to include age. 
	 * @return a String that represents the first letter of the living object
	 */
	protected String livingToString() 
	{ 
		String toReturn = this.who().toString().charAt(0) + "";
		return toReturn; 
	}
	
	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  wNew  world of the next cycle
	 * @return Living 
	 */
	public abstract Living next(World wNew); 
	
	/**
	 * Method that returns the 3X3 neighborhood surrounding the cell given at A[centerRow][centerCol]
	 * Adapted from m 227 mini assignment
	 * @param world - world to grab the neighborhood from 
	 * @param centerRow - row the Living object is at 
	 * @param centerCol - column the Living object is at 
	 * @return
	 */
	private World getNeighborhood(World world, int centerRow, int centerCol) 
	{ 
		int size = 3; 
		World neighborhood = new World(size); 
		int rows = this.world.grid.length; 
		int cols = this.world.grid[0].length; 
		//for each row 
		for (int i = 0; i < size; i++)
		{ 
			//for each column 
			for (int j = 0; j < size; j++)
			{ 
				//hard code 1 because neighborhood will always be a 3X3.........................for now
				int currRow = centerRow - 1 + i; 
				int currCol = centerCol - 1 + j; 
				
				//if it's within the array
				if (inArray(currRow, currCol, rows, cols))
				{ 
					neighborhood.grid[i][j] = world.grid[currRow][currCol]; 
				}
				//if a cell is null, I can just ignore it when calling census(). 
				else 
				{ 
					neighborhood.grid[i][j] = null; 
				}	
			}
		}
		return neighborhood; 
	}
	
	/**
	 * Method to see if the value at the given row and column are within the array 
	 * Copied directly from my 227 mini assignment from last semester. 
	 * @param row - row the Living object is at 
	 * @param col - column the Living object is at 
	 * @param rows - number of rows in the neighborhood
	 * @param cols - number of columns in the neighborhood 
	 * @return
	 */
	private static boolean inArray(int row, int col, int rows, int cols)
	{ 
		return (0 <= row && row < rows && 0 <= col && col < cols); 
	} 

}
