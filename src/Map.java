import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Map {
	private Color color_matrix [][];
	private int integer_matrix [][];
	int number_of_squares_x, number_of_squares_y;
	int block_width = 10;
	double scale = 0;
	// MAP NEED TO BE AUTO SCALE
	public Map (int maxx, int maxy, int block_width, boolean x){
		this.number_of_squares_x = maxx / block_width ;
		this.number_of_squares_y = maxy / block_width;
		this.block_width = block_width;
		
		color_matrix = new Color [number_of_squares_x][number_of_squares_y];
		integer_matrix = new int [number_of_squares_x][number_of_squares_y];
		
	}
	public Map (int number_of_squares_x, int number_of_squares_y, int block_width)
	{
		this.number_of_squares_x = number_of_squares_x;
		this.number_of_squares_y = number_of_squares_y;
		this.block_width = block_width;
		
		color_matrix = new Color [number_of_squares_x][number_of_squares_y];
		integer_matrix = new int [number_of_squares_x][number_of_squares_y];
		for (int i=0;i<number_of_squares_x;i++){
			for (int j=0;j<number_of_squares_y;j++){
				integer_matrix[i][j] = 0;
				color_matrix[i][j] = Color.orange;
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
	public void inConflict (int [][] matrix){
		
	}
	/* Draws all the squares on the ground */
	public void draw (Graphics g)
	{
		/*
		for (int i =0;i<number_of_squares_x;i++)
		{
			for (int j=0; j<number_of_squares_y;j++)
			{
				if (integer_matrix[i][j] == 0){
					continue;
				}
				Rectangle rect = new Rectangle( 
						( i * block_width ), (j * block_width),
						( (i+1) * block_width ), ((j+1) * block_width));

				((Graphics2D) g).setColor(color_matrix[i][j]);
				((Graphics2D) g).fill (rect);
				((Graphics2D) g).setColor(Color.black);
				((Graphics2D) g).draw (rect);
				
			}
		}
		// Draws all the blocks in the bottom area
		*/
		
	}
}
