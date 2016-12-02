import java.awt.*;
import java.util.Random;

public class Block {
	Color clr;
	/* Height is square level (Array Index) closest to the ground */ 
	int x, y, type, width, height;
	int box_width;
	int blockMatrix [][];
	int index_x, index_y;
	/* Blocks (must be N X N) */
	int shapeTypes [][][] ={
			{
				{1,1,0},
				{1,0,0},
				{1,0,0}
			},{
				{1,1,0,},
				{0,1,0,},
				{0,1,0,}
			},{
				{1,1},
				{1,1}
			
			},{
				{1,1,1},
				{0,1,0},
				{0,0,0},
			
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
	Color colorArr [] = {
			Color.blue, Color.orange, Color.yellow,
			Color.magenta, Color.red, Color.green, Color.CYAN
	};
	public Block (int x, int box_width, Color color)
	{
		this.x=x;
		this.y=0;
		this.index_x = 0;
		this.index_y = 0;

		this.clr = Color.black;
		this.box_width = box_width;
		
		randBlock();
		
	}
	public void randBlock ()
	{
		// PRESET VALUE (TODO)
		// REMOVE ALL PRESET AFTER
		Random rand = new Random();
		this.type =  rand.nextInt(7);
		this.blockMatrix = shapeTypes[this.type].clone();

		this.clr = colorArr[this.type];//Color.RED;//color;
		this.width =0;
		this.height =0;
		findHeight();
		findWeight();
	}
	public int findHeight (int [][] matrix){
		int aHeight =0;
		for (int i = 0;i < matrix[0].length;i++){
			for (int j =0; j < matrix.length;j++){
				if ((matrix[i][j] == 1) && ( i > aHeight)){
					aHeight = i;
				}
			}
		}
		return aHeight;
		
	}

	public int findWeight (int [][] matrix){
		int width =0;
		for (int i = 0;i < matrix[0].length;i++){
			for (int j =0; j < matrix.length;j++){
				if ((matrix[i][j] == 1) && ( i > width)){
					width = i;
				}
			}
		}
		return width;
		
	}
	public void findHeight (){
		this.height =0;
		for (int i = 0;i < this.blockMatrix[0].length;i++){
			for (int j =0; j < this.blockMatrix.length;j++){
				if ((this.blockMatrix[i][j] == 1) && ( i > this.height)){
					this.height = i;
				}
			}
		}
	}
	public void findWeight (){
		this.width =0;
		for (int i = 0;i < this.blockMatrix.length;i++){
			for (int j =0; j < this.blockMatrix[0].length;j++){
				if ((this.blockMatrix[i][j] == 1) && ( j > this.width)){
					this.width = j;
				}
			}
		}
	}
	public void roateCW ()
	{
		// getRotateCW and getRotateCWM is modifying the matrix by reference
		this.printMatrix(this.getRotateCWM(this.blockMatrix.clone()));
		this.blockMatrix = this.getRotateCWM(this.blockMatrix.clone());
		findHeight();
		findWeight();
		return;
	}
	public void rotateCCW (){
		this.printMatrix(this.getRotateCCWM(this.blockMatrix.clone()));
		this.blockMatrix = this.getRotateCCWM(this.blockMatrix.clone());
		findHeight();
		findWeight();
		return;
	}
	public void copyTo (int src[][], int dest[][]){
		for (int i = 0 ; i < src.length;i++)
			for (int j = 0 ; j < src[0].length;j++){
				dest[i][j]= src[i][j];
			}
	}
	public int [][] getRotateCWM (int matrix1[][])
	{
		int [][]matrix = new int [matrix1.length][matrix1[0].length] ;
		copyTo(matrix1, matrix);
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
	public int [][] getRotateCCWM (int matrix1[][]){

		int [][]matrix = new int [matrix1.length][matrix1[0].length] ;
		copyTo(matrix1, matrix);
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
		y=box_width*index_y;
	}
	public void moveLeft ()
	{
		index_x -=1;
		x=box_width*index_x;
		System.out.println("Moving right!");
	}
	public void moveRight ()
	{
		index_x +=1;
		x=box_width*index_x;
		System.out.println("Moving right!");
	}
	public void draw (Graphics g)
	{
		for (int j =0; j< this.blockMatrix.length;j++){
			for (int i =0; i< this.blockMatrix[j].length;i++){
				// if this section of the blocks have no squares
				if (this.blockMatrix[i][j] == 0){
					((Graphics2D) g).setColor(Color.white);
					continue;
				}else{

					((Graphics2D) g).setColor(this.clr);
				}
				((Graphics2D) g).fillRect(this.x + box_width * j,this.y + box_width*i,box_width,box_width);

				((Graphics2D) g).setColor(Color.black);
				((Graphics2D) g).drawRect(this.x + box_width * j,this.y + box_width*i,box_width,box_width);
				// Additional draw methods (TODO)
			}
		}	
	}

	public void drawInNextBlockArea(Graphics g)
	{
		for (int j =0; j< this.blockMatrix.length;j++){
			for (int i =0; i< this.blockMatrix[j].length;i++){
				// if this section of the blocks have no squares
				if (this.blockMatrix[i][j] == 0){
					((Graphics2D) g).setColor(Color.white);
					continue;
				}else{

					((Graphics2D) g).setColor(this.clr);
				}
				((Graphics2D) g).fillRect(((10*25) + (Tetris.FRAME_WIDTH - (10*25))/2) - (MainMenu.BTN_WIDTH/3) + box_width * j + ((MainMenu.BTN_WIDTH/3)/2),65 + box_width*i + ((MainMenu.BTN_WIDTH/3)/2),box_width,box_width);

				((Graphics2D) g).setColor(Color.black);
				((Graphics2D) g).drawRect(((10*25) + (Tetris.FRAME_WIDTH - (10*25))/2) - (MainMenu.BTN_WIDTH/3) + box_width * j + ((MainMenu.BTN_WIDTH/3)/2),65 + box_width*i + ((MainMenu.BTN_WIDTH/3)/2),box_width,box_width);
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
