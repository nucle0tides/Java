package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Gabby Ortman 
 * This class implements selection sort
 *
 */

@SuppressWarnings("unused")
public class SelectionSorter extends AbstractSorter
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
	public SelectionSorter(Point[] pts)  
	{
		super(pts); 
		algorithm = "selection sort"; 
		outputFileName = "select.txt"; 
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public SelectionSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName); 
		algorithm = "selection sort"; 
		outputFileName = "select.txt"; 
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order)
	{
		if(order == 1)
		{ 
			sortByAngle = false; 
			setComparator(order); 
			selectionSort();    
		}
		else if(order == 2)
		{ 
			sortByAngle = true; 
			setComparator(order);  
			selectionSort(); 
			for(int i = 0; i < points.length; i++)
			{ 
				System.out.println("selection " + points[i]); 
			} 
		}
		else 
		{ 
			throw new IllegalArgumentException(); 
		}
	}	
	
	/**
	 * Performs selection sort. 
	 */
	private void selectionSort()
	{ 
		long startTime = System.nanoTime();
		for(int i = 0; i < points.length - 1; i++)
		{ 
			Point min = points[i]; 
			int jmin = i;
			for(int j = i+1; j < points.length; j++)
			{ 
				if(pointComparator.compare(points[j], min) < 0) 
				{ 
					min = points[j];
					jmin = j;
				}
			}
			swap(i, jmin); 
		}
		long elapsedTime = System.nanoTime() - startTime;
		sortingTime = elapsedTime;
	}
}
