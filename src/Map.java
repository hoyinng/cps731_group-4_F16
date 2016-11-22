
public class Map {
	int block_matrix [][]; // 0 : 1 ; false:true for occupation
	int maxx, maxy;
	public Map (int maxx, int maxy)
	{
		this.maxx = maxx;
		this.maxy = maxy;
		block_matrix = new int [maxx][maxy];
	}
	
}
