package hw3;
import hw3.api.Position;
import hw3.example.SampleGenerator;
import hw3.impl.GridCell;

import java.awt.Color;
import java.util.ArrayList;




public class GridIconTest {
	public static void main(String[] args) { 
		BlockGame game = new BlockGame(new SampleGenerator());
		//System.out.println(game.getGridIcon(-1,-1));
		 GridCell[][] grid = game.getGrid(); // reference to the actual game array
		 grid[2][3] = new GridCell(Color.RED);
		 grid[3][3] = new GridCell(Color.BLUE);
		 grid[2][4] = new GridCell(Color.RED);
		 grid[3][4] = new GridCell(Color.RED);
		 ArrayList<Position> cells = game.determineCellsToCollapse();
		 System.out.println(cells);
		 System.out.println("Expected [(2, 4), (3, 4), (2, 3)] in some order");
	}
}
