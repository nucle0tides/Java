package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert; 

import edu.iastate.cs228.hw1.Living;
import edu.iastate.cs228.hw1.World;

public class LivingTest {
	
	private World world; 
	private int[] pop; 
	
	@Before
	public void setUp() throws FileNotFoundException
	{ 
		world = new World("public1.txt"); 
		pop = new int[5]; 
	}
	
	@Test
	public void testCensus()
	{ 
		Living center = world.grid[0][0]; 
		center.census(pop); 
		int[] expected = new int[5]; 
		expected[0] = 1; 
		expected[1] = 0; 
		expected[2] = 2; 
		expected[3] = 1; 
		expected[4] = 0; 
		for(int i = 0; i < pop.length; i++)
		{
			Assert.assertEquals(expected[i], pop[i]);  
		}
	}
	
	@Test 
	public void testCensus1()
	{ 
		Living center = world.grid[1][1]; 
		center.census(pop);
		int[] expected = new int[5];
		expected[0] = 1; 
		expected[1] = 1; 
		expected[2] = 4; 
		expected[3] = 2; 
		expected[4] = 1; 
		for(int i = 0; i < pop.length; i++)
		{
			Assert.assertEquals(expected[i], pop[i]);
		}
	}
	
	@Test 
	public void testCensus2()
	{ 
		Living center = world.grid[2][2]; 
		center.census(pop);
		int[] expected = new int[5];
		expected[0] = 0; 
		expected[1] = 1; 
		expected[2] = 1; 
		expected[3] = 1; 
		expected[4] = 1; 
		for(int i = 0; i < pop.length; i++)
		{
			Assert.assertEquals(expected[i], pop[i]);
			//System.out.println(expected[i] + ", " + pop[i]); 
		}
	}
	
	@Test 
	public void testCensus3()
	{ 
		Living center = world.grid[1][2]; 
		center.census(pop);
		int[] expected = new int[5];
		expected[0] = 1; 
		expected[1] = 1; 
		expected[2] = 2; 
		expected[3] = 1; 
		expected[4] = 1; 
		for(int i = 0; i < pop.length; i++)
		{
			Assert.assertEquals(expected[i], pop[i]);
			//System.out.println(expected[i] + ", " + pop[i]); 
		}
	}
}
