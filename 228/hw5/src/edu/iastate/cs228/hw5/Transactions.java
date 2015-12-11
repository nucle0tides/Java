package edu.iastate.cs228.hw5;

import java.util.Scanner; 

/**
 *  
 * @author Gabby Ortman
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions 
{
	
	/**
	 * The main method generates a simulation of rental and return activities.  
	 *  
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{	
		Scanner setUp = new Scanner(System.in); 
		String filename = setUp.next(); 
		VideoStore BlockBuster = new VideoStore(filename); 
		setUp.close(); 
		boolean running = true; 
		System.out.println("Transactions at a Video Store"); 
		@SuppressWarnings("unused")
		String keys = ""; 
		keys += "keys: 1 (rent)\t2 (bulk rent)\n";
		keys += "keys: 3 (return)\t4 (bulk return)\n";
		keys += "keys: 5 (summary)\t6 (exit)";
		Scanner userInput = new Scanner(System.in); 
		while(running)
		{ 
			System.out.print("Transaction: "); 
			int transaction = userInput.nextInt(); 
			if(transaction == 1)
			{ 
				System.out.println("Film to rent: "); 
				String filmToRent = ""; 
				while(filmToRent.length() == 0)
				{ 
					filmToRent = userInput.nextLine(); 
				}
				int numToRent = VideoStore.parseNumCopies(filmToRent); 
				String toRent = VideoStore.parseFilmName(filmToRent); 
				try
				{ 
					BlockBuster.videoRent(toRent, numToRent); 
				}
				catch (Exception e)
				{ 
					System.out.println(e.getMessage()); 
				}
			}
			else if(transaction == 2) 
			{ 
				System.out.println("Video file (rent): "); 
				String filmsToRent = ""; 
				while(filmsToRent.length() == 0)
				{ 
					filmsToRent = userInput.nextLine(); 
				}
				try
				{ 
					BlockBuster.bulkRent(filmsToRent); 
				}
				catch(Exception e)
				{ 
					System.out.println(e.getMessage()); 
				}
			}
			else if(transaction == 3)
			{ 
				System.out.println("Film to return: ");  
				String line = ""; 
				while(line.length() == 0)
				{ 
					line = userInput.nextLine(); 
				}
				int numToReturn = VideoStore.parseNumCopies(line); 
				String filmToReturn = VideoStore.parseFilmName(line); 
				try
				{ 
					BlockBuster.videoReturn(filmToReturn, numToReturn);
				}
				catch (Exception e)
				{ 
					System.out.println(e.getMessage()); 
				}
			}
			else if(transaction == 4)
			{ 
				System.out.println("Video file (return):"); 
				String filmsToReturn = ""; 
				while(filmsToReturn.length() == 0)
				{ 
					filmsToReturn = userInput.next(); 
				}
				try
				{ 
					BlockBuster.bulkReturn(filmsToReturn); 
				}
				catch (Exception e)
				{ 
					System.out.println(e.getMessage()); 
				}
			}
			else if(transaction == 5)
			{ 
				System.out.println(BlockBuster.transactionsSummary()); 
			}
			else
			{ 
				userInput.close(); 
				running = false; 
			}	
		}
	}
}
