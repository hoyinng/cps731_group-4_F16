import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import static org.junit.Assert.assertArrayEquals;

public class UnitTest {
	
	int[][][] blocks=Block.shapeTypes;
	Block block = new Block(0,50, new Color(50));
	
	@Test
	public void checkBlockEquality(){
		int[][] a1 = { { 1, 1, 0 }, { 1, 0, 0 }, { 1, 0, 0 } };
		int[][] a2 = a1.clone();
		int[][] a3 = Block.shapeTypes[0];
		
		System.out.println("TestCase SAME TETRIS BLOCK Runned");
		assertArrayEquals(a1,a2);
		assertArrayEquals(a1,a3);
	}
	
	@Test
	public void notTheSameBlockObject(){
		int[][] a1 = { { 1, 1, 0 }, { 1, 0, 0 }, { 1, 0, 0 } };
		int[][] a3 = Block.shapeTypes[0];
		System.out.println("TestCase NOT THE SAME BLOCK OBJECT POINTER Runned");
		assertFalse(a1.equals(a3));
	}
	
	@Test
	public void testHeightOfAllBlock(){
		System.out.println("TestCase TEST HEIGHT OF ALL BLOCK Runned");
		assertEquals(3,block.findHeight(blocks[0])+1);
		assertEquals(3,block.findHeight(blocks[1])+1);
		assertEquals(2,block.findHeight(blocks[2])+1);
		assertEquals(2,block.findHeight(blocks[3])+1);
		assertEquals(3,block.findHeight(blocks[4])+1);
		assertEquals(3,block.findHeight(blocks[5])+1);
		assertEquals(1,block.findHeight(blocks[6])+1);
	}
	
	@Test
	public void checkBlockRotation(){
		System.out.println("TestCase CHECK BLOCK ROTATIONS BY Height Runned");
		Block block = new Block(0,50, new Color(50));
		
		int [][] a = block.getRotateCWM(blocks[0]);
		assertTrue(block.findHeight(a)+1 ==2);
		
		int [][] b = block.getRotateCWM(blocks[1]);
		assertTrue(block.findHeight(b)+1 ==2);
		
		int [][] c = block.getRotateCWM(blocks[2]);
		assertTrue(block.findHeight(c)+1 ==2);
		
		int [][] d = block.getRotateCWM(blocks[3]);
		assertTrue(block.findHeight(d)+1 ==3);
		
		int [][] e = block.getRotateCWM(blocks[4]);
		assertTrue(block.findHeight(e)+1 ==2);
		
		int [][] f = block.getRotateCWM(blocks[5]);
		assertTrue(block.findHeight(f)+1 ==2);
		
		int [][] g = block.getRotateCWM(blocks[6]);
		assertTrue(block.findHeight(g)+1 ==4);
	}

	@Test 
	public void checkBlockMovement(){
		System.out.println("TestCase Check MOVEMENT OF BLOCK Runned");
		Block block = new Block(0,50, new Color(50));
		block.moveDown();
		assertEquals(block.index_y,1);
		block.moveLeft();
		assertEquals(block.index_x,-1);
		block.moveRight();
		block.moveRight();
		block.moveDown();
		assertTrue(block.index_x==1 && block.index_y==2);
	}
}
