package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.io.File; 

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import edu.iastate.cs228.hw1.Animal;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.World;

/**
 * JUnit Tests for World
 * @author Gabby Ortman
 *
 */
public class WorldTest {
	public static void main(String args[]) throws FileNotFoundException { 
		String file = "S:\\cs228\\hw1\\public1.txt"; 
		World world = new World(file);
		System.out.println(world.toString()); 
		//World world1 = new World(50); 
		//world1.randomInit();
		//System.out.println(world1.toString());
		//world1.write("GIANT.txt"); 
	}
		private World world;
		private World world1;
		private World world2;
		private World world4;
		private World world5; 
	
	@Before
	public void setUp() throws FileNotFoundException
	{ 
		//World's with a text file accompanying them 
		world  = new World("public1.txt"); 
		world1 = new World("public2.txt"); 
		world2 = new World("public3.txt"); 

	}
	
	@Test 
	public void testToStringSuccess()
	{ 
		String string = new String("G  B0 F0 \nF0 F0 R0 \nF0 E  G  "); 
		String worldString = world.toString(); 
		Assert.assertEquals(string, worldString); 
		
		String string1 = new String("F0 E  E  F0 E  E  \nB0 F0 B0 R0 G  R0 \nR0 E  R0 B0 B0 G  \nB0 E  E  R0 F0 E  \nB0 E  E  G  E  R0 \nG  G  E  B0 R0 E  "); 
		String worldString1 = world1.toString(); 
		Assert.assertEquals(string1, worldString1);
		
		//nice formatting, Gabby! 
		String string2 = new String("B0 E  B0 E  B0 R0 E  R3 E  G  \nG  E  B0 E  F0 R0 E  B4 G  G  \nG  G  G  G  E  E  R0 E  G  G  \nF0 E  G  G  E  R0 R0 B0 B0 G  \nF0 F1 E  E  E  E  E  E  B0 E  \nG  G  R1 R0 R0 R0 R0 B0 B0 E  \nE  G  R0 R1 R2 R2 G  E  G  G  \nB0 B0 G  R0 R0 R0 G  B0 E  G  \nE  G  G  F4 R2 R0 E  G  G  G  \nG  G  E  E  E  G  G  G  G  G  "); 
		String worldString2 = world2.toString(); 
		Assert.assertEquals(string2, worldString2); 
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testUnitWorldException()
	{ 
		//a world passed a 0 or negative number should throw n 
		//illegal argument exception 
		world4 = new World(-5); 
	}
	
	@Test
	public void testUninitWorldCellsNull()
	{ 
		world4 = new World(2); 
		//cells should be null
		for(int i = 0; i < world4.getWidth(); i++)
		{ 
			for(int j = 0; j < world4.getWidth(); j++)
			{ 
				Assert.assertNull(world4.grid[i][j]); 
			}
		}
	}
	
	@Test 
	public void testRandomWorldAge()
	{ 
		world4 = new World(2); 
		world4.randomInit();
		for(int i = 0; i < world4.getWidth(); i++)
		{ 
			for(int j = 0; j < world4.getWidth(); j++)
			{ 
				if(world4.grid[i][j].who() == State.BADGER || world4.grid[i][j].who() == State.FOX || world4.grid[i][j].who() == State.RABBIT)
				{ 
					//Animal created with RandomInit() should have an age of 0 
					Animal animal = (Animal)world4.grid[i][j]; 
					Assert.assertEquals(0, animal.myAge());
				}
			}
		}
	}
	
	@Test 
	public void testRandomWorldInit()
	{ 
		world4 = new World(2); 
		world4.randomInit();
		//after random initialization, cells should no longer be null. 
		for(int i = 0; i < world4.getWidth(); i++)
		{ 
			for(int j = 0; j < world4.getWidth(); j++)
			{ 
				Assert.assertNotNull(world4.grid[i][j]); 
			}
		}
		
		world5 = new World(100); 
		world5.randomInit(); 
		
		for(int i = 0; i < world4.getWidth(); i++)
		{ 
			for(int j = 0; j < world4.getWidth(); j++)
			{ 
				Assert.assertNotNull(world5.grid[i][j]); 
			}
		}
	}
	
	@Test 
	public void testParse()
	{ 
		String[][] fakeWorld = new String[3][3]; 
		fakeWorld[0][0] = "G";
		fakeWorld[0][1] = "B0";
		fakeWorld[0][2] = "F0";
		fakeWorld[1][0] = "F0";
		fakeWorld[1][1] = "F0"; 
		fakeWorld[1][2] = "R0"; 
		fakeWorld[2][0] = "F0"; 
		fakeWorld[2][1] = "E"; 
		fakeWorld[2][2] = "G"; 
		for(int i = 0; i < world.getWidth(); i++)
		{ 
			for(int j = 0; j < world.getWidth(); j++)
			{ 
				Assert.assertEquals(fakeWorld[i][j], world.grid[i][j].livingToString());
			}
		}
	}
	
	@Test 
	public void testWrite() throws FileNotFoundException 
	{ 
		//test that write() actually writes out to a file. Since we know that parsing and converting to string is fine
		//we shouldn't have to do anything fancy here 
		world.write("test.txt");
		File file = new File("test.txt"); 
		Assert.assertTrue(file.exists()); 
		
		
	}
}
