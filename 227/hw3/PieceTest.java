package hw3;

import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;

import java.awt.Color;
import java.util.Arrays; 

public class PieceTest {

	public static void main(String[] args)
	 {
	 Color[] colors = new Color[2];
	 colors[0] = Color.RED;
	 colors[1] = Color.BLUE;
	 DiagonalPiece d = new DiagonalPiece(new Position(2, 5), colors);
	 Block[] blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (2, 5), blue at (3, 6)]");
	 System.out.println("Shift Down");
	 d.shiftDown();
	 System.out.println(d.getPosition());
	 System.out.println("Expected (3, 5)");
	 blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (3, 5), blue at (4, 6)]");
	 System.out.println("Transform");
	 d.transform();
	 System.out.println(d.getPosition());
	 System.out.println("Expected (3, 5)");
	 blocks = d.getBlocks();
	 System.out.println(Arrays.toString(blocks));
	 System.out.println("Expected [red at (3, 6), blue at (4, 5)]");
	 }

}
