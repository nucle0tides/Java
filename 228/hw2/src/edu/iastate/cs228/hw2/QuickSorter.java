package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 


/**
 *  
 * @author Gabby Ortman 
 * This class implements quicksort
 *
 */

@SuppressWarnings("unused")
public class QuickSorter extends AbstractSorter
{ 		
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort"; 
		outputFileName = "quick.txt"; 
	}
		

	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public QuickSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName);
		algorithm = "quicksort"; 
		outputFileName = "quick.txt"; 
	}


	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
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
			quickSort(points);   
		}
		else if(order == 2)
		{ 
			sortByAngle = true; 
			setComparator(order);  
			quickSort(points); 
			for(int i = 0; i < points.length; i++)
			{ 
				System.out.println(points[i]); 
			}
		}
		else 
		{ 
			throw new IllegalArgumentException(); 
		}
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(Point[] pts, int first, int last)
	{
		if(first >= last)
		{ 
			return; 
		}
		int p = partition(pts, first, last); 
		quickSortRec(pts, first, p-1); 
		quickSortRec(pts, p+1, last); 
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(Point[] pts, int first, int last)
	{
		Point pivot = pts[last]; 
		int i = first - 1; 
		for(int j = first; j < last; j++)
		{ 
			if(pointComparator.compare(pts[j], pivot) < 0)
			{ 
				i++; 
				swap(i, j);
			}
		}
		swap(i+1, last); 
		return i + 1; 
	}	
		
	/**
	 * Calls quickSortRec and calculates the time the algorithm takes to run. 
	 * 
	 * @param pts
	 */
	private void quickSort(Point[] pts)
	{ 
		long startTime = System.nanoTime();
		quickSortRec(pts, 0, pts.length -1);
		long elapsedTime = System.nanoTime() - startTime;
		sortingTime = elapsedTime;
	}
}
