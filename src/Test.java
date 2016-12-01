import java.util.ArrayList;
import java.util.Collection;
public class Test {
	public static void main (String [] args)
	{
		int[][] a1 = {
				{1,2}, {1,2},{1,3}
		};
		int [][] a2 = a1.clone();
		int[][] a3 = {
				{1,2}, {1,2}
		};
		System.out.println(a1 + " " + a2 + " "+ a3);
		System.out.println("SAME POINTER " + (a1 == a2));
		System.out.println("SAME value " + (a1.equals(a2) ));
		
		System.out.println("SAME POINTER " + (a1 == a1));
		System.out.println("SAME value " + (a1 == a3));
		
		System.out.println((a1.length));
		
	}
}
