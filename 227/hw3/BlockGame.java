/**
 * 
 */
package hw3;

import java.util.ArrayList;
import java.util.Random;

import hw3.api.IPolyominoGenerator;
import hw3.api.Position;
import hw3.impl.GridCell;
import hw3.impl.AbstractBlockGame;
 

/**
 * @author Gabby Ortman
 * 
 * Joke: 
 * Q: Why did the programmer quit her job?
 * A: Because she didn’t get arrays.
 *
 */
public class BlockGame extends AbstractBlockGame {
	
	/**
	 * collapsedCells equals the number of cells to collapse as determined by determineCellsToCollapse.
	 * It also correlates with the score. 
	 */
	private int collapsedCells; 
	
	/**
	 * 
	 * @param generator
	 * 		random Polyomino Generator
	 */
	public BlockGame(IPolyominoGenerator gen) {
		super(gen);
	}
	
	/**
	 * 
	 * @param gen
	 * 		random Polyomino Generator
	 * @param rand
	 * 		random block color generator
	 */
	public BlockGame(IPolyominoGenerator gen, Random rand) { 
		super(gen);
		fillGrid(rand); 
	}

	/**
	 * Helper method to fill the grid, cool. 
	 */
	private void fillGrid(Random rand) { 
		//get the grid
		super.getGrid();
		
		
		
		//for rows 17-2 fill every other one with a random color
		for (int i = super.getGrid().length - 8; i < super.getGrid().length; i++) { 
			boolean shift = (i % 2 == 0);
			for (int j = 0; j < super.getGrid()[0].length; j++) {
				//choose a random color from index 0 - 6
				int randomColor = rand.nextInt(7); 
				if(shift) { 
					if (j % 2 == 0) { 
						//put a block
						super.getGrid()[i][j] = new GridCell(AbstractBlockGame.COLORS[randomColor]); 
					}
				}
				else { 
					if (j % 2 != 0) { 
						//put a block
						super.getGrid()[i][j] = new GridCell(AbstractBlockGame.COLORS[randomColor]); 
					}
				}
			}
		}	
	}
	
	
	/**
	   * Returns an ArrayList including all cells that are part of any collapsible group. 
	   * A collapsible group is defined as a group that have two or more neighbors that match its color
	   * 
	   * It essentially picks a center cell and then picks its rightmost, leftmost, upper and lower neighbor, checks to see if it's within the array and if it's not null
	   * and then checks colors with the center cell. 
	   * 
	   * @return list of locations for cells to be collapsed
	*/
	@Override
	public ArrayList<Position> determineCellsToCollapse() {
	    //victims, as in the blocks that are destined to be eradicated if I implement this correctly
		ArrayList<Position> victims = new ArrayList<Position>();
		
		//to find a collapsible group, search up, down, left and right to see if there's a block with a matching color
		//for each cell of the grid, if j.matches(j+1), add it to victims
		for (int i = 0; i < super.getGrid().length; i++) { 
			for (int j = 0; j < super.getGrid()[0].length; j++) { 
				GridCell inQuestion = super.getGridIcon(i, j); 
				ArrayList<Position> audition_victims = new ArrayList<Position>();
				if (inQuestion != null) {
					
					//if checkBounds returns true and if matches returns true, add it to the audition_victims arraylist 
					if (inArray(i+1, j) && super.getGridIcon(i+1, j).matches(inQuestion)) { 
						audition_victims.add(new Position(i+1, j));
					}
					if (inArray(i-1, j) && super.getGridIcon(i-1, j).matches(inQuestion)) { 
						audition_victims.add(new Position(i-1, j));
					}
					if (inArray(i, j+1) && super.getGridIcon(i, j+1).matches(inQuestion)) { 
						audition_victims.add(new Position(i, j+1));
					}
					if (inArray(i, j-1) && super.getGridIcon(i, j-1).matches(inQuestion)) { 
						audition_victims.add(new Position(i, j-1));
					}
				}
				if(audition_victims.size() >= 2)
				{
					audition_victims.add(new Position(i,j));
					victims.addAll(audition_victims);
				}
			}
		}
		
	    //Now we have to get rid of the stupid duplicates so our stupid score can update correctly ]
		for (int k = 0; k < victims.size(); k++) { 
			Position chosen = victims.get(k); 
			for (int l = k+1; l < victims.size(); l++) { 
				if (chosen.equals(victims.get(l))) { 
					victims.remove(l); 
				}
			}
		}
		
		//update the score 
		collapsedCells += victims.size(); 
	     
		return victims;
	}
	
	 /** 
	   * 
	   * @param row - y coordinate
	   * @param col - x coordinant 
	   * @return
	   * 	boolean of whether or not the given point is in the array and if the given cell is not null 
	   */
	  private boolean inArray(int row, int col)
	  { 
		  int rows = super.getGrid().length;
		  int cols = super.getGrid()[0].length;
		  return (0 <= row && row < rows && 0 <= col && col < cols) && (super.getGridIcon(row, col) != null);
	  }
	  	
	
	/**
	 * The score
	 * @return
	 * 		The...................score
	 */
	@Override
	public int determineScore() {
		return collapsedCells; 
	}

}
