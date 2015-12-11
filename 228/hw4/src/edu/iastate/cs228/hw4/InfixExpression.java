package edu.iastate.cs228.hw4;

/**
 *  
 * @author Gabby Ortman
 *
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * This class represents an infix expression. It implements infix to postfix conversion using 
 * one stack, and evaluates the converted postfix expression.    
 *
 */

public class InfixExpression extends Expression 
{
	private String infixExpression;   		// the infix expression to convert		
	private boolean postfixReady = false;   // postfix already generated if true

	private PureStack<Operator> operatorStack; 	  // stack of operators 
	
	
	/**
	 * Constructor stores the input infix string, and initializes the operand stack and 
	 * the hash map.
	 * 
	 * @param st  input infix string. 
	 * @param varTbl  hash map storing all variables in the infix expression and their values. 
	 */
	public InfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		super(st, varTbl);  
		this.operatorStack = (PureStack<Operator>) new ArrayBasedStack(); 
		infixExpression = Expression.removeExtraSpaces(st); 
	}
	

	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public InfixExpression (String s)
	{
		super(s); 
		this.operatorStack = (PureStack<Operator>) new ArrayBasedStack(); 
		infixExpression = Expression.removeExtraSpaces(s); 
	}
	

	/**
	 * Outputs the infix expression according to the format in the project description.
	 * It first calls the method toStringHelper() from the class Expression.  
	 */
	@Override
	public String toString()
	{
		return infixExpression.replace("( ", "(").replace(" )", ")").trim();
	}
	
	
	/** 
	 * @return equivalent postfix expression, or  
	 * 
	 *         a null string if a call to postfix() inside the body (when postfixReady 
	 * 		   == false) throws an exception.
	 */
	public String postfixString() 
	{ 
		try 
		{ 
			postfix(); 
		}
		catch(Exception e)
		{ 
			return null; 
		}
		return postfixExpression.trim(); 
	}


	/**
	 * Resets the infix expression. 
	 * 
	 * @param st
	 */
	public void resetInfix (String st)
	{
		infixExpression = Expression.removeExtraSpaces(st); 
	}

	public void postfix() throws ExpressionFormatException
	{ 
		Scanner infixScan = new Scanner(infixExpression); 
		postfixExpression = ""; 
		evaluateRank(); 
		checkLeftParen(); 
		while(infixScan.hasNext())
		{ 
			String token = infixScan.next(); 
			if(isInt(token))
			{ 
				postfixExpression += token + " "; 
			}
			else if(isVariable(token.charAt(0)))
			{
				postfixExpression += token + " "; 
			}
			else if (isOperator(token.charAt(0)) || token.equals("("))
			{ 
				Operator currentOp = new Operator(token.charAt(0)); 
				while(!operatorStack.isEmpty() && (inputPrec(currentOp.getOp()) <= stackPrec(operatorStack.peek().getOp())))
				{ 
					Operator op = operatorStack.pop(); 
					postfixExpression += op.getOp() + " "; 
				}
				operatorStack.push(currentOp);
			}
			else if(token.equals(")"))
			{ 
				while(operatorStack.peek().getOp() != '(')
				{ 
					postfixExpression += operatorStack.pop().getOp() + " "; 
				}
//				if(operatorStack.peek().getOp() != '(')
//				{ 
//					infixScan.close();
//					throw new ExpressionFormatException("Missing '('"); 
//				}
//				else
//				{ 
//					operatorStack.pop();
//				}
				operatorStack.pop(); 
			}
			else
			{ 
				infixScan.close();
				throw new ExpressionFormatException("Invalid character"); 
			}
		}
		while(!operatorStack.isEmpty())
		{ 
			postfixExpression += operatorStack.pop().getOp() + " "; 
		}
		infixScan.close();
		postfixReady = true; 
	}
	/**                                                                 
	 * Converts infixexpression to an equivalent postfix string stored at postfixExpression.
	 * If postfixReady == false, the method scans the infixExpression, and does the following
	 * (for algorithm details refer to the relevant PowerPoint slides): 
	 * 
	 *     1. Skips a whitespace character.
	 *     2. Writes a scanned operand to postfixExpression.
	 *     3. If a scanned operator has a higher input precedence than the stack precedence of 
	 *        the top operator on the operatorStack, push it onto the stack.   
	 *     4. Otherwise, first calls outputHigherOrEqual() before pushing the scanned operator 
	 *        onto the stack. No push if the scanned operator is ). 
     *     5. Keeps track of the cumulative rank of the infix expression. 
     *     
     *  During the conversion, catches errors in the infixExpression by throwing 
     *  ExpressionFormatException with one of the following messages:
     *  
     *      -- "Operator expected" if the cumulative rank goes above 1;
     *      -- "Operand expected" if the rank goes below 0; 
     *      -- "Missing '('" if scanning a ‘)’ results in popping the stack empty with no '(';
     *      -- "Missing ')'" if a '(' is left unmatched on the stack at the end of the scan; 
     *      -- "Invalid character" if a scanned char is neither a digit nor an operator; 
     *  
     *  If an error is not one of the above types, throw the exception with a message you define.
     *      
     *  Sets postfixReady to true.  
	 */
	
	
	/**
	 * This function first calls postfix() to convert infixExpression into postfixExpression. Then 
	 * it creates a PostfixExpression object and calls its evaluate() method (which may throw  
	 * an exception).  It also passes any exception thrown by the evaluate() method of the 
	 * PostfixExpression object upward the chain. 
	 * 
	 * @return value of the infix expression 
	 * @throws UnassignedVariableException 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public int evaluate() throws ExpressionFormatException, UnassignedVariableException  
    {
		postfix(); 
		PostfixExpression postfixEval = new PostfixExpression(postfixExpression, varTable);
		int toReturn = postfixEval.evaluate(); 
		return toReturn; 
    }


//	/**
//	 * Pops the operator stack and output as long as the operator on the top of the stack has a 
//	 * stack precedence greater than or equal to the input precedence of the current operator op.  
//	 * Writes the popped operators to the string postfixExpression. 
//	 * 
//	 * If op is a ')', and the top of the stack is a '(', also pops '(' from the stack but does 
//	 * not write it to postfixExpression. 
//	 * 
//	 * @param op  current operator
//	 */
//	private void outputHigherOrEqual(Operator op)
//	{
//		// I'm not doing this
//	}
	
	/**
	 * Scans infixExpression from left to right, determining whether it is a valid infixExpression. 
	 * @throws ExpressionFormatException
	 */
	private void evaluateRank() throws ExpressionFormatException
	{ 
		int rank = 0; 
		Scanner infixScan = new Scanner(infixExpression); 
		while(infixScan.hasNext())
		{ 
			String token = infixScan.next();
			if(isInt(token))
			{ 
				rank += 1; 
			}
			else if(isVariable(token.charAt(0)))
			{ 
				rank += 1; 
			}
			else if(isParentheses(token.charAt(0)))
			{ 
				rank += 0; 
			}
			else if(isOperator(token.charAt(0)))
			{ 
				rank += -1; 
			}
			if(rank > 1)
			{ 
				infixScan.close();
				throw new ExpressionFormatException("Operator expected"); 
			}
			if(rank < 0)
			{ 
				infixScan.close();
				throw new ExpressionFormatException("Operand expected"); 
			}
		}
		infixScan.close(); 
	}
	
	/**
	 * Stupid method makes sure there's enough left parentheses. 
	 * I realize I could do this during stack eval but I don't care. 
	 * @throws ExpressionFormatException 
	 */
	private void checkLeftParen() throws ExpressionFormatException
	{ 
		int left = 0; 
		int right = 0; 
		for(int i = 0; i < infixExpression.length(); i++)
		{ 
			char currentChar = infixExpression.charAt(i); 
			if(isParentheses(currentChar))
			{ 
				if(currentChar == '(')
				{ 
					left += 1;
				}
				if(currentChar == ')')
				{ 
					right += 1; 
				}
				if(right > left)
				{ 
					throw new ExpressionFormatException("Missing '('"); 
				}
			}
		}
		if(left > right)
		{ 
			throw new ExpressionFormatException("Missing ')'"); 
		}
	}
	
	private int inputPrec(char ch)
	{ 
		Operator op = new Operator(ch); 
		return op.getInputPrec(ch); 
	}
	
	private int stackPrec(char ch)
	{ 
		Operator op = new Operator(ch); 
		return op.getStackPrec(ch); 
	}
	// other helper methods if needed
}
