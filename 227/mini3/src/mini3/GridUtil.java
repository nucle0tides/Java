package mini3;

/**
 * Utility class for applying transformations to 2d arrays.
 * A transformation computes a new value for a cell that is
 * based on the square neighborhood of cells that surround it.
 * Each transformation is an implementation of 
 * the ITransform interface.
 */
public class GridUtil
{
  /**
   * Applies the given transformation to all cells of the given
   * array and produces a new array of the same size.  The original
   * array is not modified.
   * @param arr
   *   given array
   * @param transform
   *   transformation to apply
   * @return
   *   new array consisting of transformed values
   */
  public static int[][] applyAll(int[][] arr, ITransform transform)
  {
    int numRows = arr.length;
    int numCols = arr[0].length;
    int[][] result = new int[numRows][numCols];
    for (int row = 0; row < numRows; row += 1)
    {
      for (int col = 0; col < numCols; col += 1)
      {
        int[][] subArray = getSubArray(arr, row, col, transform.getRadius(), transform.isWrapped());
        int newValue = transform.apply(subArray);
        result[row][col] = newValue;
      }
    }
    return result;
  }
  
  /**
   * Returns the neighborhood surrounding the specified cell.  In general,
   * the returned array is a square sub-array of <code>arr</code>, 
   * with width and height 2 * <code>radius</code> - 1, whose center is 
   * <code>arr[centerRow][centerCol]</code>.  For cells within
   * <code>radius</code> of the edge, the value for out-of-range
   * indices is determined by
   * the <code>wrapped</code> parameter, as follows:
   * <ul>
   * <li>if <code>wrapped</code> is false, cells for out-of-range indices
   * are filled with zeros
   * <li>if <code>wrapped</code> is true, cells for out-of-range indices
   * are determined by "wrapping" the indices:
   * <ul>
   * <li>for a row index, the array height is added to or subtracted from the index
   * to bring it within range
   * <li>for a column index, the array width is added to or subtracted from
   * the index to bring it within range
   * </ul>
   * </ul>
   * Note that the size of neighborhood, neighborhood size 2 * <code>radius</code> + 1, 
   * is not allowed to be greater than either the width or height of the given array.
   * @param arr
   *   the original array
   * @param centerRow
   *   row index of center cell
   * @param centerCol
   *   column index of center cell
   * @param radius
   *   radius of the neighborhood (return array has width and height
   *   equal to 2 * <code>radius</code> + 1
   * @param wrapped
   *   true if out-of-range indices should be wrapped, false if cells
   *   should be filled with zeros
   * @return
   *   a new 2d array consisting of the cells surrounding the given center
   *   cell
   * @throws IllegalArgumentException
   *   if the neighborhood size 2 * <code>radius</code> + 1 is greater
   *   than the given array's width or height
   */
  public static int[][] getSubArray(int[][] arr, int centerRow, int centerCol, int radius, boolean wrapped)
  {
	   
	  int size = 2 * radius + 1; 
	  int [][] neighborhood = new int[size][size]; 
	  if (size > arr.length || size > arr[0].length)
	  { 
		  throw new IllegalArgumentException(); 
	  }
	  int rows = arr.length; 
	  int cols = arr[0].length; 
	  
	  for (int neiRow = 0; neiRow < size; neiRow ++)
	  { 
		  for (int neiCol = 0; neiCol < size; neiCol++)
		  { 
			  int givRow = centerRow - radius + neiRow; 
			  int givCol = centerCol - radius + neiCol; 
			  
			  if (wrapped)
			  { 
				  if (inArray(givRow, givCol, rows, cols))
				  { 
					  neighborhood[neiRow][neiCol] = arr[givRow][givCol]; 
				  }
				  else 
				  { 
					  neighborhood[neiRow][neiCol] = arr[(givRow%rows + rows) % rows][(givCol%cols + cols) % cols]; 
				  }
			  }
			  else 
			  { 
				  if (inArray(givRow, givCol, rows, cols))
				  { 
					  neighborhood[neiRow][neiCol] = arr[givRow][givCol]; 
				  }
				  else
				  { 
					  neighborhood[neiRow][neiCol] = 0; 
				  }
			  }
		  }	 
	  }
	  return neighborhood; 
  }
  /** 
   * 
   * @param row - y coordinate
   * @param col - x coordinant 
   * @param rows - total number rows 
   * @param cols - total number of columns
   * @return
   */
  private static boolean inArray(int row, int col, int rows, int cols)
  { 
	  return (0 <= row && row < rows && 0 <= col && col < cols); 
  }
}
