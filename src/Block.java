import java.awt.*;

public class Block {
	int x, y, type, width;
	Color clr;
	public Block (int x, int y, int shapeType, int width, Color color)
	{
		this.x=x;
		this.y=y;
		this.clr = color;
		this.type = shapeType;
		this.width = width;
	}
	public void rotateCW (){}
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
		System.out.println("X is " + this.x);
	}
}
