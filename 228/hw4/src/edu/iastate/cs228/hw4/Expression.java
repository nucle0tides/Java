package edu.iastate.cs228.hw4;

import java.util.HashMap;

public abstract class Expression 
{
	protected String postfixExpression; 		
	protected HashMap<Character, Integer> varTable; // hash map to store variables in the 

	
	protected Expression()
	{
		// no implementation needed 
		// removable when you are done
	}
	
	
	/**
	 * Initialization with a provided hash map. 
	 * 
	 * @param varTbl
	 */
	protected Expression(String st, HashMap<Character, Integer> varTbl)
	{
		setVarTable(varTbl); 
		postfixExpression = ""; 
	}
	
	/**
	 * Initialization with a default hash map.
	 * 
	 * @param st
	 */
	protected Expression(String st) 
	{
		this(st, new HashMap<Character, Integer>());  
		postfixExpression = "";
	}

	/**
	 * Useful with the d
	 * @param varTbl
	 */
	public void setVarTable(HashMap<Character, Integer> varTbl) 
	{
		this.varTable = varTbl; 
	}
	
	/**
	 * Evaluates the infix or postfix expression. 
	 * 
	 * @return value of the expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public abstract int evaluate() throws ExpressionFormatException, UnassignedVariableException;  

	
	
	// --------------------------------------------------------
	// Helper methods for InfixExpression and PostfixExpression 
	// --------------------------------------------------------

	/** 
	 * Check if a string represents an integer.  You may call the static method 
	 * Integer.parseInt(). 
	 * 
	 * @param s
	 * @return
	 */
	protected static boolean isInt(String s) 
	{ 
		try
		{ 
			Integer.parseInt(s); 
		}
		catch(NumberFormatException e)
		{ 
			return false; 
		}
		return true; 
	}

	
	/**
	 * Check if a char represents an operator, i.e., one of '+', '-', '*', '/', '%', '^'. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isOperator(char c) 
	{
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^')
		{ 
			return true; 
		}
		else 
		{ 
			return false; 
		}
	}
	
	protected static boolean isParentheses(char c)
	{
		if (c == ')' || c == '(')
		{ 
			return true; 
		}
		else 
		{ 
			return false; 
		}
		
	}

	/** 
	 * Check if a char is a variable, i.e., a lower case English letter. 
	 * 
	 * @param c
	 * @return
	 */
	protected static boolean isVariable(char c) 
	{
		return Character.isLowerCase(c);
	}	
	
	/**
	 * Check to see if a char is a space or a tab
	 * @param c
	 * @return
	 */
	protected static boolean isSpace(char c)
	{ 
		return Character.isWhitespace(c) || c == ' '; 
	}
	
	/**
	 * Removes extra spaces from string
	 * @param s
	 * @return
	 */
	 protected static String removeExtraSpaces(String s) {
	     return s.replaceAll("\\s+", " ").trim();
	 }

}
