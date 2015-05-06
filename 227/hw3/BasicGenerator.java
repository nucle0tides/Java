/**
 * 
 */
package hw3;

import hw3.api.IPolyomino;
import hw3.api.IPolyominoGenerator;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;

import java.awt.Color;
import java.util.Random;

/**
 * @author Gabby Ortman
 *
 */
public class BasicGenerator implements IPolyominoGenerator {

	/**
	 * Instance of the random class for random goodies
	 */
	private Random rand; 
	
	/**
	 * Constuctor for the BasicGenerator for the game. Initializes the instance of random that's essential to the random nature of piece picking and color choosing. 
	 */
	public BasicGenerator(Random rand) {
		this.rand = rand; 
	}
	
	@Override
	public IPolyomino getNext() {
		 
		int randomShape = rand.nextInt(100); 
		if (randomShape <= 19) { 
			return new LPiece(new Position(-2,5), colorPicker(4)); 
		}
		else if (randomShape > 19 && randomShape <= 39) { 
			return new DiagonalPiece(new Position(-1,5), colorPicker(2)); 
		}
		else { 
			return new IPiece(new Position(-2, 5), colorPicker(3)); 
		}
	}
	
	/**
	 * Fun helper method that allows us to easily pick a color based on the length of our shape. 
	 * @param shapeLength
	 * 		the number of blocks a certain shape has
	 * @return
	 * 		an array of colors that will be applied to the shape. 
	 */
	private Color[] colorPicker(int shapeLength) { 
		Color[] colors = new Color[shapeLength]; 
		for(int i = 0; i < shapeLength; i++) {
			colors[i] = AbstractBlockGame.COLORS[rand.nextInt(7)]; 
		}
		return colors;
	}
	
}
