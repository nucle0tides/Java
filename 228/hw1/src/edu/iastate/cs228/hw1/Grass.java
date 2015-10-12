package edu.iastate.cs228.hw1;

/**
 * Grass remains if more than rabbits in the neighborhood;
 * otherwise, it is eaten. 
 * 
 * @author Gabby Ortman - https://www.youtube.com/watch?v=RwdJQz48130
 *
 */
public class Grass extends Living 
{
	public Grass (World w, int r, int c) 
	{
		super(w, r, c); 
	}
	
	public State who()
	{
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits in the neighborhood. Rabbits may also 
	 * multiply fast enough to take over Grass. 
	 */
	public Living next(World wNew)
	{
		census(this.pop); 
		if(this.pop[RABBIT] >= this.pop[GRASS] * 3)
		{ 
			//empty, rabbits have eradicated the grasses
			return new Empty(wNew, this.row, this.column); 
		}
		else if(this.pop[RABBIT] >= 3)
		{ 
			//rabbits take over 
			return new Rabbit(wNew, this.row, this.column, 0); 
		}
		else 
		{ 
			//your ass is grass and I'm going to mow it 
			return new Grass(wNew, this.row, this.column); 
		}
	}
}
