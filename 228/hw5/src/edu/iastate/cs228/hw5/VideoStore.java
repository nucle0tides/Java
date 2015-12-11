package edu.iastate.cs228.hw5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner; 
import java.io.File; 

/**
 * 
 * @author Gabby Ortman
 *
 */

public class VideoStore 
{
	protected SplayTree<Video> inventory;     // all the videos at the store
	// ------------
	// Constructors 
	// ------------
	
    /**
     * Default constructor sets inventory to an empty tree. 
     */
    VideoStore()
    {
    	this.inventory = new SplayTree<Video>(); 
    }
    
    
	/**
	 * Constructor accepts a video file to create its inventory.  Refer to Section 3.2 of  
	 * the project description for details regarding the format of a video file. 
	 * 
	 * The construtor works in two steps: 
	 * 
	 *     1. Calls the default constructor. 
	 *     2. Has the splay tree inventory call its method addBST() to add videos to the tree.
	 * 
	 * @param videoFile  no format checking on the file
	 * @throws FileNotFoundException
	 */
    VideoStore(String videoFile) throws FileNotFoundException 
    { 
    	this(); 
    	setUpInventory(videoFile); 
    	
    }
    
    
   /**
     * Accepts a video file to initialize the splay tree inventory.  To be efficient, 
     * add videos to the inventory by calling the addBST() method, which does not splay. 
     * 
     * Refer to Section 3.2 for the format of video file. 
     * 
     * @param  videoFile  correctly formated if exists
     * @throws FileNotFoundException 
     */
    public void setUpInventory(String videoFile) throws FileNotFoundException
    {
    	bulkImport(videoFile); 
    }
	
    
    // ------------------
    // Inventory Addition
    // ------------------
    
    /**
     * Find a Video object by film title. 
     * 
     * @param film
     * @return
     */
	public Video findVideo(String film) 
	{
		return inventory.findElement(new Video(film)); 
	}


	/**
	 * Updates the splay tree inventory by adding a given number of video copies of the film.  
	 * (Splaying is justified as new videos are more likely to be rented.) 
	 * 
	 * Calls the add() method of SplayTree to add the video object.  If true is returned, the 
	 * film was not on the inventory before, and has been added.  If false is returned, the 
	 * film is already on the inventory. The root of the splay tree must store the corresponding
	 * Video object for the film. Calls findElement() of the SplayTree class to get this Video 
	 * object, which then calls getNumCopies() and addNumCopies() of the Video class to increase
	 * the number of copies of the corresponding film by n 
	 * 
	 * @param film  title of the film
	 * @param n     number of video copies 
	 */
	public void addVideo(String film, int n)
	{
		boolean added = inventory.add(new Video(film, n)); 
		if(!added)
		{ 
			Video vid = inventory.findElement(new Video(film, n)); 
			vid.addNumCopies(n); 
		}
	}
	

	/**
	 * Add one video copy of the film. 
	 * 
	 * @param film  title of the film
	 */
	public void addVideo(String film)
	{
		addVideo(film, 1); 
	}
	

	/**
     * Update the splay trees inventory.  
     * 
     * The videoFile format is given in Section 3.2 of the project description. 
     * 
     * @param videoFile  correctly formated if exists 
     * @throws FileNotFoundException
     */
    public void bulkImport(String videoFile) throws FileNotFoundException 
    {
    	File videoList = new File(videoFile); 
    	Scanner fileScan = new Scanner(videoList); 
    	while(fileScan.hasNextLine())
    	{ 
    		String line = fileScan.nextLine(); 
    		String name = parseFilmName(line); 
    		int num = parseNumCopies(line); 
    		inventory.addBST(new Video(name, num)); 
    	}
    	fileScan.close(); 
    }

    
    // ----------------------------
    // Video Query, Rental & Return 
    // ----------------------------
    
	/**
	 * Search the splay tree inventory to determine if a video is available. 
	 * 
	 * @param  film
	 * @return true if available
	 */
	public boolean available(String film)
	{
		Iterator<Video> iter = inventory.iterator(); 
		Video current = null; 
		while(iter.hasNext())
		{ 
			current = iter.next(); 
			if(current.getFilm().equals(film) && current.getNumAvailableCopies() != 0)
			{ 
				return true; 
			}
		}
		return false; 
	}

	
	
	/**
     * Update inventory. 
     * 
     * Search if the film is in inventory by calling findElement(new Video(film, 1)). 
     * 
     * If the film is not in inventory, prints the message "Film <film> is not 
     * in inventory", where <film> shall be replaced with the string that is the value 
     * of the parameter film.  If the film is in inventory with no copy left, prints
     * the message "Film <film> has been rented out".
     * 
     * If there is at least one available copy but n is greater than the number of 
     * such copies, rent all available copies. In this case, no AllCopiesRentedOutException
     * is thrown.  
     * 
     * @param film   
     * @param n 
	 * @throws FilmNotInInventoryException
	 * @throws AllCopiesRentedOutException   if there is zero copy of the film.
	 */
	public void videoRent(String film, int n) throws FilmNotInInventoryException, AllCopiesRentedOutException 
	{
		Video vid = inventory.findElement(new Video(film)); 
		if(vid == null)
		{ 
			throw new FilmNotInInventoryException("Film " + film + " is not in inventory"); 
		}
		else if(vid.getFilm().equals(film))
		{ 
			if(vid.getNumAvailableCopies() == 0)
			{ 
				throw new AllCopiesRentedOutException("Film " + film + " has been rented out"); 
			}
//			else if(vid.getNumAvailableCopies() > 0 && n > vid.getNumAvailableCopies())
//			{ 
//				throw new AllCopiesRentedOutException("Film " + film + " has been rented out"); 
//			}
			else
			{
				vid.rentCopies(n); 
			}
		}
	}

	
	/**
	 * Update inventory.
	 * 
	 *    1. Calls videoRent() repeatedly for every video listed in the file.  
	 *    2. For each requested video, do the following: 
	 *       a) If it is not in inventory or is rented out, an exception will be 
	 *          thrown from rent().  Based on the exception, prints out the following 
	 *          message: "Film <film> is not in inventory" or "Film <film> 
	 *          has been rented out." In the message, <film> shall be replaced with 
	 *          the name of the video. 
	 *       b) Otherwise, update the video record in the inventory.
	 * 
	 * @param videoFile  correctly formatted if exists
	 * @throws Exception 
	 */
	public void bulkRent(String videoFile) throws Exception 
	{
		try
		{ 
			File videoList = new File(videoFile);
			Scanner fileScan = new Scanner(videoList); 
			ArrayList<Exception> arr = new ArrayList<Exception>(); 
	    	while(fileScan.hasNextLine())
	    	{ 
	    		String line = fileScan.nextLine(); 
	    		String name = parseFilmName(line); 
	    		int num = parseNumCopies(line);  
	    		try
	    		{ 
	    			this.videoRent(name, num); 
	    		}
	    		catch(IllegalArgumentException i)
	    		{ 
	    			arr.add(i); 
	    		}
	    		catch(FilmNotInInventoryException f)
	    		{ 
	    			arr.add(f); 
	    		}
	    		catch(AllCopiesRentedOutException a)
	    		{ 
	    			arr.add(a); 
	    		}
	    	}
	    	fileScan.close(); 
	    	for (Exception e : arr)
	    	{ 
	    		e.getClass(); 
	    	}
		}
		catch(FileNotFoundException file)
		{ 
			System.out.println(file.getMessage()); 
		}
	}

	
	/**
	 * Update inventory.
	 * 
	 * If n exceeds the number of rented video copies, accepts up to that number of rented copies
	 * while ignoring the extra copies. 
	 * 
	 * @param film
	 * @param n
	 * @throws FilmNotInInventoryException 
	 */
	public void videoReturn(String film, int n) throws FilmNotInInventoryException
	{
		Video vid = inventory.findElement(new Video(film)); 
		if(vid == null)
		{ 
			throw new FilmNotInInventoryException("Film " + film + " is not in the inventory"); 
		}
		if(n < 0)
		{ 
			throw new IllegalArgumentException("Film " + film + " has has an invalid request"); 
		}
		vid.returnCopies(n); 
	}
	
	
	/**
	 * Update inventory. 
	 * 
	 * Handles excessive returned copies of a film in the same way as videoReturn() does.  
	 * 
	 * @param videoFile
	 * throws FileNotFoundException
	 * @throws Exception 
	 */
	public void bulkReturn(String videoFile) throws Exception 
	{
		File videoList = new File(videoFile); 
    	Scanner fileScan = new Scanner(videoList); 
    	ArrayList<Exception> arr = new ArrayList<Exception>(); 
    	while(fileScan.hasNextLine())
    	{ 
    		String line = fileScan.nextLine(); 
    		String name = parseFilmName(line); 
    		int num = parseNumCopies(line); 
    		
    		try
    		{ 
    			this.videoReturn(name, num); 
    		}
    		catch(FilmNotInInventoryException e)
    		{ 
    			arr.add(e); 
    		}
    		catch(IllegalArgumentException a)
    		{ 
    			arr.add(a); 
    		}
    	}
    	fileScan.close(); 
    	for (Exception e : arr)
    	{ 
    		e.getClass(); 
    	}
	}
		
	

	// ------------------------
	// Methods with No Splaying
	// ------------------------
		
	/**
	 * Performs inorder traveral on the splay tree inventory to list all the videos by film 
	 * title, whether rented or not.  Below is a sample string if printed out: 
	 * 
	 * 
	 * Films in inventory: 
	 * 
	 * A Streetcar Named Desire (1) 
	 * Brokeback Mountain (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (5) 
	 * Taxi Driver (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	public String inventoryList()
	{
		Iterator<Video> iter = inventory.iterator(); 
		String returnString = "";  
		while(iter.hasNext())
		{ 
			Video current = iter.next(); 
			returnString += current.getFilm() + " (" + current.getNumCopies() + ")" + "\n"; 
		}
		return returnString; 
	}

	
	/**
	 * Calls rentedVideosList() and unrentedVideosList() sequentially.  For the string format, 
	 * see Transaction 5 in the sample simulation in Section 4 of the project description. 
	 *   
	 * @return 
	 */
	public String transactionsSummary()
	{
		String returnString = ""; 
		returnString += rentedVideosList() +"\n" + unrentedVideosList(); 
		return returnString; 
	}	
	
	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Rented films: 
	 * 
	 * Brokeback Mountain (1) 
	 * Singin' in the Rain (2)
	 * Slumdog Millionaire (1) 
	 * The Godfather (1) 
	 * 
	 * 
	 * @return
	 */
	private String rentedVideosList()
	{
		Iterator<Video> iter = inventory.iterator(); 
		String returnString = "Rented films:\n\n";  
		while(iter.hasNext())
		{ 
			Video current = iter.next(); 
			if(current.getNumRentedCopies() != 0)
			{
				returnString += current.getFilm() + " (" + current.getNumRentedCopies() + ")" + "\n"; 
			}
		}
		return returnString; 
	}

	/**
	 * Performs inorder traversal on the splay tree inventory.  Use a splay tree iterator.
	 * Prints only the films that have unrented copies. 
	 * 
	 * Below is a sample return string when printed out:
	 * 
	 * 
	 * Films remaining in inventory:
	 * 
	 * A Streetcar Named Desire (1) 
	 * Forrest Gump (1)
	 * Psycho (1) 
	 * Slumdog Millionaire (4) 
	 * Taxi Driver (1) 
	 * 
	 * 
	 * @return
	 */
	private String unrentedVideosList()
	{
		Iterator<Video> iter = inventory.iterator(); 
		String returnString = "Films remaining in inventory:\n\n";  
		while(iter.hasNext())
		{ 
			Video current = iter.next(); 
			if(current.getNumAvailableCopies() != 0)
			{ 
			returnString += current.getFilm() + " (" + current.getNumAvailableCopies() + ")" + "\n"; 
			} 
		}
		return returnString; 
	}	

	
	/**
	 * Parse the film name from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static String parseFilmName(String line) 
	{
		//This is messy and I feel bad.
		//Should have used regular expressions
		String lineRev = new StringBuilder(line).reverse().toString(); 
		String integer;  
		if(lineRev.startsWith(")"))
		{ 
			if(Character.isDigit(lineRev.charAt(1)))
			{ 
				integer = lineRev.substring(lineRev.indexOf(')'), lineRev.indexOf('(', 0) + 1);
				integer = new StringBuilder(integer).reverse().toString(); 
				return line.replace(integer, "").trim(); 
			}
			else
			{ 
				return line; 
			}
			
		}
		else
		{ 
			return line; 
		}
	}
	
	
	/**
	 * Parse the number of copies from an input line. 
	 * 
	 * @param line
	 * @return
	 */
	public static int parseNumCopies(String line) 
	{
		//This is messy and I feel bad. 
		String lineRev = new StringBuilder(line).reverse().toString(); 
		String integer = Integer.toString(1); 
		if(lineRev.startsWith(")"))
		{ 
			integer = lineRev.substring(lineRev.indexOf(')')+1, lineRev.indexOf('(')); 
			integer = new StringBuilder(integer).reverse().toString(); 
		}
		else
		{ 
			return 1; 
		}
		int toReturn; 
		try
		{ 
			toReturn = Integer.parseInt(integer); 
		}
		catch(Exception e)
		{ 
			return 1; 
		}
		return toReturn; 
	}
}
