package edu.iastate.cs228.hw1;

/**
 * A badger eats a rabbit and competes against a fox.
 * @author Gabby Ortman: stole the Krabby Patty secret formula. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (World w, int r, int c, int a) 
	{
		super(w, r, c, a);
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, from isolation and attack by a group of foxes. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew)
	{
		census(this.pop); 
		if (this.myAge() == Living.BADGER_MAX_AGE)
		{ 
			return new Empty(wNew, this.row, this.column); 
		}
		else if (this.pop[BADGER] == 1 && this.pop[FOX] > 1)
		{ 
			return new Fox(wNew, this.row, this.column, 0); 
		}
		else if ((this.pop[BADGER] + this.pop[FOX]) > this.pop[RABBIT])
		{ 
			return new Empty(wNew, this.row, this.column); 
		}
		else 
		{ 
			return new Badger(wNew, this.row, this.column, this.myAge()+1); 
		}  
	}
}
