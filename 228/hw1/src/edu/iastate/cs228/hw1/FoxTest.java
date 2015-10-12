package edu.iastate.cs228.hw1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoxTest {
	
	private World world;  
	private World wNew; 

	@Before
	public void setUp()
	{ 
		world = new World(3); 
	}
	
	@Test
	public void foxDeath()
	{
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Fox(this.world, 1, 1, Living.FOX_MAX_AGE); 
		world.grid[1][2] = new Empty(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.EMPTY, returnType.who()); 
	}
	
	@Test
	public void badgerTakeover()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Fox(this.world, 1, 1, Living.FOX_MAX_AGE - 1); 
		world.grid[1][2] = new Empty(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Badger(this.world, 2, 1, Living.BADGER_MAX_AGE); 
		world.grid[2][2] = new Badger(this.world, 2, 2, Living.BADGER_MAX_AGE); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.BADGER, returnType.who()); 
	}
	
	@Test
	public void greatRabbitWar()
	{ 
		//it actually returns empty 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Fox(this.world, 1, 1, Living.FOX_MAX_AGE - 1); 
		world.grid[1][2] = new Rabbit(this.world, 1, 2, Living.RABBIT_MAX_AGE); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Grass(this.world, 2, 1); 
		world.grid[2][2] = new Badger(this.world, 2, 2, Living.BADGER_MAX_AGE); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.EMPTY, returnType.who()); 
	}
	
	@Test
	public void happyBirthday()
	{ 
		//the third case of the test doesn't really make it such that the fox will ever grow up
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Fox(this.world, 1, 1, 0); 
		world.grid[1][2] = new Rabbit(this.world, 1, 2, 0); 
		world.grid[2][0] = new Rabbit(this.world, 2, 0, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Animal returnType = (Animal)world.grid[1][1].next(wNew); 
		Assert.assertEquals(1, returnType.myAge()); 
	}

}
