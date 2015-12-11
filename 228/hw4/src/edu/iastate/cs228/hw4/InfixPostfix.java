package edu.iastate.cs228.hw4;

/**
 *  
 * @author
 *
 */

/**
 * 
 * This class evaluates input infix and postfix expressions. 
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import edu.iastate.cs228.hw4.Expression; 

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 * @throws UnassignedVariableException 
	 * @throws ExpressionFormatException 
	 * @throws FileNotFoundException 
	 **/
	public static void main(String[] args) throws ExpressionFormatException, UnassignedVariableException, FileNotFoundException 
	{
		//trash code 
		boolean running = true; 
		System.out.println("Evaluation of Infix and Postfix Expressions\nkeys: 1 (standard input) 2 (file input) 3 (exit)\n(Enter 'I' before an infix expression, 'P' before a postfix expression)"); 
		int trial = 1;
		Scanner userInput = new Scanner(System.in); 
		while(running)
		{ 
			System.out.print("Trial " + trial + ": ");
			int keyChoice = userInput.nextInt(); 
			if(keyChoice == 1)
			{ 
				System.out.print("Expression: ");
				String expression = "";  
				while(expression.length() == 0)
				{ 
					expression = userInput.nextLine(); 
				}
				if(expression.contains("I"))
				{ 
					if(hasVariables(expression))
					{ 
						HashMap<Character, Integer> varHash = new HashMap<Character, Integer>();
						InfixExpression infix = new InfixExpression(expression.substring(1, expression.length())); 
						System.out.println("Infix form: " + infix.toString()); 
						PostfixExpression postfix = new PostfixExpression(infix.postfixString());  
						System.out.println("Postfix form: " + postfix.toString());
						System.out.println("where"); 
						for(int i = 0; i < expression.length(); i++)
						{ 
							if(Expression.isVariable(expression.charAt(i)))
							{ 
								if(!varHash.containsKey(expression.charAt(i)))
								{ 
									System.out.print(expression.charAt(i)+"= ");
									int varVal = userInput.nextInt(); 
									varHash.put(expression.charAt(i), varVal);
								}
							}
						}
						postfix = new PostfixExpression(infix.postfixString(), varHash); 
						System.out.println("Expression value: " + postfix.evaluate()); 
					}
					else 
					{ 
						InfixExpression infix = new InfixExpression(expression.substring(1, expression.length())); 
						System.out.println("Infix form: " + infix.toString()); 
						PostfixExpression postfix = new PostfixExpression(infix.postfixString());  
						System.out.println("Postfix form: " + postfix.toString()); 
						System.out.println("Expression value: " + postfix.evaluate()); 
					}
					
				}
				if(expression.contains("P"))
				{ 
					if(hasVariables(expression))
					{ 
						HashMap<Character, Integer> varHash = new HashMap<Character, Integer>();
						PostfixExpression postfix = new PostfixExpression(expression.substring(1, expression.length())); 
						System.out.println("Postfix form: " + postfix.toString());
						System.out.println("where"); 
						for(int i = 0; i < expression.length(); i++)
						{ 
							if(Expression.isVariable(expression.charAt(i)))
							{ 
								if(!varHash.containsKey(expression.charAt(i)))
								{ 
									System.out.print(expression.charAt(i)+"= ");
									int varVal = userInput.nextInt(); 
									varHash.put(expression.charAt(i), varVal);
								}
							}
						}
						postfix = new PostfixExpression(expression.substring(1, expression.length()), varHash); 
						System.out.println("Expression value: " + postfix.evaluate()); 
					}
					else 
					{ 
						PostfixExpression postfix = new PostfixExpression(expression.substring(1, expression.length())); 
						System.out.println("Postfix form: " + postfix.toString()); 
						System.out.println("Expression value: " + postfix.evaluate()); 
					}
					
				}
				trial++;
			}
			else if(keyChoice == 2)
			{ 
				System.out.println("Input from a file"); 
				System.out.print("Enter file name: "); 
				String inFile = ""; 
				while(inFile.length() == 0)
				{ 
					inFile = userInput.nextLine(); 
				}
				Scanner fileScan = new Scanner(new File(inFile)); 
				String current = fileScan.nextLine(); 
				while(fileScan.hasNextLine())
				{   
					if(current.contains("I"))
					{ 
						String expression = current; 
						if(hasVariables(expression))
						{ 
							char variable; 
							int value = 0; 
							HashMap<Character, Integer> varHash = new HashMap<Character, Integer>();
							InfixExpression infix = new InfixExpression(expression.substring(1, expression.length())); 
							System.out.println("Infix form: " + infix.toString()); 
							PostfixExpression postfix = new PostfixExpression(infix.postfixString());  
							System.out.println("Postfix form: " + postfix.toString());
							System.out.println("where"); 
							String variables = fileScan.nextLine(); 
							while(Expression.isVariable(variables.charAt(0)))
							{ 
								variable = variables.charAt(0); 
								value = Integer.parseInt(variables.substring(4, variables.length())); 
								if(!varHash.containsKey(variable))
								{ 
									System.out.println(variable+"= "+value);
									varHash.put(variable, value);
								}
								if(fileScan.hasNextLine())
								{ 
									variables = fileScan.nextLine(); 
								}
								if(!fileScan.hasNextLine())
								{ 
									break; 
								} 
							}
							postfix = new PostfixExpression(infix.postfixString(), varHash); 
							System.out.println("Expression value: " + postfix.evaluate()); 
							if(fileScan.hasNextLine())
							{ 
								current = variables;
							}
							if(!fileScan.hasNextLine())
							{ 
								break; 
							}
							 
						}
						else 
						{ 
							InfixExpression infix = new InfixExpression(expression.substring(1, expression.length())); 
							System.out.println("Infix form: " + infix.toString()); 
							PostfixExpression postfix = new PostfixExpression(infix.postfixString());  
							System.out.println("Postfix form: " + postfix.toString()); 
							System.out.println("Expression value: " + postfix.evaluate()); 
							if(fileScan.hasNextLine())
							{ 
								current = fileScan.nextLine(); 
							}
						}
					}
					if(current.contains("P"))
					{
						String expression = current; 
						if(hasVariables(expression))
						{ 
							char variable; 
							int value = 0; 
							HashMap<Character, Integer> varHash = new HashMap<Character, Integer>();
							PostfixExpression postfix = new PostfixExpression(expression.substring(1, expression.length())); 
							System.out.println("Postfix form: " + postfix.toString());
							System.out.println("where");
							String variables = fileScan.nextLine();
							while(Expression.isVariable(variables.charAt(0)))
							{ 
								variable = variables.charAt(0); 
								value = Integer.parseInt(variables.substring(4, variables.length())); 
								if(!varHash.containsKey(variable))
								{ 
									System.out.println(variable+"= "+value);
									varHash.put(variable, value);
								}
								if(fileScan.hasNextLine())
								{ 
									variables = fileScan.nextLine(); 
								}
								if(!fileScan.hasNextLine())
								{ 
									break; 
								}
							}
							postfix = new PostfixExpression(expression.substring(1, expression.length()), varHash); 
							System.out.println("Expression value: " + postfix.evaluate()); 
							if(fileScan.hasNextLine())
							{ 
								current = variables; 
							}
						}
						else
						{ 
							PostfixExpression postfix = new PostfixExpression(expression.substring(1, expression.length())); 
							System.out.println("Postfix form: " + postfix.toString()); 
							System.out.println("Expression value: " + postfix.evaluate()); 
							if(fileScan.hasNextLine())
							{ 
								current = fileScan.nextLine(); 
							} 
						}
					}
					if((!current.contains("I") && !current.contains("P")) && fileScan.hasNextLine())
					{ 
						current = fileScan.nextLine(); 
					}
				}
				fileScan.close(); 
				trial++; 
			}
			else 
			{ 
				running = false; 
			}
		}
		userInput.close(); 
	}
	
	private static boolean hasVariables(String s)
	{ 
		Scanner stringScan = new Scanner(s); 
		while(stringScan.hasNext())
		{ 
			String token = stringScan.next(); 
			if(Expression.isVariable(token.charAt(0)))
			{ 
				stringScan.close(); 
				return true; 
			}
		}
		stringScan.close(); 
		return false; 
	}
}
