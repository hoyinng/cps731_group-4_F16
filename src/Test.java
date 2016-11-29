import java.util.ArrayList;
import java.util.Collection;
public class Test {
	public static void main (String [] args)
	{
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
		System.out.println(Collection.reverse(shapeTypes[0]));
		
	}
}
