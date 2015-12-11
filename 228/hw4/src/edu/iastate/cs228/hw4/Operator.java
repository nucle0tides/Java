package edu.iastate.cs228.hw4;

/**
 *  
 * @author Gabby Ortman, fine, fresh, fatigued. 
 *
 */

/**
 * 
 * This class represents operators '+', '-', '*', '/', '%', '^', '(', and ')'.  
 * Every operator has an input precedence, a stack precedence, and a rank, as specified 
 * in the table below. 
 * 
 *   operator       input precedence        stack precedence       rank
 *   ___________________________________________________________________ 
 *   +  -                   1                        1              -1
 *   *  /  %                2                        2              -1
 *   ^                      4                        3              -1
 *   (                      5                       -1               0
 *   )                      0                        0               0 
 *
 */


import java.lang.Comparable; 

public class Operator implements Comparable<Operator>
{
	public char operator; 	      // operator
	
	private	int inputPrecedence;  // input precedence of operator in the range [0, 5]
	private	int stackPrecedence;  // stack precedence of operator in the range [-1, 3]
	private int rank; 			  // rank of operator
	/**
	 * Constructor 
	 * @param ch
	 */
	public Operator(char ch) 
	{
		this.operator = ch; 
		assignOperator(ch); 
	}
	

	/**
	 * Returns 1, 0, -1 if the stackPrecedence of this operator is greater than, equal to, 
	 * or less than the inputPrecedence of the parameter operator op. It's for determining 
	 * whether this operator on the stack should be output before pushing op onto the stack.
	 */
	@Override
	public int compareTo(Operator op)
	{ 	
		if(stackPrecedence > inputPrecedence) return 1; 
		else if (stackPrecedence < inputPrecedence) return -1; 
		else return 0; 
	} 


	/**
	 * 
	 * @return char Returns the operator character.  
	 */
	public char getOp()   
	{
	   return operator; 
	}
	
	/**
	 * Set up instance variables and such 
	 * @param c
	 */
	private void assignOperator(char ch)
	{ 
		if(ch == '+' || ch == '-')
		{ 
			this.inputPrecedence = 1; 
			this.stackPrecedence = 1; 
			this.rank = -1; 
		}
		if(ch == '*' || ch == '/' || ch == '%')
		{ 
			this.inputPrecedence = 2; 
			this.stackPrecedence = 2; 
			this.rank = -1; 
		}
		if(ch == '^')
		{ 
			this.inputPrecedence = 4; 
			this.stackPrecedence = 3; 
			this.rank = 0; 
		}
		if(ch == '(')
		{ 
			this.inputPrecedence = 5; 
			this.stackPrecedence = -1; 
			this.rank = 0; 
		}
		if(ch == ')')
		{ 
			this.inputPrecedence = 0; 
			this.stackPrecedence = 0; 
			this.rank = 0; 
		}
	}
	
	/**
	 * 
	 * @param ch
	 */
	protected int getInputPrec(char ch)
	{ 
		return inputPrecedence; 
	}
	
	/**
	 * 
	 * @param ch
	 * @return
	 */
	protected int getStackPrec(char ch)
	{ 
		return stackPrecedence; 
	}

}
