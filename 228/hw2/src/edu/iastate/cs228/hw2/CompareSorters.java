package edu.iastate.cs228.hw2;

/**
 *  
 * @author Gabby Ortman
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		boolean running = true;
		Scanner userInput = new Scanner(System.in); 
		System.out.println("Comparison of Four Sorting Algorithms"); 
		System.out.println("keys:\t1 (random integers)\t2 (file input)\t3 (exit)"); 
		System.out.println("order:\t1 (by x-coordinate)\t2 (by polar angle)");	
		int trial = 1; 
		while(running)
		{
			System.out.print("Trial " + trial + ": \n");
			int action = userInput.nextInt(); 
			if(action == 1)
			{ 
				System.out.println("Enter number of random points: "); 
				int numPoints = userInput.nextInt(); 
				System.out.println("Order used in sorting: "); 
				int order = userInput.nextInt(); 
				Point[] pts = CompareSorters.generateRandomPoints(numPoints, new Random());
				SelectionSorter sSort = new SelectionSorter(pts); 
				InsertionSorter iSort = new InsertionSorter(pts); 
				MergeSorter mSort = new MergeSorter(pts); 
				QuickSorter qSort = new QuickSorter(pts); 
				sSort.sort(order);
				iSort.sort(order);
				mSort.sort(order);
				qSort.sort(order);
				System.out.println("algorithm\tsize\ttime (ns)");
				System.out.println("------------------------------------"); 
				System.out.println(sSort.stats());
				System.out.println(iSort.stats());
				System.out.println(mSort.stats());
				System.out.println(qSort.stats());
				System.out.println("------------------------------------");
				sSort.writePointsToFile();
				iSort.writePointsToFile();
				mSort.writePointsToFile();
				qSort.writePointsToFile();
				trial++; 
			}
			else if(action == 2)
			{ 
				System.out.println("Points from a file"); 
				System.out.println("File name: "); 
				String fileName = userInput.next(); 
				System.out.println("Order used in sorting: "); 
				int order = userInput.nextInt();  
				SelectionSorter sSort = new SelectionSorter(fileName); 
				InsertionSorter iSort = new InsertionSorter(fileName); 
				MergeSorter mSort = new MergeSorter(fileName); 
				QuickSorter qSort = new QuickSorter(fileName); 
				sSort.sort(order);
				iSort.sort(order);
				mSort.sort(order);
				qSort.sort(order);
				System.out.println("algorithm\tsize\ttime (ns)");
				System.out.println("------------------------------------"); 
				System.out.println(sSort.stats());
				System.out.println(iSort.stats());
				System.out.println(mSort.stats());
				System.out.println(qSort.stats());
				System.out.println("------------------------------------");
				sSort.writePointsToFile();
				iSort.writePointsToFile();
				mSort.writePointsToFile();
				qSort.writePointsToFile();
				trial++;
			}
			else
			{ 
				running = false; 
			}
		}
		userInput.close();
	}
	
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		Point[] randPoints = new Point[numPts]; 
		for(int i = 0; i < randPoints.length; i++)
		{ 
			randPoints[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50); 
		}
		return randPoints; 
	}
}
