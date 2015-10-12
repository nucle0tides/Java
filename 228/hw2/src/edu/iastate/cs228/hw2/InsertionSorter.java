package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Gabby Ortman. I can sort objects of any type, but I can't sort my life out...
 * This class implements insertion sort
 *
 */

@SuppressWarnings("unused")
public class InsertionSorter extends AbstractSorter 
{ 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points. 
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "insertion sort"; 
		outputFileName = "insert.txt"; 
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public InsertionSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName); 
		algorithm = "insertion sort"; 
		outputFileName = "insert.txt"; 
	}
	
	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 * IllegalArgumentException if order is less than 1 or greater than 2
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 */
	@Override 
	public void sort(int order)
	{
		if(order == 1)
		{ 
			sortByAngle = false; 
			setComparator(order); 
			insertionSort();   
		}
		else if(order == 2)
		{ 
			sortByAngle = true; 
			setComparator(order);  
			insertionSort(); 
		}
		else 
		{ 
			throw new IllegalArgumentException(); 
		}
	}
	
	/**
	 * Performs insertion sort and times insertion sort. 
	 */
	private void insertionSort()
	{ 
		
		long startTime = System.nanoTime();
		for(int i = 1; i < points.length; i++)
		{ 
			Point temp = points[i];
			int j = i - 1; 
			while(j > -1 && pointComparator.compare(temp, points[j]) < 0)
			{ 
				points[j+1] = points[j]; 
				j--; 
			}
			points[j+1] = temp; 
		}
		long elapsedTime = System.nanoTime() - startTime;
		sortingTime = elapsedTime; 
	}
}
