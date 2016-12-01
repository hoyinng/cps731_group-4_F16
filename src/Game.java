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
    public int maxx, maxy;
    
    Map map, working_map ;
    Block user_block;
    public Game(int maxx, int maxy) {
    	super ();
    	this.maxx=maxx;
    	this.maxy=maxy;
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setFocusable(true);
        addKeyListener (new KeyListener(){
        	public void keyPressed(KeyEvent e){
	    		if (e.getKeyChar() == 'd')
	    		{
	    			System.out.println("hello world");
	    			if (!map.conflict(user_block,user_block.index_x+1,user_block.index_y)){
		    			System.out.println("right");
		    			user_block.moveRight();
	    			}
	    			System.out.println("hello world");
	    		}
	    		else if (e.getKeyChar() == 'a')
	    		{
	    			if (!map.conflict(user_block,user_block.index_x-1,user_block.index_y)){
			    		System.out.println("LEFT");
		    			user_block.moveLeft();
	    			}
	    		}
	    		else if (e.getKeyChar() == 's')
	    		{
	    			if (map.conflict(user_block, user_block.index_x, user_block.index_y+1)){
	            		map.addBlock (user_block,user_block.index_x, user_block.index_y);
	            		user_block = new Block(1,30,Color.black);
	            	}
		    		System.out.println("Down");
	    			user_block.moveDown();
		    		repaint();
	    			
	    		}
	    		else if (e.getKeyChar() == 'w')
	    		{
	    			int [][] newMatrix = user_block.getRotateCWM(user_block.blockMatrix);
	    			if (!map.conflict(newMatrix,user_block.findHeight(newMatrix),
	    					user_block.findWeight(newMatrix), user_block.index_x, user_block.index_y)){
		    			user_block.roateCW();
	    			}
	    			//Transform
	    		}
	    		else if (e.getKeyChar() == 'e')
	    		{
	    			int [][] newMatrix = user_block.getRotateCCWM(user_block.blockMatrix);
	    			if (!map.conflict(newMatrix,user_block.findHeight(newMatrix),
	    					user_block.findWeight(newMatrix), user_block.index_x, user_block.index_y)){
		    			user_block.rotateCCW();
	    			}
	    		}
	    		else if (e.getKeyChar() == 'q'){
	    			user_block = new Block(1,30,Color.black);
	    			
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
            	if (map.conflict(user_block, user_block.index_x, user_block.index_y)){
            		map.addBlock (user_block,user_block.index_x, user_block.index_y-1);
            		user_block = new Block(1,30,Color.black);
            	}
            	//System.out.println("MAP IN CONFLICT : " + map.conflict(user_block));
            	
            }
        };
        this.setFocusable(true);
		//this.map = new Map(40,40,30);
        // HARD CODED screen dimension
		this.map = new Map(600,600,30,true);
        user_block = new Block(1,30,Color.black);
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
        return new Dimension(maxx,maxy);
    }
    
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g);
        user_block.draw(g);
    }
}
