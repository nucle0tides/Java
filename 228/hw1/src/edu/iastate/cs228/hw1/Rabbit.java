package edu.iastate.cs228.hw1;

/**
 * A rabbit eats grass and lives no more than three years.
 * @author Gabby Ortman  
 */
public class Rabbit extends Animal 
{	
	
	/**
	 * Creates a Rabbit object.
	 * @param w: world  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (World w, int r, int c, int a) 
	{
		super(w, r, c, a); 

	}
		
	// Rabbit occupies the square.
	public State who()
	{ 
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger, or it is eaten if there are as many 
	 * foxes and badgers in the neighborhood.  
	 * @param wNew     world of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(World wNew)
	{
		census(super.pop); 
		if(this.myAge() == Living.RABBIT_MAX_AGE)
		{ 
			return new Empty(wNew, this.row, this.column); 
		}
		else if(this.pop[GRASS] == 0)
		{ 
			//rabbit dies 
			return new Empty(wNew, this.row, this.column); 
		}
		else if((this.pop[BADGER] + pop[FOX]) >= pop[RABBIT] && (pop[FOX] > pop[BADGER]))
		{ 
			//fox
			return new Fox(wNew, this.row, this.column, 0); 
		}
		else if(this.pop[BADGER] > this.pop[RABBIT])
		{ 
			//badger 
			return new Badger(wNew, this.row, this.column, 0); 
		}
		else 
		{ 
			return new Rabbit(wNew, this.row, this.column, this.myAge()+1); 
		}
	}
}
