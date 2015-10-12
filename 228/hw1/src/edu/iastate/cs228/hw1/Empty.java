package edu.iastate.cs228.hw1;

/** 
 * Empty squares are competed by various forms of life.
 * @author Gabby Ortman
 */
public class Empty extends Living 
{
	
	public Empty (World w, int r, int c) 
	{
		super(w, r, c);  
	}
	
	public State who()
	{
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or 
	 * remain empty. 
	 * @param wNew     world of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(World wNew)
	{
//		for reference 
//		BADGER = 0; 
//		EMPTY = 1; 
//		FOX = 2; 
//		GRASS = 3; 
//		RABBIT = 4;
		census(this.pop); 
		if(this.pop[RABBIT] > 1)
		{ 
			return new Rabbit(wNew, this.row, this.column, 0); 
		}
		else if(this.pop[FOX] > 1)
		{ 
			return new Fox(wNew, this.row, this.column, 0); 
		}
		else if(this.pop[BADGER] > 1)
		{ 
			return new Badger(wNew, this.row, this.column, 0); 
		}
		else if(this.pop[GRASS] >= 1)
		{ 
			return new Grass(wNew, this.row, this.column); 
		}
		else 
		{ 
			return new Empty(wNew, this.row, this.column); 
		}
		
	}
}
