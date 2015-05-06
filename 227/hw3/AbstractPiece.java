package hw3;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.Block;

import java.awt.Color;

/**
 * 
 * @author Gabby Ortman
 * 
 * I don't want to study, so here's a joke: 
 * What's the object oriented way to become wealthy?
 * 		Inheritance
 *	
 */
public abstract class AbstractPiece implements IPolyomino
{
	/**
	 * Holds the position of the block
	 */
	private Position position; 
	
	/**
	 * Array of blocks that make up each piece
	 */
	private Block[] blocks;


  /**
   * Hip and fun constructor for each abstract piece.
   * @param position 
   * 	position of each block
   * @param colors 
   * 	color each block holds
   */
  public AbstractPiece(Position position, Color[] colors)
  { 
	   this.position = position; 
  }
  
   /**
    * 
    * Accessor used to ACCESS OUR POLYOMINO, WOAH. 
    * @return 
    * 	our Block[] array, coolio 
    */
  protected Block[] getPieces() { 
	  return blocks; 
  }
  

	@Override
	public Block[] getBlocks() {
		Block[] toReturn = new Block[blocks.length];
	     
		for(int i = 0; i < toReturn.length; i++) { 
			Position p = new Position(blocks[i].getPosition());
		    p.setRow(position.getRow() + p.getRow());
		    p.setCol(position.getCol() + p.getCol());    
		    toReturn[i] = new Block(blocks[i].getColorHint(), p);
		}
	    return toReturn;
	}
	
	
	@Override
	public Position getPosition() {
		return position; 
	}
	
	
	
	@Override
	public void initializePosition(Position givenPosition) {
		for (int i = 0; i < blocks.length; i++)
		{ 
			blocks[i].getPosition().setRow(givenPosition.getRow() + blocks[i].getPosition().getRow()); 
			blocks[i].getPosition().setCol(givenPosition.getCol() + blocks[i].getPosition().getCol()); 
		}
		
	}
	
	
	@Override
	public void shiftDown() {
		//FOR THE LOVE OF GOD GABBY MAKE A CONDITIONAL FOR ILLEGAL STATE EXCEPTION, COOL OKAY 
		position.setRow(position.getRow() + 1);
	}

	
	@Override
	public void shiftLeft() {
		position.setCol(position.getCol() -1);
	}

	
	@Override
	public void shiftRight() {
		position.setCol(position.getCol() + 1);

	}
	
	
	@Override
	public void transform() {}

	
	@Override
	public void cycle() {		
		//save the very last color
		Color temp = blocks[blocks.length-1].getColorHint();
		
		//for each block, set the color of the last block - the current block you're on (minus 1) to the one before it
		for (int i = blocks.length-2; i >= 0; i--)
		{ 
			blocks[i+1].setIcon(blocks[i].getColorHint());
		}
		
		//the set the very first color to the very last color we saved earlier. 
		blocks[0].setIcon(temp);

	}
	
  public IPolyomino clone()
  {
    // The built-in cloning mechanism of the Java runtime will
    // create an object of the correct runtime type.
    AbstractPiece cloned = null;
    try
    {
      cloned = (AbstractPiece) super.clone();
    }
    catch (CloneNotSupportedException e)
    {
      // can't happen in this case
    }
    
    //basically copied from samplegame, except now it has a loop
    cloned.position = new Position(position);
    cloned.blocks = new Block[blocks.length];
    for (int i = 0; i < blocks.length; i++) { 
    	cloned.blocks[i] = new Block(blocks[i]);
    }
    
    return cloned;
  }
  
  public void initializeBlocks(Block[] givenBlocks)
  {
	  blocks = givenBlocks;
  }

}
