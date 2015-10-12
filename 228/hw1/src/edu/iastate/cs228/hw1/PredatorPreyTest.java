package edu.iastate.cs228.hw1;

import edu.iastate.cs228.hw1.PredatorPrey; 

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PredatorPreyTest {
	
	World world; 
	World world1; 
	World world2; 
	World world3; 
	@Before
	public void setUp() throws FileNotFoundException
	{ 
		world = new World("public1.txt"); 
		world1 = new World("public1-5cycles.txt"); 
		world2 = new World("public2.txt"); 
		world3 = new World("public2-8cycles.txt"); 
	}
	
	@Test 
	public void test1()
	{ 
		World givenWorld = world; 
		World finalWorld = new World(givenWorld.getWidth()); 
		
		finalWorld = simulateNextWorld(givenWorld, 5);
		Assert.assertEquals(world1.toString(), finalWorld.toString());
		
	}
	
	@Test 
	public void test2()
	{ 
		World givenWorld = world2; 
		World finalWorld = new World(givenWorld.getWidth()); 
		
		finalWorld = simulateNextWorld(givenWorld, 8);
		Assert.assertEquals(world3.toString(), finalWorld.toString());
	}
	
	
	/**
	 * Helper method to actually simulate the world. 
	 * @param world
	 * @param cycles
	 * @return
	 */
	private static World simulateNextWorld(World world, int cycles)
	{ 
		World oddWorld = new World(world.getWidth());
		World evenWorld = new World(world.getWidth());
		
		PredatorPrey.updateWorld(world, oddWorld);
		
		for (int i=1; i < cycles; ++i){
			
			if (i%2==0){
				PredatorPrey.updateWorld(evenWorld, oddWorld);
			} else {
				PredatorPrey.updateWorld(oddWorld, evenWorld);
			} 
		}
		
		if(cycles%2==0)
			return evenWorld;
		else
			return oddWorld;
		
	}
}
