package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert; 

public class AnimalTest {

	Animal testAnimal;
	Animal testAnimal1; 
	World world; 
	World world1; 
	
	@Before
	public void setUp() throws FileNotFoundException
	{ 
		world = new World("public1.txt"); 
		world1 = new World("public2-8cycles.txt"); 
		testAnimal = (Animal)world.grid[1][1]; 
		testAnimal1 = (Animal)world1.grid[1][2]; 
	}
	
	@Test
	public void myAge()
	{ 
		Assert.assertEquals(0, testAnimal.myAge());
		Assert.assertEquals(6, testAnimal1.myAge()); 
	}
	
}
