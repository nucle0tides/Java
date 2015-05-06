package hw3;
/**
 * 
 * DiagonalPiece test!
 * 
 * @author Teddy Reinert && Alayne.
 */
import static org.junit.Assert.*;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


import hw3.api.IGame;
import hw3.api.IPolyomino;
import hw3.api.Position;
import hw3.impl.AbstractBlockGame;
import hw3.impl.Block;
import hw3.impl.GridCell;

import org.junit.Before;
import org.junit.Test;

public class HW3Tests {
private DiagonalPiece dp;
private IPiece ip;
private LPiece lp;
private GridCell[][] grid;
private IGame game;
private ArrayList<Position> cellsToCollapse;

	@Before
	public void setUp() throws Exception {
		Color[] c = new Color[2];
		c[0] = AbstractBlockGame.COLORS[0];
		c[1] = AbstractBlockGame.COLORS[1];
		dp = new DiagonalPiece(new Position(-1, 5), c);
	}

	//Test passes if an IllegalArgumentException is thrown, as expected here.
	@Test(expected = IllegalArgumentException.class)
	public void checkIllegalArgumentExceptionDP() {
		Color[] colors = new Color[3];
		colors[0] = Color.RED;
		colors[1] = Color.BLUE;
		colors[2] = Color.ORANGE;
		dp = new DiagonalPiece(new Position(2, 5), colors);
	}
	
	@Test
	public void initializeTestDP() {
		assertEquals(new Position(-1, 5), dp.getPosition());
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
	}
	
	@Test
	public void downTestDP() {
		dp.shiftDown();
		assertEquals("After shifting down 1 time(s): ",new Position(0, 5), dp.getPosition());
		for(int i = 0; i < 5; i++)
			dp.shiftDown();
		assertEquals("After shifting down 6 time(s): ",new Position(5, 5), dp.getPosition());
		for(int i = 0; i < 10; i++)
			dp.shiftDown();
		assertEquals("After shifting down 16 time(s): ",new Position(15, 5), dp.getPosition());
		for(int i = 0; i < 15; i++)
			dp.shiftDown();
		assertEquals("Shiftdown Does not bounds check; After shifting down 31 time(s): ",new Position(30, 5), dp.getPosition());
	}
	@Test
	public void leftTestDP() {
		dp.shiftLeft();
		assertEquals("After shifting left 1 time(s): ",new Position(-1, 4), dp.getPosition());
		assertEquals("[[C, (-1, 4)], [B, (0, 5)]]", Arrays.toString(dp.getBlocks()));
		dp.shiftLeft();
		dp.shiftLeft();
		assertEquals("After shifting left 3 time(s): ",new Position(-1, 2), dp.getPosition());
		assertEquals("[[C, (-1, 2)], [B, (0, 3)]]", Arrays.toString(dp.getBlocks()));
		dp.shiftLeft();
		dp.shiftLeft();
		assertEquals("After shifting left 5 time(s): ",new Position(-1, 0), dp.getPosition());
		assertEquals("[[C, (-1, 0)], [B, (0, 1)]]", Arrays.toString(dp.getBlocks()));
		for(int i = 0; i < 5; i++)
			dp.shiftLeft();
		assertEquals("ShiftLeft does not bounds check; After shifting left 10 time(s): ",new Position(-1, -5), dp.getPosition());
		assertEquals("[[C, (-1, -5)], [B, (0, -4)]]", Arrays.toString(dp.getBlocks()));
	}
	@Test
	public void rightTestDP(){
		dp.shiftRight();
		assertEquals("After shifting Right 1 time(s): ",new Position(-1, 6), dp.getPosition());
		assertEquals("[[C, (-1, 6)], [B, (0, 7)]]",Arrays.toString(dp.getBlocks()));
		dp.shiftRight();
		dp.shiftRight();
		assertEquals("After shifting Right 3 time(s): ",new Position(-1, 8), dp.getPosition());
		assertEquals("[[C, (-1, 8)], [B, (0, 9)]]",Arrays.toString(dp.getBlocks()));
		dp.shiftRight();
		dp.shiftRight();
		assertEquals("After shifting Right 5 time(s): ",new Position(-1, 10), dp.getPosition());
		assertEquals("[[C, (-1, 10)], [B, (0, 11)]]",Arrays.toString(dp.getBlocks()));
		for(int i = 0; i < 5; i++)
			dp.shiftRight();
		assertEquals("ShiftLeft does not bounds check; After shifting Right 10 time(s): ",new Position(-1, 15), dp.getPosition());
		assertEquals("[[C, (-1, 15)], [B, (0, 16)]]",Arrays.toString(dp.getBlocks()));
	}
	
	@Test
	public void cycleTestDP(){
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		dp.cycle();
		assertEquals("[[B, (-1, 5)], [C, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		dp.cycle();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		dp.cycle();
		dp.cycle();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
	}
	
	@Test
	public void TransformTestDP(){
		dp.transform();
		assertEquals("[[C, (-1, 6)], [B, (0, 5)]]", Arrays.toString(dp.getBlocks()));
		dp.transform();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		dp.transform();
		assertEquals("[[C, (-1, 6)], [B, (0, 5)]]", Arrays.toString(dp.getBlocks()));
	}
	
	@Test
	public void cycleAndTransDP(){
		dp.transform();
		assertEquals("[[C, (-1, 6)], [B, (0, 5)]]", Arrays.toString(dp.getBlocks()));
		dp.cycle();
		assertEquals("[[B, (-1, 6)], [C, (0, 5)]]", Arrays.toString(dp.getBlocks()));
		dp.transform();
		assertEquals("[[B, (-1, 5)], [C, (0, 6)]]", Arrays.toString(dp.getBlocks()));
	}
	
	
	/*
	 * Start tests for Ipiece
	 */
	
	@Before
	public void setUpIPiece() throws Exception {
		Color[] c = new Color[3];
		c[0] = AbstractBlockGame.COLORS[0];
		c[1] = AbstractBlockGame.COLORS[1];
		c[2] = AbstractBlockGame.COLORS[3];
		ip = new IPiece(new Position(-2, 5), c);
	}

	//Test passes if an IllegalArgumentException is thrown, as expected here.
	@Test(expected = IllegalArgumentException.class)
	public void checkIllegalArgumentExceptionIPiece() {
		Color[] colors = new Color[4];
		colors[0] = Color.RED;
		colors[1] = Color.BLUE;
		colors[2] = Color.ORANGE;
		colors[3] = Color.GREEN;
		ip = new IPiece(new Position(2, 5), colors);
	}
	
	@Test
	public void downTestIP(){
//		System.out.println(Arrays.toString(ip.getBlocks()));
		ip.shiftDown();
		assertEquals("After shifting down 1 time(s): ",new Position(-1, 5), ip.getPosition());
		assertEquals("[[C, (-1, 6)], [B, (0, 6)], [Y, (1, 6)]]", Arrays.toString(ip.getBlocks()));
		for(int i = 0; i < 5; i++)
			ip.shiftDown();
		assertEquals("After shifting down 6 time(s): ",new Position(4, 5), ip.getPosition());
		assertEquals("[[C, (4, 6)], [B, (5, 6)], [Y, (6, 6)]]", Arrays.toString(ip.getBlocks()));
		for(int i = 0; i < 10; i++)
			ip.shiftDown();
		assertEquals("After shifting down 16 time(s): ",new Position(14, 5), ip.getPosition());
		assertEquals("[[C, (14, 6)], [B, (15, 6)], [Y, (16, 6)]]", Arrays.toString(ip.getBlocks()));
		for(int i = 0; i < 15; i++)
			ip.shiftDown();
		assertEquals("Shiftdown Does not bounds check; After shifting down 31 time(s): ",new Position(29, 5), ip.getPosition());
		assertEquals("[[C, (29, 6)], [B, (30, 6)], [Y, (31, 6)]]", Arrays.toString(ip.getBlocks()));
	}
	
	@Test
	public void leftTestIP() {
		ip.shiftLeft();
		assertEquals("After shifting left 1 time(s): ",new Position(-2, 4), ip.getPosition());
		assertEquals("[[C, (-2, 5)], [B, (-1, 5)], [Y, (0, 5)]]", Arrays.toString(ip.getBlocks()));
		ip.shiftLeft();
		ip.shiftLeft();
		assertEquals("After shifting left 3 time(s): ",new Position(-2, 2), ip.getPosition());
		assertEquals("[[C, (-2, 3)], [B, (-1, 3)], [Y, (0, 3)]]", Arrays.toString(ip.getBlocks()));
		ip.shiftLeft();
		ip.shiftLeft();
		assertEquals("After shifting left 5 time(s): ",new Position(-2, 0), ip.getPosition());
		assertEquals("[[C, (-2, 1)], [B, (-1, 1)], [Y, (0, 1)]]", Arrays.toString(ip.getBlocks()));
		for(int i = 0; i < 5; i++)
			ip.shiftLeft();
		assertEquals("ShiftLeft does not bounds check; After shifting left 10 time(s): ",new Position(-2, -5), ip.getPosition());
		assertEquals("[[C, (-2, -4)], [B, (-1, -4)], [Y, (0, -4)]]", Arrays.toString(ip.getBlocks()));
	}
	
	@Test
	public void rightTestIP(){
		ip.shiftRight();
		assertEquals("After shifting Right 1 time(s): ",new Position(-2, 6), ip.getPosition());
		assertEquals("[[C, (-2, 7)], [B, (-1, 7)], [Y, (0, 7)]]",Arrays.toString(ip.getBlocks()));
		ip.shiftRight();
		ip.shiftRight();
		assertEquals("After shifting Right 3 time(s): ",new Position(-2, 8), ip.getPosition());
		assertEquals("[[C, (-2, 9)], [B, (-1, 9)], [Y, (0, 9)]]",Arrays.toString(ip.getBlocks()));
		ip.shiftRight();
		ip.shiftRight();
		assertEquals("After shifting Right 5 time(s): ",new Position(-2, 10), ip.getPosition());
		assertEquals("[[C, (-2, 11)], [B, (-1, 11)], [Y, (0, 11)]]",Arrays.toString(ip.getBlocks()));
		for(int i = 0; i < 5; i++)
			ip.shiftRight();
		assertEquals("ShiftRight does not bounds check; After shifting Right 10 time(s): ",new Position(-2, 15), ip.getPosition());
		assertEquals("[[C, (-2, 16)], [B, (-1, 16)], [Y, (0, 16)]]",Arrays.toString(ip.getBlocks()));
	}
	
	@Test
	public void TransformTestIP(){
		dp.transform();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		dp.transform();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		dp.transform();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
	}
	
	@Test
	public void cycleTestIP(){
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		ip.cycle();
		assertEquals("[[Y, (-2, 6)], [C, (-1, 6)], [B, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		ip.cycle();
		assertEquals("[[B, (-2, 6)], [Y, (-1, 6)], [C, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		ip.cycle();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
	}
	
	/*
	 * Start LPiece Tests!
	 */
	
	@Before
	public void setUpLP() throws Exception {
		Color[] c = new Color[4];
		c[0] = AbstractBlockGame.COLORS[0];
		c[1] = AbstractBlockGame.COLORS[1];
		c[2] = AbstractBlockGame.COLORS[2];
		c[3] = AbstractBlockGame.COLORS[3];
		lp = new LPiece(new Position(-2, 5), c);
	}

	//Test passes if an IllegalArgumentException is thrown, as expected here.
	@Test(expected = IllegalArgumentException.class)
	public void checkIllegalArgumentExceptionLP() {
		Color[] colors = new Color[5];
		colors[0] = AbstractBlockGame.COLORS[0];
		colors[1] = AbstractBlockGame.COLORS[1];
		colors[2] = AbstractBlockGame.COLORS[2];
		colors[3] = AbstractBlockGame.COLORS[3];
		colors[4] = AbstractBlockGame.COLORS[4];
		lp = new LPiece(new Position(2, 5), colors);
	}
	
	@Test
	public void downTestLP(){
		lp.shiftDown();
		assertEquals("After shifting down 1 time(s): ",new Position(-1, 5), lp.getPosition());
		assertEquals("[[C, (-1, 5)], [B, (-1, 6)], [O, (0, 6)], [Y, (1, 6)]]", Arrays.toString(lp.getBlocks()));
		for(int i = 0; i < 5; i++)
			lp.shiftDown();
		assertEquals("After shifting down 6 time(s): ",new Position(4, 5), lp.getPosition());
		assertEquals("[[C, (4, 5)], [B, (4, 6)], [O, (5, 6)], [Y, (6, 6)]]", Arrays.toString(lp.getBlocks()));
		for(int i = 0; i < 10; i++)
			lp.shiftDown();
		assertEquals("After shifting down 16 time(s): ",new Position(14, 5), lp.getPosition());
		assertEquals("[[C, (14, 5)], [B, (14, 6)], [O, (15, 6)], [Y, (16, 6)]]", Arrays.toString(lp.getBlocks()));
		for(int i = 0; i < 15; i++)
			lp.shiftDown();
		assertEquals("Shiftdown Does not bounds check; After shifting down 31 time(s): ",new Position(29, 5), lp.getPosition());
		assertEquals("[[C, (29, 5)], [B, (29, 6)], [O, (30, 6)], [Y, (31, 6)]]", Arrays.toString(lp.getBlocks()));
	}
	
	@Test
	public void leftTestLP() {
		lp.shiftLeft();
		assertEquals("After shifting left 1 time(s): ",new Position(-2, 4), lp.getPosition());
		assertEquals("[[C, (-2, 4)], [B, (-2, 5)], [O, (-1, 5)], [Y, (0, 5)]]", Arrays.toString(lp.getBlocks()));
		lp.shiftLeft();
		lp.shiftLeft();
		assertEquals("After shifting left 3 time(s): ",new Position(-2, 2), lp.getPosition());
		assertEquals("[[C, (-2, 2)], [B, (-2, 3)], [O, (-1, 3)], [Y, (0, 3)]]", Arrays.toString(lp.getBlocks()));
		lp.shiftLeft();
		lp.shiftLeft();
		assertEquals("After shifting left 5 time(s): ",new Position(-2, 0), lp.getPosition());
		assertEquals("[[C, (-2, 0)], [B, (-2, 1)], [O, (-1, 1)], [Y, (0, 1)]]", Arrays.toString(lp.getBlocks()));
		for(int i = 0; i < 5; i++)
			lp.shiftLeft();
		assertEquals("ShiftLeft does not bounds check; After shifting left 10 time(s): ",new Position(-2, -5), lp.getPosition());
		assertEquals("[[C, (-2, -5)], [B, (-2, -4)], [O, (-1, -4)], [Y, (0, -4)]]", Arrays.toString(lp.getBlocks()));
	}
	
	@Test
	public void TransformTestLP(){
		lp.transform();
		assertEquals("[[C, (-2, 7)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[C, (-2, 7)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
	}
	
	@Test
	public void cycleTestLP(){
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[Y, (-2, 5)], [C, (-2, 6)], [B, (-1, 6)], [O, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[O, (-2, 5)], [Y, (-2, 6)], [C, (-1, 6)], [B, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[B, (-2, 5)], [O, (-2, 6)], [Y, (-1, 6)], [C, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
	}
	
	@Test
	public void cycleAndTransLP(){
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[Y, (-2, 5)], [C, (-2, 6)], [B, (-1, 6)], [O, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[Y, (-2, 7)], [C, (-2, 6)], [B, (-1, 6)], [O, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[O, (-2, 7)], [Y, (-2, 6)], [C, (-1, 6)], [B, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[O, (-2, 5)], [Y, (-2, 6)], [C, (-1, 6)], [B, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[B, (-2, 5)], [O, (-2, 6)], [Y, (-1, 6)], [C, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[B, (-2, 7)], [O, (-2, 6)], [Y, (-1, 6)], [C, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.cycle();
		assertEquals("[[C, (-2, 7)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		lp.transform();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
	}
	
	@Test
	public void cloneTestDP(){
		DiagonalPiece dpClone = (DiagonalPiece) dp.clone();
		dpClone.shiftDown();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		assertEquals("[[C, (0, 5)], [B, (1, 6)]]", Arrays.toString(dpClone.getBlocks()));
		dpClone.shiftRight();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		assertEquals("[[C, (0, 6)], [B, (1, 7)]]", Arrays.toString(dpClone.getBlocks()));
		dpClone.shiftLeft();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		assertEquals("[[C, (0, 5)], [B, (1, 6)]]", Arrays.toString(dpClone.getBlocks()));
		dpClone.cycle();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		assertEquals("[[B, (0, 5)], [C, (1, 6)]]", Arrays.toString(dpClone.getBlocks()));
		dpClone.transform();
		assertEquals("[[C, (-1, 5)], [B, (0, 6)]]", Arrays.toString(dp.getBlocks()));
		assertEquals("[[B, (0, 6)], [C, (1, 5)]]", Arrays.toString(dpClone.getBlocks()));
		
	}
	
	@Test
	public void cloneTestIP(){
		IPiece ipClone = (IPiece) ip.clone();
		ipClone.shiftDown();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		assertEquals("[[C, (-1, 6)], [B, (0, 6)], [Y, (1, 6)]]", Arrays.toString(ipClone.getBlocks()));
		ipClone.shiftLeft();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		assertEquals("[[C, (-1, 5)], [B, (0, 5)], [Y, (1, 5)]]", Arrays.toString(ipClone.getBlocks()));
		ipClone.shiftRight();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		assertEquals("[[C, (-1, 6)], [B, (0, 6)], [Y, (1, 6)]]", Arrays.toString(ipClone.getBlocks()));
		ipClone.cycle();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		assertEquals("[[Y, (-1, 6)], [C, (0, 6)], [B, (1, 6)]]", Arrays.toString(ipClone.getBlocks()));
		ipClone.transform();
		assertEquals("[[C, (-2, 6)], [B, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(ip.getBlocks()));
		assertEquals("[[Y, (-1, 6)], [C, (0, 6)], [B, (1, 6)]]", Arrays.toString(ipClone.getBlocks()));
	}
	
	@Test
	public void cloneTestLP(){
		LPiece lpClone = (LPiece) lp.clone();
		lpClone.shiftDown();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		assertEquals("[[C, (-1, 5)], [B, (-1, 6)], [O, (0, 6)], [Y, (1, 6)]]", Arrays.toString(lpClone.getBlocks()));
		lpClone.shiftLeft();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		assertEquals("[[C, (-1, 4)], [B, (-1, 5)], [O, (0, 5)], [Y, (1, 5)]]", Arrays.toString(lpClone.getBlocks()));
		lpClone.shiftRight();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		assertEquals("[[C, (-1, 5)], [B, (-1, 6)], [O, (0, 6)], [Y, (1, 6)]]", Arrays.toString(lpClone.getBlocks()));
		lpClone.cycle();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		assertEquals("[[Y, (-1, 5)], [C, (-1, 6)], [B, (0, 6)], [O, (1, 6)]]", Arrays.toString(lpClone.getBlocks()));
		lpClone.transform();
		assertEquals("[[C, (-2, 5)], [B, (-2, 6)], [O, (-1, 6)], [Y, (0, 6)]]", Arrays.toString(lp.getBlocks()));
		assertEquals("[[Y, (-1, 7)], [C, (-1, 6)], [B, (0, 6)], [O, (1, 6)]]", Arrays.toString(lpClone.getBlocks()));
	}
	//will test the empty one.
	@Before
	public void BlockGameSetup(){
		Random rand = new Random(100);
		game = new BlockGame(new BasicGenerator(rand));
		grid = ((AbstractBlockGame) game).getGrid();
	}
	
	

	@Test
	public void EmptyGridTest(){
		boolean status = true;
		 for (int row = 0; row < grid.length; row += 1)
		    {
		      for (int col = 0; col < grid[0].length; col += 1)
		      {
		    	if(grid[row][col] != null)
		    		status = false;
		      }
		    }
		 assertEquals("The grid should be empty for the constructor without a random",true, status);
	}
	
	
	@Test
	public void CollapseTest(){
		grid[0][1] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[0][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[1][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[2][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		cellsToCollapse = ((AbstractBlockGame) game).determineCellsToCollapse();
		assertEquals("Should remove duplicates", 4 , cellsToCollapse.size());
		assertEquals(4 , game.getScore());

		
		grid[23][11] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[22][11] = new GridCell(AbstractBlockGame.COLORS[1]);
		grid[23][10] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[22][10] = new GridCell(AbstractBlockGame.COLORS[0]);

		cellsToCollapse = ((AbstractBlockGame) game).determineCellsToCollapse();
		//this will be 7 since I cannot alter the game's state. It will not be able to actually collapse.
		assertEquals("Should not remove wrong color", 7 , cellsToCollapse.size());
		//since we cannot actually get rid of them, the score will be inaccurate, however, I have compensated for it. 
		assertEquals(11 , game.getScore());
	}
	
	@Test
	public void CollapseTestDiag(){
		grid[0][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[1][1] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[2][2] = new GridCell(AbstractBlockGame.COLORS[0]);
		cellsToCollapse = ((AbstractBlockGame) game).determineCellsToCollapse();
		assertEquals("Should not collapse diagonals", 0 , cellsToCollapse.size());
		assertEquals(0 , game.getScore());
	}
	
	@Test
	public void CollapseTestColor(){
		grid[0][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[0][1] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[0][2] = new GridCell(AbstractBlockGame.COLORS[1]);
		grid[1][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		grid[1][1] = new GridCell(AbstractBlockGame.COLORS[1]);
		grid[2][2] = new GridCell(AbstractBlockGame.COLORS[3]);
		cellsToCollapse = ((AbstractBlockGame) game).determineCellsToCollapse();
		assertEquals("Should not collapse diagonals", 3 , cellsToCollapse.size());
		assertEquals(3 , game.getScore());
	}
	
	@Test
	public void CollapseTestDuplicates(){
		for(int j = 0; j < 11; j++)
			grid[0][j] = new GridCell(AbstractBlockGame.COLORS[0]);
		for(int i = 1; i < 20; i++)
			grid[i][0] = new GridCell(AbstractBlockGame.COLORS[0]);
		cellsToCollapse = ((AbstractBlockGame) game).determineCellsToCollapse();
		assertEquals("Must remove duplicates before setting score!", 30, game.getScore());
	}
	
	
	@Test
	public void pieceProbability(){
		double[] count = new double[3];
		double[] colorCount = new double[AbstractBlockGame.COLORS.length];
		Block[] tempArr;
		double numberBlockGenerated = 0;
		
		//I decided not to seed it, it seems to work without a seed. This will help
		//over come the implimentation in your code!
		//It doesnt matter how you did it, the percentages need to be the same.
		BasicGenerator gen = new BasicGenerator(new Random());
		for(int i = 0; i < 1000000; i++){
			IPolyomino temp = gen.getNext();
			if(temp instanceof DiagonalPiece){
				count[0] += 1.0;
				numberBlockGenerated += 2.0;
				
				tempArr = temp.getBlocks();
				for(int k = 0; k < tempArr.length; k++){
					colorCount[colorIndexOf(tempArr[k].getColorHint())] += 1;
				}
			}
			else if(temp instanceof LPiece){
				count[1] += 1.0;
				numberBlockGenerated += 4.0;
				
				tempArr = temp.getBlocks();
				for(int k = 0; k < tempArr.length; k++){
					colorCount[colorIndexOf(tempArr[k].getColorHint())] += 1;
				}
			}
			else{
				count[2] += 1.0;	
				numberBlockGenerated += 3.0;
				
				tempArr = temp.getBlocks();
				for(int k = 0; k < tempArr.length; k++){
					colorCount[colorIndexOf(tempArr[k].getColorHint())] += 1;
				}
			}
		}
		assertEquals("DiagonalPiece should have a 20% chance", .2, count[0]/1000000, .025);
		assertEquals("LPiece should have a 20% chance", .2, count[1]/1000000, .025);
		assertEquals("IPiece should have a 60% chance", .6, count[2]/1000000, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[0]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[1]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[2]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[3]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[4]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[5]/numberBlockGenerated, .025);
		assertEquals("Each Color should have an equal chance of being generated", 1.0/7.0, colorCount[6]/numberBlockGenerated, .025);
	}
	
/**
 * Will return the index that the color appears at. 
 * @param c
 * @return index it appears at in the colors list
 */
	public int colorIndexOf(Color c){
		for(int i = 0; i < AbstractBlockGame.COLORS.length; i++){
			if(c == AbstractBlockGame.COLORS[i])
				return i;
		}
		return -1;
	}
	
	/* Just copied it from GridTest to avoid problems.
	 * us if you are having trouble visualizing.
	 */
	 public static void printGrid(GridCell[][] grid)
	  {
	    for (int row = 0; row < grid.length; row += 1)
	    {
	      for (int col = 0; col < grid[0].length; col += 1)
	      {
	        String s = findShortColorName(grid[row][col]);
	        System.out.print(s + " ");
	      }
	      System.out.println();
	    }
	  }	 
	 
	  /**
	   * Returns a one-letter abbreviation for the colors listed in
	   * AbstractBlockGame.
	   * @param c
	   * @return
	   */
	  public static String findShortColorName(GridCell c)
	  {
	    if (c == null) return "-";
	    Color color = c.getColorHint();
	    String colorName = "?";     
	    
	    // look for a color name that is used in abstract block game
	    Color[] knownColors = AbstractBlockGame.COLORS;
	    String[] knownColorNames = AbstractBlockGame.COLOR_NAMES;
	    
	    for (int i = 0; i < knownColors.length; i += 1)
	    {
	      if (knownColors[i].equals(color))
	      {
	        colorName = knownColorNames[i];
	        break;
	      }
	    }
	    return colorName;
	  }
	  
}
