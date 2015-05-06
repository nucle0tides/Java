package mini3;

/**
 * Transformation implementing Conway's Game of Life.
 * All cells have value 0 or 1. The new value is based on the center 
 * cell along with the sum S of its eight neighbors, according to 
 * the rules:
 * <ul>
 *   <li>if S < 2 the result is 0
 *   <li>if the center cell is 1 and S is 2 or 3, the result is 1
 *   <li>if S > 3 the result is 0
 *   <li>if the center cell is 0 and S is exactly 3, the result is 1
 * </ul>
 * See
 * http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * <p>
 * The radius is 1 and the wrapping behavior is true.
 */
public class ConwayTransform implements ITransform
{

  @Override
  public int apply(int[][] elements)
  {
	  if ((elements.length > 2 * getRadius() + 1) || (elements[0].length > 2 * getRadius() + 1))
	  { 
		  throw new IllegalArgumentException(); 
	  }
	  int result = 0; 
	  int total = 0;
	  int middle = elements[elements.length/2][elements.length/2];
	  for (int x = 0; x < elements.length; x++ )
	  { 
	   		for (int y = 0; y < elements.length; y++)
	   		{ 
	   			total += elements[x][y];
	   		}
	  }
	  total -= middle; 
	  if (total < 2){ 
		  return 0;  
	  }
	  if ((middle == 1) && (total == 2 || total == 3))
	  { 
		  return 1; 
	  }
	  if (total > 3)
	  { 
		  return 0; 
	  }
	  if ((middle == 0) && (total == 3))
	  { 
		  return 1; 
	  }
	  return result; 
  }

  @Override
  public int getRadius()
  {
    return 1;
  }

  @Override
  public boolean isWrapped()
  {
    return true;
  }
  
}
