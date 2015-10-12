package edu.iastate.cs228.hw1;

/**
 * A fox eats rabbits and competes against a badger. 
 * @author Gabby Ortman
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (World w, int r, int c, int a) 
	{
		super(w, r, c, a);
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew)
	{
		census(super.pop); 
		if (this.myAge() == Living.FOX_MAX_AGE)
		{ 
			//fox dies, empty 
			return new Empty(wNew, this.row, this.column); 
		}
		else if(this.pop[BADGER] > this.pop[FOX])
		{ 
			//badgers take over foxes 
			return new Badger(wNew, this.row, this.column, 0); 
		}
		else if(this.pop[BADGER] + this.pop[FOX] > this.pop[RABBIT])
		{ 
			//empty, the great Rabbit War
			return new Empty(wNew, this.row, this.column); 
		}
		else { 
			return new Fox(wNew, this.row, this.column, this.myAge()+1); 
		}
	}
}
