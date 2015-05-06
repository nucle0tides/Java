/**
 * 
 */
package hw3;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;

import java.awt.Color;

/**
 * @author Gabby Ortman
 *
 */
public class DiagonalPiece extends AbstractPiece implements IPolyomino {
	

	 
	/**
	 * Constructor that sets up the diagonal piece. 
	 * Is a subclass of Abstract Piece
	 */
	public DiagonalPiece(Position position, Color[] colors) {
		super(position, colors);  
		
		//set up our diamond piece, it has two blocks
		Block [] givenBlocks = new Block[2];
		//I'm so sorry for repeating this code.
		if (colors.length != givenBlocks.length) { 
	    	throw new IllegalArgumentException(); 
	    }
	    givenBlocks[0] = new Block(colors[0], new Position(0, 0));
	    givenBlocks[1] = new Block(colors[1], new Position(1, 1));
	    
		//Initialize those cute blocks that make up our shape
	    initializeBlocks(givenBlocks);
	}	
	
	/**
	 * Overriding the transform() method for LPiece since I am too /clearly/ too dumb to figure out something for every piece. 
	 * I'll forgive myself. 
	 */
	@Override 
	public void transform() {
		for(int i = 0; i < getPieces().length; i++)
		{ 
			if(getPieces()[i].getPosition().getCol() == 0)
			{ 
				getPieces()[i].getPosition().setCol(1);
			} 
			
			else if(getPieces()[i].getPosition().getCol() == 1)
			{ 
				getPieces()[i].getPosition().setCol(0);
			} 
		}
	}
}
