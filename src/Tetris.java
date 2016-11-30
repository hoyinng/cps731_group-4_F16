//
import java.applet.Applet;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JFrame
{
	public static final int FRAME_WIDTH = 520, FRAME_HEIGHT = 480;
	// (TODO)
	
	/* Stage represent  mainmenu, game, setting in numerical order */
	int stage, level;
	
	CardLayout layout;
	JPanel default_panel;
	Game game;
	MainMenu menu;
	String keys [] = {"menu","game","setting","about"};
	Map map;
	/* Tetris Constructor 
	 * Tetris inherit from JFrame; This frame contains default_panel and default_panel contains layout
	 * layout is a CardLayout Object containing multiple panel object such as Game and MainMenu
	 * @param program_name Title of the JFrame Window
	 */
	public Tetris (String program_name)
	{
		super(program_name);
		this.level = 0;
		
		this.map = new Map(40,40,25);
		layout = new CardLayout();
		default_panel = new JPanel (layout);
		add(default_panel);
		
		game = new Game();
		menu = new MainMenu ();
		
		// adding the (panel,key)
		default_panel.add(menu, "menu");	
		default_panel.add(game, "game");
		
		this.init();	// Initialize tetris game state
	}
	/* Shows the panel selected in this frame panel
	 * @param key is the key related to the panel inserted
	 */
	public void changeLayout (String key){
		CardLayout cl = (CardLayout)(default_panel.getLayout());
		cl.show(default_panel, key);
	}

	public void init()
	{
		this.stage = 0;
		
	}
	/*	Main Life Cycle of the program
	 *	MainMenu menu.main_loop changes the stage of the game
	 *	After every operation, stage will be set back to 0
	 */
	public void main_loop(){
		while (true)
		{
			// (TODO) this currently does not work
			//if (this.stage == -1){ this.getDefaultCloseOperation(); } 
			changeLayout(keys[this.stage]);
			switch (this.stage)
			{
			case 0: this.stage = this.menu.main_loop();
				break;
			case 1: this.game.main_loop(); this.stage = 0;
				break;
			}
			
			
		}
	}
	public void stop ()
	{
		
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Helloworld");
		Tetris frame = new Tetris("Tetris Game");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setFocusable(false);
		frame.main_loop();
	}

}
