/**
 * Model of an atom because Science is Cool™ 
 * Created for an assignment. 
 * @author Gabby Ortman
*/

public class Atom {
/* 
 * Instance variables 
 */
	private int protons; 
	private int neutrons; 
	private int electrons; 

/* 
 * Constructor
 */
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons) {
		protons = givenProtons; 
		neutrons = givenNeutrons;
		electrons = givenElectrons; 
	}
	
	
/*
 * METHODS
 */
	/** 
	 * @return total number of protons plus neutrons.  
	*/
	public int getAtomicMass()  { 
		int atomicMass = protons + neutrons; 
		return (atomicMass);
	}
	
	/** 
	 * @return the difference between the number of protons and electrons.  
	*/
	public int getAtomicCharge() { 
		int atomicCharge = protons - electrons; 
		return (atomicCharge); 
	}
	
	/** 
	 * decreases the number of protons by 2 and the number of neutrons by 2.  
	*/
	public void decay() { 
		protons = protons - 2; 
		neutrons = neutrons - 2; 
	}
}
