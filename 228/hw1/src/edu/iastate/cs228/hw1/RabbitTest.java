package edu.iastate.cs228.hw1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RabbitTest {
	private World world;  
	private World wNew; 

	@Before
	public void setUp()
	{ 
		world = new World(3); 
	}
	
	@Test
	public void rabbitDeath()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Rabbit(this.world, 1, 1, Living.RABBIT_MAX_AGE); 
		world.grid[1][2] = new Empty(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.EMPTY, returnType.who()); 
	}
	
	@Test
	public void noGrass()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Rabbit(this.world, 1, 1, Living.RABBIT_MAX_AGE-1); 
		world.grid[1][2] = new Empty(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.EMPTY, returnType.who());	
	}
	
	@Test
	public void foxTakeover()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Fox(this.world, 0, 1, Living.FOX_MAX_AGE); 
		world.grid[0][2] = new Fox(this.world, 0, 2, Living.FOX_MAX_AGE); 
		world.grid[1][0] = new Grass(this.world, 1, 0); 
		world.grid[1][1] = new Rabbit(this.world, 1, 1, Living.RABBIT_MAX_AGE-1); 
		world.grid[1][2] = new Empty(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Badger(this.world, 2, 1, Living.BADGER_MAX_AGE); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.FOX, returnType.who());
	}
	
	@Test
	public void badgerTakeover()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Badger(this.world, 1, 0, Living.BADGER_MAX_AGE); 
		world.grid[1][1] = new Rabbit(this.world, 1, 1, Living.RABBIT_MAX_AGE-1); 
		world.grid[1][2] = new Badger(this.world, 1, 2, Living.BADGER_MAX_AGE); 
		world.grid[2][0] = new Grass(this.world, 2, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Living returnType = world.grid[1][1].next(wNew); 
		Assert.assertEquals(State.BADGER, returnType.who());
	}
	
	@Test 
	public void happyBirthday()
	{ 
		world.grid[0][0] = new Empty(this.world, 0, 0); 
		world.grid[0][1] = new Empty(this.world, 0, 1); 
		world.grid[0][2] = new Empty(this.world, 0, 2); 
		world.grid[1][0] = new Empty(this.world, 1, 0); 
		world.grid[1][1] = new Rabbit(this.world, 1, 1, Living.RABBIT_MAX_AGE-1); 
		world.grid[1][2] = new Grass(this.world, 1, 2); 
		world.grid[2][0] = new Empty(this.world, 2, 0); 
		world.grid[2][1] = new Empty(this.world, 2, 1); 
		world.grid[2][2] = new Empty(this.world, 2, 2); 
		Animal returnType = (Animal)world.grid[1][1].next(wNew); 
		Assert.assertEquals(Living.RABBIT_MAX_AGE, returnType.myAge()); 
	}

}
