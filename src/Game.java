import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;


public class Game extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    private int speed = 2000;
    public boolean frozen;
    public Timer myTimer;	// Timer Counting Down
    
    
    Map map, working_map ;
    Block user_block;
    public Game() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setFocusable(true);
		this.map = new Map(40,40,30);
        addKeyListener (new KeyListener(){
        	public void keyPressed(KeyEvent e){
	    		if (e.getKeyChar() == 'd')
	    		{
	    			System.out.println("right");
	    			user_block.moveRight();
	    		}
	    		else if (e.getKeyChar() == 'a')
	    		{
		    		System.out.println("LEFT");
	    			user_block.moveLeft();
	    		}
	    		else if (e.getKeyChar() == 's')
	    		{
		    		System.out.println("Down");
	    			user_block.moveDown();
	    		}
	    		else if (e.getKeyChar() == 'w')
	    		{
	    			System.out.println("Transform");
	    			user_block.roateCW();
	    			//Transform
	    		}
	    		repaint();
        	}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
        
        ActionListener taskPerformer = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
            	user_block.moveDown();
            	repaint();
            }
        };
        user_block = new Block(1,30,Color.white);
        myTimer = new Timer (1000, taskPerformer);
    }
    public void main_loop ()
    {
    	this.frozen = false;
    	myTimer.start() ;
    	while (true)
    	{
    		
    	}
    }
    public void freeze ()
    {
    	this.frozen = true;
    	myTimer.stop();
    }
    public void reset ()
    {
    	
    }
    

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g);
        user_block.draw(g);
    }
}
