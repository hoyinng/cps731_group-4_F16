//
import java.applet.Applet;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JFrame
{
	public static final int FRAME_WIDTH = 570, FRAME_HEIGHT = 540;
	public static final int STAGE_MENU = 0, STAGE_GAME = 1, STAGE_INSTRUCTIONS = 2, STAGE_HIGHSCORES = 3, STAGE_ABOUT = 4, STAGE_PAUSE = 5;
	// (TODO)
	
	/* Stage represent  mainmenu, game, setting in numerical order */
	int stage, level;
	
	CardLayout layout;
	JPanel default_panel;
	Game game;
	MainMenu menu;
	InstructionsScreen instructions;
	HighscoresScreen highscores;
	AboutScreen about;
	PauseScreen pause;
	String keys [] = {"menu","game","instructions","highscores","about", "pause"};
	/* Tetris Constructor 
	 * Tetris inherit from JFrame; This frame contains default_panel and default_panel contains layout
	 * layout is a CardLayout Object containing multiple panel object such as Game and MainMenu
	 * @param program_name Title of the JFrame Window
	 */
	public Tetris (String program_name)
	{
		super(program_name);
		this.level = 0;
		
		layout = new CardLayout();
		default_panel = new JPanel (layout);
		add(default_panel);
		
		game = new Game(FRAME_HEIGHT,FRAME_WIDTH);
		menu = new MainMenu ();
		instructions = new InstructionsScreen();
		highscores = new HighscoresScreen();
		about = new AboutScreen();
		pause = new PauseScreen();
		
		// adding the (panel,key)
		default_panel.add(menu, "menu");	
		default_panel.add(game, "game");
		default_panel.add(instructions, "instructions");
		default_panel.add(highscores, "highscores");
		default_panel.add(about, "about");
		default_panel.add(pause, "pause");
		
		
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
			changeLayout(keys[this.stage]);
			if (this.stage == STAGE_MENU){
				this.stage = this.menu.main_loop();
			}
			else if (this.stage == STAGE_GAME){
				this.stage = this.game.main_loop();
			}
			else if (this.stage == STAGE_INSTRUCTIONS){
				this.stage = this.instructions.main_loop();
			}
			else if (this.stage == STAGE_HIGHSCORES){
				this.stage = this.highscores.main_loop();
			}
			else if (this.stage == STAGE_ABOUT){
				this.stage = this.about.main_loop();
			}
			else if (this.stage == STAGE_PAUSE){
				this.stage = this.pause.main_loop();
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
