import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public class Map {
	private Color color_matrix [][];
	private int integer_matrix [][];
	int number_of_squares_x, number_of_squares_y;	
	int block_width = 10;
	double scale = 0;
	// MAP NEED TO BE AUTO SCALE
	public Map (int maxx, int maxy, int block_width, boolean x){
		this.number_of_squares_x = 10;//maxx / block_width;
		this.number_of_squares_y = 18;// maxy / block_width;
		this.block_width = block_width;
		
		color_matrix = new Color [number_of_squares_y][number_of_squares_x];
		integer_matrix = new int [number_of_squares_y][number_of_squares_x];
		System.out.println(maxx +  " " + maxy);
		System.out.println(number_of_squares_x +  " " + number_of_squares_y);
		for (int i=0;i<number_of_squares_y;i++){
			for (int j=0;j<number_of_squares_x;j++){
				integer_matrix[i][j] = 0;
				color_matrix[i][j] = Color.white;
			}				
		}
	}
	public Color [][] getColorMatrix ()
	{
		return color_matrix;
	}
	public int [][] getIntegerMatrix ()
	{
		return integer_matrix;
	}
	public void setMatrix (int index_x, int index_y, Color aColor)
	{
		// (TODO) check in bound
		this.integer_matrix [index_x][index_y] = 1;
		this.color_matrix[index_x][index_y] = aColor;
	}
	public void emptyMatrix (int index_x, int index_y, Color aColor)
	{
		setMatrix (index_x,index_y, aColor);
	}
	
	
	public boolean conflict (Block aBlock, int index_x,int index_y){
		int [][] matrix = aBlock.blockMatrix;
		aBlock.findHeight(); aBlock.findHeight();
		int width= aBlock.width; int height = aBlock.height;
		// IF OUT OF BOUND
		
		if ((index_x + width< 0 ) || ( index_y  + height < 0 ) || (index_x + width) >= number_of_squares_x || (index_y + height+1) >= number_of_squares_y){
			return true ;
		}
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				if ( (matrix [i][j] == 1) && (integer_matrix[i+index_y][j+index_x] == 1)) {
					return true;
				}
			}
		}
		return false;
		
	}
	public boolean conflict (int [][] matrix, int width, int height, int index_x,int index_y){
		// IF OUT OF BOUND
		if ((index_x + width< 0 ) || ( index_y  + height < 0 ) || (index_x + width) >= number_of_squares_x || (index_y + height+1) >= number_of_squares_y){
			return true ;
		}
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				if ( (matrix [i][j] == 1) && (integer_matrix[i+index_y][j+index_x] == 1)) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	public void addBlock (Block aBlock, int index_x, int index_y){
		int [][] matrix = aBlock.blockMatrix;
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				if ( (matrix [i][j] == 1) ) {
					this.integer_matrix [i+index_y][j+index_x] = matrix[i][j];
					this.color_matrix[i+index_y][j+index_x] = aBlock.clr;
				}
			}
			
		}
		checkLines(index_y, index_y+matrix.length);
	}
	
	public void checkLines (int y1, int y2)
	{
		boolean clearedRow = true;
		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();
		for (int i=y1; i < y2;i++){
			clearedRow = true;
			for (int j=0; j < this.integer_matrix[i].length;j++){
				if (this.integer_matrix[i][j]==0) { clearedRow = false; break;}
			}
			if (clearedRow) { rowsToClear.add(i); }
		}
		if (!rowsToClear.isEmpty())
			clearLines(rowsToClear);
		rowsToClear.clear();
	}
	public void clearLines (ArrayList<Integer> linesToClear){
		for(int row : linesToClear){
			System.out.println("clearing row: " + row);
			for (int i=0; i < this.integer_matrix[i].length;i++){
				this.integer_matrix[row][i] = 0;
			}
		}
		shiftLines(linesToClear);
	}
	public void shiftLines (ArrayList<Integer> emptyRows){
		int[][] newMatrix = new int[this.integer_matrix.length][this.integer_matrix[0].length];
		Color[][] newColorMatrix = new Color[this.color_matrix.length][this.color_matrix[0].length];
		Collections.reverse(emptyRows);
		int count = 0;
		boolean emptyRowAppeared;
		for (int emptyRow : emptyRows){
			emptyRowAppeared = false;
			System.out.println("Clearing Row = " + emptyRow);
			for(int y = this.integer_matrix.length-1; y > 1; y--){
				for(int x = 0; x < this.integer_matrix[y].length; x++){
					if (y == emptyRow + count){
						emptyRowAppeared = true;
						break;
					}
					if (emptyRowAppeared){
						newMatrix[y+1][x] = this.integer_matrix[y][x];
						newColorMatrix[y+1][x] = this.color_matrix[y][x];
					}
					else{
						newMatrix[y][x] = this.integer_matrix[y][x];
						newColorMatrix[y][x] = this.color_matrix[y][x];
					}
				}
			}
			count++;
			this.integer_matrix = newMatrix;
			this.color_matrix = newColorMatrix;
		}
	}
	/* Draws all the squares on the ground */
	public void draw (Graphics g)
	{
		for (int i = 0; i < integer_matrix.length; i++){
			for (int j = 0; j < integer_matrix[i].length; j++){
				if ( (integer_matrix[i][j] == 1)) {
					((Graphics2D) g).setColor(this.color_matrix[i][j]);
					((Graphics2D) g).fillRect(block_width * j, block_width*i ,block_width,block_width);

					((Graphics2D) g).setColor(Color.black);
					((Graphics2D) g).drawRect(block_width * j,block_width*i ,block_width,block_width);
				}
			}
		}
	}
}
