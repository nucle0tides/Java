package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 * 
 * The PredatorPrey class performs the predator-prey simulation over a grid world 
 * with squares occupied by badgers, foxes, rabbits, grass, or none. 
 * @author Gabby Ortman
 *
 */
public class PredatorPrey 
{
	/**
	 * Update the new world from the old world in one cycle. 
	 * @param wOld  old world
	 * @param wNew  new world 
	 */
	public static void updateWorld(World wOld, World wNew)
	{
		for(int i = 0; i < wOld.getWidth(); i++)
		{ 
			for(int j = 0; j < wOld.getWidth(); j++)
			{ 
				wNew.grid[i][j] = wOld.grid[i][j].next(wNew); 
			}
		}
	}
	
	/**
	 * Repeatedly generates worlds either randomly or from reading files. 
	 * Over each world, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		Scanner userInput = new Scanner(System.in); 
		//if user input is 2
		String fileName = ""; 
		//if user input is 1
		int wWidth = 0; 
		//
		int trial = 1; 
		//
		int cycles = 0; 
		// will be false when user input is anything other than 1 or 2
		boolean simulationLoop = true;
		
		World givenWorld; 
		World finalWorld; 
		
		//while simulation should still be occurring 
		while(simulationLoop)
		{ 
			System.out.println("The Predator-Prey Simulator"); 
			System.out.println("keys: 1 (random world) 2 (file input) 3(exit)"); 
			int userChoice = userInput.nextInt(); 
			if(userChoice == 1)
			{ 
				System.out.println("Trial: " + trial);
				System.out.println("Random world");
				System.out.print("Enter grid width: "); 
				wWidth = userInput.nextInt(); 
				//create world
				givenWorld = new World(wWidth); 
				givenWorld.randomInit();
				//System.out.println(givenWorld.toString()); 
				
				System.out.print("Enter number of cycles: ");
				cycles = userInput.nextInt(); 
				finalWorld = simulateNextWorld(givenWorld, cycles); 
				
				System.out.println("Initial world: \n" + givenWorld.toString()); 
				System.out.println("Final world: \n" + finalWorld.toString()); 
				trial += 1;
			}
			else if (userChoice == 2)
			{ 
				System.out.println("Trial: " + trial);
				System.out.println("World input from a file");
				System.out.print("File name: "); 
				fileName = userInput.next(); 
				//create world
				givenWorld = new World(fileName); 
				//System.out.println(givenWorld.toString()); 
				System.out.print("Enter number of cycles: ");
				cycles = userInput.nextInt(); 
				System.out.println("Initial world: \n" + givenWorld.toString());
				finalWorld = simulateNextWorld(givenWorld, cycles);  
				System.out.println("Final world: \n" + finalWorld.toString()); 
				trial += 1; 
			}
			//any other number was entered 
			else 
			{ 
				userInput.close();
				simulationLoop = false; 
			}
			
		} 
	}
	
	/**
	 * Helper method to actually simulate the world. 
	 * @param world - world to act on 
	 * @param cycles - number of cycles to simulate 
	 * @return - the successfully simulated world 
	 */
	private static World simulateNextWorld(World world, int cycles)
	{ 
		World oddWorld = new World(world.getWidth());
		World evenWorld = new World(world.getWidth());
		
		updateWorld(world, oddWorld);
		
		for (int i=1; i < cycles; ++i){
			
			if (i%2==0){
				updateWorld(evenWorld, oddWorld);
			} else {
				updateWorld(oddWorld, evenWorld);
			} 
		}
		
		if(cycles%2==0)
			return evenWorld;
		else
			return oddWorld;
	}
}
