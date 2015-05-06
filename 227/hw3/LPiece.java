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
public class LPiece extends AbstractPiece implements IPolyomino {

	
	/**
	 * Constructor for the L Piece.
	 * Takes in a position and array of colors
	 */
	public LPiece(Position position, Color[] colors) {
		super(position, colors);
		
		Block[] givenBlocks = new Block[4];
		//I'm so sorry for repeating this code.
		if (colors.length != givenBlocks.length) { 
	    	throw new IllegalArgumentException(); 
	    }
		givenBlocks[0] = new Block(colors[0], new Position(0, 0));
	    givenBlocks[1] = new Block(colors[1], new Position(0, 1));
	    givenBlocks[2] = new Block(colors[2], new Position(1, 1));
	    givenBlocks[3] = new Block(colors[3], new Position(2, 1));
		initializeBlocks(givenBlocks);        
	}	
	
	/**
	 * Overriding the transform() method for LPiece since I am too /clearly/ too dumb to figure out something for every piece. 
	 * I'll forgive myself. 
	 * Transforming the LPiece flips the block that may exist in the first index or last index of the array
	 */
	@Override 
	public void transform() {
		//3 is the length of the bounding box
		int mid = 3/2; 
		for(int i = 0; i < getPieces().length; i++)
		{ 
			if(getPieces()[i].getPosition().getCol() < (mid))
			{ 
				getPieces()[i].getPosition().setCol(2);
			} 
			
			else if(getPieces()[i].getPosition().getCol() > (mid))
			{ 
				getPieces()[i].getPosition().setCol(0);
			} 
		}
	}

}
