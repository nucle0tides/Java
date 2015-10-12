package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 

/**
 *  
 * @author Gabby Ortman 
 * This class implements merge sort
 *
 */

@SuppressWarnings("unused")
public class MergeSorter extends AbstractSorter
{	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts); 
		algorithm = "mergesort"; 
		outputFileName = "merge.txt"; 
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public MergeSorter(String inputFileName) throws FileNotFoundException
	{
		super(inputFileName); 
		algorithm = "mergesort"; 
		outputFileName = "merge.txt";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
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
			mergeSortRec(points);    
		}
		else if(order == 2)
		{ 
			sortByAngle = true; 
			setComparator(order);  
			mergeSortRec(points); 
		}
		else 
		{ 
			throw new IllegalArgumentException(); 
		}
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 * So maybe I ripped this directly from my 227 notes..........or maybe I didn't.
	 */
	private void mergeSortRec(Point[] pts)
	{
		long startTime = System.nanoTime();
		// Base case
	    if (pts.length <= 1)
	    {
	      return;
	    }
	    
	    // Split into two new arrays
	    int mid = pts.length / 2;
	    int secondLength = pts.length - mid;
	    
	    Point[] first = new Point[mid];   
	    for (int i = 0; i < mid; ++i)
	    {
	      first[i] = pts[i];
	    }
	    
	    Point[] second = new Point[secondLength];
	    for (int i = 0; i < secondLength; ++i)
	    {
	      second[i] = pts[mid + i];
	    }
	    
	    // Recursively sort each half and merge back together
	    mergeSortRec(first);
	    mergeSortRec(second);
	    Point[] result = merge(first, second);
	    
	    // result now has to be put into the given array
	    for (int i = 0; i < result.length; ++i)
	    {
	      pts[i] = result[i];
	    }
	    long elapsedTime = System.nanoTime() - startTime;
		sortingTime = elapsedTime;
	}
	
	/**
	 * Merge the left and right half of the subarray. 
	 * @param left
	 * @param right
	 */
	private Point[] merge(Point[] left, Point[] right)
	{ 
		Point[] result = new Point[left.length + right.length]; 
		int i = 0;                  // starting index in a
	    int j = 0;                  // starting index in b
	    final int iMax = left.length;  // max index in a
	    final int jMax = right.length;  // max index in b
	    int k = 0;                  // index in result
	     
	    while (i < iMax && j < jMax)
	    {
	      if (pointComparator.compare(left[i], right[j]) < 0) 
	      {
	        result[k] = left[i];
	        i = i + 1;
	        k = k + 1;
	       }
	      else
	      {
	        result[k] = right[j];
	        j = j + 1;
	        k = k + 1;
	      }
	    }
	    
	    // pick up any stragglers
	    while (i < iMax)
	    {
	      result[k] = left[i];
	      i = i + 1;
	      k = k + 1;
	    }
	    while (j < jMax)
	    {
	      result[k] = right[j];
	      j = j + 1;
	      k = k + 1;
	    }
	    return result; 
	  }
}
