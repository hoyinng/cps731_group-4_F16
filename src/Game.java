import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;

public class Game extends JPanel {

    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    private int speed = 1300;
    private final int SPEED_DECREMENT = 100;
    private final int MAX_LEVEL = 10;
    public boolean frozen;
    public Timer myTimer;	// Timer Counting Down
    public int maxx, maxy;
    
    public boolean gameOver;
	ActionListener taskPerformer;
    
    Map map, working_map ;

    Block user_block;

	private CustomButton pauseBtn, menuBtn;

	public int returnState = Tetris.STAGE_GAME;
	public LinkedList<Block> user_blocks;
	private final int NUM_OF_PRELOADED_BLOCKS = 3;
	private final int SCORE_PER_LINE = 100;
	public int score, level, linesCleared, totalLinesCleared;
	JLabel currentScoreLbl, currentLevelLbl;
	public String name;
    public Game(int maxx, int maxy) {
    	super ();
	
    	this.maxx=maxx;
    	this.maxy=maxy;
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setFocusable(true);
		
		setBlocks();
		this.gameOver = false;
		setBackground(MainMenu.TETRIS_BLUE);
        addKeyListener (new KeyListener(){
        	public void keyPressed(KeyEvent e){
	    		if (e.getKeyChar() == 'd')
	    		{
	    			if (!map.conflict(user_block,user_block.index_x+1,user_block.index_y)){
		    			System.out.println("right");
		    			user_block.moveRight();
	    			}
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
	    				if (user_block.index_y <=0) {  
    						gameOver = true;
    					}
	            		linesCleared = map.addBlock (user_block,user_block.index_x, user_block.index_y);
	            		updateStats(linesCleared);
	            		user_block = getNextBlockRemoveLast();
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
	    			user_block = getNextBlockRemoveLast();
	    			
	    		}
				//SPACEBAR TO DROP THE BLOCK DOWN RIGHT AWAY
				else if(e.getKeyCode()==KeyEvent.VK_SPACE){
					while(true){
              			if (map.conflict(user_block, user_block.index_x, user_block.index_y+1)){
    	    				if (user_block.index_y <=1) { 
    	    						gameOver = true;
    	    					}
    	        				
	            			linesCleared = map.addBlock (user_block,user_block.index_x, user_block.index_y);
	            			updateStats(linesCleared);
	            			user_block = getNextBlockRemoveLast();
							break;
	            		}
		    			System.out.println("Down");
	    				user_block.moveDown();
					}
		    		repaint();
         		}
	    		repaint();
        	}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        });
        
        taskPerformer = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
            	user_block.moveDown();
            	repaint();
            	if (map.conflict(user_block, user_block.index_x, user_block.index_y)){
    				if (user_block.index_y <=1) 
    					{
    						gameOver = true;
    					}
    				
            		linesCleared = map.addBlock (user_block,user_block.index_x, user_block.index_y-1);
            		updateStats(linesCleared);
            		user_block = getNextBlockRemoveLast();
            	}
            	//System.out.println("MAP IN CONFLICT : " + map.conflict(user_block));
            	
            }
        };
        this.setFocusable(true);
        // HARD CODED screen dimension
		this.map = new Map(600,600,30,true);
        user_block = getNextBlockRemoveLast();
        myTimer = new Timer (speed, taskPerformer);

		setPanel();
    }

	public void setBlocks(){
		user_blocks = new LinkedList<Block>();
		for (int i = 0; i < NUM_OF_PRELOADED_BLOCKS; i++){
			user_blocks.addLast(new Block(1, 30, Color.black));
		}
	}

	public Block getNextBlockRemoveLast(){
		user_blocks.addLast(new Block(1, 30, Color.black));
		user_blocks.removeFirst();
		return user_blocks.getFirst();
	}

	public void updateStats(int linesCleared){
		score += linesCleared * SCORE_PER_LINE;
		totalLinesCleared += linesCleared;
		level = totalLinesCleared/10 + 1;
		if (!currentLevelLbl.getText().equals(Integer.toString(level))){
			if (level < MAX_LEVEL){
				speed -= SPEED_DECREMENT;
				myTimer.setDelay(speed);
				currentLevelLbl.setText(Integer.toString(level));
			}
		}
		currentScoreLbl.setText(Integer.toString(score));
	}

    public int main_loop ()
    {
		//myTimer.start();
		requestFocus();
		int temp = returnState;	

		if (temp == Tetris.STAGE_PAUSE) myTimer.stop();
		else myTimer.start();

		if (temp == Tetris.STAGE_MENU) myTimer.stop();
		else myTimer.start();
			
		returnState = Tetris.STAGE_GAME;

		if (gameOver){
			returnState = Tetris.STAGE_MENU;
			myTimer.stop();
			boolean newHighscore = manageHighscore();
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Game Over :(");
			if (newHighscore) {
				JOptionPane.showMessageDialog(frame, "New Highscore: " + score + "!!!");
			}
			this.reset();
		}
		return temp;
    }

	public boolean manageHighscore(){
		try{
			File f = new File("scoreboard.txt");
			Scanner s = new Scanner(f);

			int fileScore;
			int line = 99;
			boolean hasHighscore = false;
			for (int i = 0; i < 10; i++){
				s.next();
				fileScore = s.nextInt();

				if (score > fileScore){
					line = i;
					hasHighscore = true;
					break;
				}
				s.nextLine();
			}

			if (hasHighscore){
				s = new Scanner(f);
				PrintWriter writer = new PrintWriter("scoreboardTemp.txt");
				for (int i = 0; i < 10; i++){
					if (line == i)
						writer.println(MainMenu.name + " " + score);
					else
						writer.println(s.next() + " " + s.next());
				}
				writer.close();
				File f2 = new File("scoreboardTemp.txt");
				f2.renameTo(f);
				return true;
			}

		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}

    public void freeze ()
    {
    	this.frozen = true;
    	myTimer.stop();
    }

	public void unFreeze(){
		this.frozen = false;
		myTimer.start();
	}
    public void reset ()
	{
    		score = 0;
		level = 0;
		totalLinesCleared = 0;
		linesCleared = 0;
		currentScoreLbl.setText("0");
		currentLevelLbl.setText("1");
		
		this.map = new Map(600,600,30,true);
		user_block = getNextBlockRemoveLast ();
		this.gameOver = false;
		setBlocks ();
    	}
    
    
    public Dimension getPreferredSize() {
        return new Dimension(maxx,maxy);
    }

	public void setPanel(){
		setLayout(null);

		frozen = false;
		pauseBtn = new CustomButton("Pause Game");
		menuBtn = new CustomButton("Main Menu");
		
		menuBtn.setBounds((10*30) + ((Tetris.FRAME_WIDTH - (10*30))/2) - MainMenu.BTN_WIDTH/2, Tetris.FRAME_HEIGHT-100, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT); 
		pauseBtn.setBounds((10*30) + ((Tetris.FRAME_WIDTH - (10*30))/2) - MainMenu.BTN_WIDTH/2, Tetris.FRAME_HEIGHT-100-MainMenu.BTN_HEIGHT-MainMenu.BTN_PADDING, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT);

		pauseBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_PAUSE;
			}
		});
		
		menuBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_MENU;
			}
		});

		JLabel nextBlockLbl = new JLabel ("Next Block:");
		nextBlockLbl.setForeground(Color.WHITE);
		nextBlockLbl.setBounds((Tetris.FRAME_WIDTH - (10*30)/2) - MainMenu.BTN_WIDTH/2, 20, MainMenu.BTN_WIDTH*2, MainMenu.BTN_HEIGHT); 
		nextBlockLbl.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel currentScoreTextLbl = new JLabel ("Current Score:");
		currentScoreTextLbl.setForeground(Color.WHITE);
		currentScoreTextLbl.setBounds((Tetris.FRAME_WIDTH - (10*30)/2) - MainMenu.BTN_WIDTH/2, 200, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT); 
		currentScoreTextLbl.setFont(new Font("Dialog", Font.BOLD, 16));

		currentScoreLbl = new JLabel ("0");
		currentScoreLbl.setForeground(Color.WHITE);
		currentScoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentScoreLbl.setBounds((Tetris.FRAME_WIDTH - (10*30)/2) - MainMenu.BTN_WIDTH/2, 200+MainMenu.BTN_HEIGHT, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT); 
		currentScoreLbl.setFont(new Font("Dialog", Font.BOLD, 16));

		JLabel currentLevelTextLbl = new JLabel ("Level:");
		currentLevelTextLbl.setForeground(Color.WHITE);
		currentLevelTextLbl.setBounds((Tetris.FRAME_WIDTH - (10*30)/2) - MainMenu.BTN_WIDTH/2, 280, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT); 
		currentLevelTextLbl.setFont(new Font("Dialog", Font.BOLD, 16));

		currentLevelLbl = new JLabel ("1");
		currentLevelLbl.setForeground(Color.WHITE);
		currentLevelLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentLevelLbl.setBounds((Tetris.FRAME_WIDTH - (10*30)/2) - MainMenu.BTN_WIDTH/2, 280+MainMenu.BTN_HEIGHT, MainMenu.BTN_WIDTH, MainMenu.BTN_HEIGHT); 
		currentLevelLbl.setFont(new Font("Dialog", Font.BOLD, 16));

		add(nextBlockLbl);
		add(currentScoreTextLbl);
		add(currentScoreLbl);
		add(currentLevelTextLbl);
		add(currentLevelLbl);
		add(menuBtn);
		add(pauseBtn);
	}
    
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        map.draw(g);
        user_block.draw(g);

		Graphics2D g2d = (Graphics2D) g;

   		g2d.setColor(new Color(193, 238, 244));
		g2d.setStroke(new BasicStroke(5));
    	g2d.drawRect((10*30)+1, 0, Tetris.FRAME_WIDTH-(10*30), Tetris.FRAME_HEIGHT);

		g2d.setColor(new Color(25, 115, 178));
    	g2d.fillRect((10*30)+2, 0, Tetris.FRAME_WIDTH-(10*30), Tetris.FRAME_HEIGHT);

		g2d.setColor(new Color(193, 238, 244));
		g2d.fillRect((10*30) + ((Tetris.FRAME_WIDTH - (10*30))/2) - (MainMenu.BTN_WIDTH/3)-3, 65-3, (MainMenu.BTN_WIDTH/3)*2+6, (MainMenu.BTN_WIDTH/3)*2+6);
		g2d.setColor(MainMenu.TETRIS_BLUE);
    	g2d.fillRect((10*30) + ((Tetris.FRAME_WIDTH - (10*30))/2) - (MainMenu.BTN_WIDTH/3), 65, (MainMenu.BTN_WIDTH/3)*2, (MainMenu.BTN_WIDTH/3)*2);

		g2d.setStroke(new BasicStroke(1));
		user_blocks.get(1).drawInNextBlockArea(g);
    }
}
