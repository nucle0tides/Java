package mini3;

/**
 * Transformation implementing a smoothing operation
 * on cells of an array.  The new value is the 
 * average of the values in a neighborhood
 * around a given cell, rounded to the nearest
 * integer. The size of the neighborhood is 
 * 2 * radius + 1, where the radius is a parameter
 * provided to the constructor. Values are not wrapped.
 */
public class SmoothingTransform implements ITransform
{
  private int radius; 
  public SmoothingTransform(int givenRadius)
  {
    radius = givenRadius; 
  }
  
  @Override
  public int apply(int[][] elements)
  { 
 	int total = 0;
 	int size = elements.length;
   	for (int x = 0; x < elements.length; x++ )
   	{ 
   		for (int y = 0; y < elements.length; y++)
   		{ 
   			total += elements[x][y]; 
   		}
   	}
    return total/(int) Math.pow(size,2);
    
  }

  @Override
  public int getRadius()
  { 
	  return radius; 
  }

  @Override
  public boolean isWrapped()
  {
    return false;
  }

}
