/**
 * 
 */
package hw3;

import java.awt.Color;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;

/**
 * @author Gabby Ortman
 *
 */
public class IPiece extends AbstractPiece implements IPolyomino {
	
	/**
	 * Constructor for the IPiece. It takes in a position, and array of colors it can have. FUN!
	 */
	public IPiece(Position position, Color[] colors) {
		super(position, colors);  
		
		Block[] givenBlocks = new Block[3];
		//I'm so sorry for repeating this code.
		if (colors.length != givenBlocks.length) { 
	    	throw new IllegalArgumentException(); 
	    }
		givenBlocks[0] = new Block(colors[0], new Position(0, 1));
	    givenBlocks[1] = new Block(colors[1], new Position(1, 1));
	    givenBlocks[2] = new Block(colors[2], new Position(2, 1));
		initializeBlocks(givenBlocks);    
	}	
	
	/**
	 * Transforming the IPiece does nothing and is a waste of time. 
	 */
	@Override
	public void transform() {
		//what a useless piece. 
	}

}
