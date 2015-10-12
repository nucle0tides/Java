package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner; 
import java.io.File;

/**
 * 
 * @author Gabby Ortman 
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later on the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter
{
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort",  
	                                   // "mergesort", or "quicksort". Initialized by a subclass 
									   // constructor.
	protected boolean sortByAngle;     // true if last sort was done by polar angle and false 
									   // if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 

	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0)
		{ 
			throw new IllegalArgumentException(); 
		}
		else 
		{ 
			points = new Point[pts.length]; 
			for(int i = 0; i < pts.length; i++)
			{ 
				points[i] = pts[i]; 
			}
			lowestPoint = findLowest(points);
		}
	}

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		points = readFile(inputFileName);
		lowestPoint = findLowest(points); 
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		return algorithm +"\t" + points.length+ "\t"+ sortingTime; 
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString()
	{
		String returnString = ""; 
		for(int i = 0; i < points.length; i++)
		{ 
			returnString += points[i].toString() + "\n"; 
		}
		return returnString; 
	}

	
	/**
	 *  
	 * This method, called after sorting, writes point data into a file by outputFileName. It will 
	 * be used for Mathematica plotting to verify the sorting result.  The data format depends on 
	 * sortByAngle.  It is detailed in Section 4.1 of the projection description proj2.pdf. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		PrintWriter fileWriter = new PrintWriter(outputFileName);
		if(sortByAngle)
		{ 
			fileWriter.write(lowestPoint.toString()+"\n");
			for(int i = 2; i <= points.length; i++)
			{ 
				fileWriter.write(points[i-1].toString()+ " " + lowestPoint.toString()+ " " + points[i-1].toString()+"\n");
			}
		}
		else 
		{ 
			for(int i = 0; i < points.length; i++)
			{ 
				fileWriter.write(points[i].toString()+"\n");
			}
		}	
		fileWriter.close();
	}	
	
	public Point getLowestPoint() 
	{ 
		return lowestPoint; 
	}

	
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 * @param order
	 */
	protected void setComparator(int order) 
	{
		if(sortByAngle)
		{ 
			pointComparator = new PolarAngleComparator(lowestPoint); 
		}
		if(!sortByAngle)
		{ 
			pointComparator = new Comparator<Point>()
			{ 
				@Override 
				public int compare(Point p, Point other)
				{ 
					return p.compareTo(other); 
				}
			}; 
		}
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i]; 
		points[i] = points[j]; 
		points[j] = temp; 
		
	}	
	
	/**
	 * Finds the lowest and leftmost point in the array.  
	 * 
	 * @param arr
	 * @return	the lowest and leftmost point in the array. 
	 */
	private Point findLowest(Point[] arr)
	{ 
		Point lowPoint = arr[0];  
		for(int i = 1; i < arr.length; i++)
		{ 
			//smallest y coord, then smallest x coord 
			if(lowPoint.getY() > arr[i].getY())
			{ 
				lowPoint = arr[i]; 
			}
			//y coords equal, then smallest x coord
			if(lowPoint.getY() == arr[i].getY())
			{ 
				if(lowPoint.getX() > arr[i].getX())
				{ 
					lowPoint = arr[i]; 
				}
			}
		}
		return lowPoint; 
	}
	
	
	/**
	 * Scans the file to fill the array to be sorted. Does a check to see if there is an even number of points in the file.  
	 * 
	 * @param fileName
	 * @return
	 */
	protected static Point[] readFile(String fileName) throws FileNotFoundException, InputMismatchException
	{ 
		int arraySize = isEven(fileName); 
		Point[] arr; 
		File numFile = new File(fileName); 
		 
		if(arraySize % 2 == 0)
		{ 
			Scanner numScan = new Scanner(numFile);
			arr = new Point[arraySize/2]; 
			int arrIndex = 0; 
			while(numScan.hasNextInt())
			{ 
				arr[arrIndex] = new Point(numScan.nextInt(), numScan.nextInt());
				arrIndex += 1; 
			}
			numScan.close(); 
		}
		else
		{ 
			throw new InputMismatchException(); 
		}
		return arr; 
	}
	
	/**
	 * Scans through the file, counts up the number of points and returns that number.  
	 * 
	 * @param fileName
	 * @return 	the number of integers in the file. 
	 */
	private static int isEven(String fileName) throws FileNotFoundException
	{ 
		int numCount = 0; 
		File numFile = new File(fileName); 
		Scanner numScan = new Scanner(numFile); 
		while(numScan.hasNextInt())
		{ 
			numCount += 1;
			numScan.nextInt();  
		}
		numScan.close(); 
		return numCount; 
	}
}
