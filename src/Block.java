import java.awt.*;
import java.util.Random;

public class Block {
	Color clr;
	/* Height is square level (Array Index) closest to the ground */ 
	int x, y, type, width, height;
	int blockMatrix [][];
	int index_x, index_y;
	/* Blocks (must be N X N) */
	int shapeTypes [][][] ={
			{
				{1,1,0,0},
				{1,0,0,0},
				{1,0,0,0},
				{1,0,0,0}
			},{
				{1,1,0,0},
				{0,1,0,0},
				{0,1,0,0},
				{0,1,0,0}
			},{
				{1,1},
				{1,1}
			
			},{
				{1,1,1},
				{0,1,0},
				{0,1,0},
			
			},{
				{0,1,0},
				{1,1,0},
				{1,0,0}
			},{
				{1,0,0},
				{1,1,0},
				{0,1,0}
			},{
				{1,1,1,1},
				{0,0,0,0},
				{0,0,0,0},
				{0,0,0,0}
			}
			
	};
	public Block (int x, int width, Color color)
	{
		this.x=x;
		this.y=0;
		this.index_x = 0;
		this.index_y = 0;
		
		this.clr = Color.RED;//color;
		this.width = width;
		randBlock();
		
	}
	public void findHeight (){} // (TODO)
	public void randBlock ()
	{
		// PRESET VALUE (TODO)
		// REMOVE ALL PRESET AFTER
		//Random rand = new Random();
		this.type = 3;// rand.nextInt(3);
		this.blockMatrix = shapeTypes[this.type].clone();
	}
	public void roateCW ()
	{
		// getRotateCW and getRotateCWM is modifying the matrix by reference
		//this.printMatrix(this.getRotateCWM(this.blockMatrix));
		this.blockMatrix = this.getRotateCW();
		return;
	}
	public void rotateCCW (){
		this.blockMatrix = this.getRotateCCW();
		return;
	}
	// NEED TO DELETE AFTER
	public int [][] getRotateCWM (int matrix[][])
	{
		int n = matrix.length;
		for (int layer = 0; layer < n / 2; ++layer) {
	        int first = layer;
	        int last = n - 1 - layer;
	        for(int i = first; i < last; ++i) {
	            int offset = i - first;
	            int top = matrix[first][i]; // save top
	            // top = left, left = bottom, bottom = right, right = saved_top;
	            
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
	public int [][] getRotateCW ()
	{
		int matrix [][] = this.blockMatrix.clone();//(new int  [this.blockMatrix.length][this.blockMatrix[0].length]);
		System.out.println("THEY ARE THE SAME POINTER " + (matrix == this.blockMatrix.clone()));
		System.out.println(matrix + " " + this.blockMatrix);
		int n = matrix.length;
		for (int layer = 0; layer < n / 2; ++layer) {
	        int first = layer;
	        int last = n - 1 - layer;
	        for(int i = first; i < last; ++i) {
	            int offset = i - first;
	            int top = matrix[first][i]; // save top
	            // top = left, left = bottom, bottom = right, right = saved_top;
	            
	            matrix[first][i] = matrix[last-offset][first];          
	            matrix[last-offset][first] = matrix[last][last - offset]; 
	            matrix[last][last - offset] = matrix[i][last]; 
	            matrix[i][last] = top; 
	        }
	    }
		return matrix;
	}
	public int [][] getRotateCCW ()
	{
		int matrix [][] = this.blockMatrix.clone();
		int n= matrix.length;
		for (int layer = 0; layer < n / 2; ++layer) {
	        int first = layer;
	        int last = n - 1 - layer;
	        for(int i = first; i < last; ++i) {
	            int offset = i - first;
	            int bottom = matrix[last][last - offset];
	            matrix[last][last - offset] = matrix[last-offset][first] ;
	            matrix[last-offset][first] =  matrix[first][i];
	            matrix[first][i] = matrix[i][last]; 
	            matrix[i][last] = bottom;
	        }
	    }
		return matrix;
		
	}
	public void moveDown ()
	{
		index_y += 1;
		y=width*index_y;
	}
	public void moveLeft ()
	{
		index_x -=1;
		x=width*index_x;
		System.out.println("Moving right!");
	}
	public void moveRight ()
	{
		index_x +=1;
		x=width*index_x;
		System.out.println("Moving right!");
	}
	public void draw (Graphics g)
	{
		for (int i =0; i< this.blockMatrix.length;i++){
			for (int j =0; j< this.blockMatrix[i].length;j++){
				// if this section of the blocks have no squares
				if (this.blockMatrix[i][j] == 0){
					((Graphics2D) g).setColor(Color.white);
					continue;
				}else{

					((Graphics2D) g).setColor(this.clr);
				}
				((Graphics2D) g).fillRect(this.x + width * j,this.y + width*i,width,width);

				((Graphics2D) g).setColor(Color.black);
				((Graphics2D) g).drawRect(this.x + width * j,this.y + width*i,width,width);
				// Additional draw methods (TODO)
			}
		}
		
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
