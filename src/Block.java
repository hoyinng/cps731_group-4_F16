import java.awt.*;
import java.util.Random;

public class Block {
	int x, y, type, width;
	int blockMatrix [][];
	/* Blocks (must be N X N) */
	int shapeTypes [][][] ={
			{
				{1,1,1,1},
				{1,0,0,0},
				{1,0,0,0},
				{0,0,0,0}
			},
			{
				{0,1},
				{0,1}
			
			},
			{
				{1,1,1},
				{0,1,0},
				{0,1,0},
			
			}
			
	};
	Color clr;
	public Block (int x, int width, Color color)
	{
		this.x=x;
		this.y=0;
		this.clr = color;
		randBlock();
		this.width = width;
	}
	public void randBlock ()
	{
		//Random rand = new Random();
		this.type = 1;// rand.nextInt(3);
		this.blockMatrix = shapeTypes[1];
	}
	public void roateCW ()
	{
		this.blockMatrix = this.getRotateCW();
	}
	public int [][] getRotateCW ()
	{
		int matrix [][] = this.blockMatrix;
		int n = matrix.length;
		for (int layer = 0; layer < n / 2; ++layer) {
	        int first = layer;
	        int last = n - 1 - layer;
	        for(int i = first; i < last; ++i) {
	            int offset = i - first;
	            int top = matrix[first][i]; // save top

	            // left -> top
	            matrix[first][i] = matrix[last-offset][first];          

	            // bottom -> left
	            matrix[last-offset][first] = matrix[last][last - offset]; 

	            // right -> bottom
	            matrix[last][last - offset] = matrix[i][last]; 

	            // top -> right
	            matrix[i][last] = top; // right <- saved top
	        }
	    }
		return matrix;
	}
	public void rotateCCW (){}
	public void moveDown ()
	{
		y+=width;
	}
	public void moveLeft ()
	{
		x-=width;
		System.out.println("Moving right!");
	}
	public void moveRight ()
	{
		x+=width;
		System.out.println("Moving right!");
	}
	public void draw (Graphics g)
	{
		((Graphics2D) g).setColor(this.clr);
		((Graphics2D) g).fillRect(this.x,this.y,width,width);
		
		for (int i =0; i< this.blockMatrix.length;i++){
			for (int j =0; j< this.blockMatrix[i].length;j++){
				if (this.blockMatrix[i][j] == 1) { 
					((Graphics2D) g).setColor(this.clr);
				}else{
					((Graphics2D) g).setColor(Color.WHITE);
				}
				System.out.println((this.x + i * width) + " " + this.y + " " + (this.x + (i+1)*width) + " " + width);
				((Graphics2D) g).fillRect(this.x + i * width,this.y,this.x + (i+1)*width,width);
				((Graphics2D) g).setColor(Color.BLACK);
				((Graphics2D) g).drawRect(this.x + i * width,this.y,this.x + (i+1)*width,width);
				
			}
		}
		
		System.out.println("X is " + this.x);
	}
	public void printMatrix (int [][] matrix)
	{
		for (int i = 0; i < matrix.length;i++)
		{
			for (int j =0; j < matrix[i].length;j++)
			{
				System.out.print(matrix[i][j]);
			}
			System.out.println("");
		}
	}
}
