//
import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris extends JFrame
{
	// (TODO)
	int stage, level;
	//JFrame main_frame, game_frame, setting_frame, about_frame,previous_frame;
	JPanel main_frame,game_frame,setting_frame;
	public Tetris (String program_name)
	{
		super(program_name);
		this.level = 0;
		Game game = new Game();
		MainMenu menu = new MainMenu ();
		
		add(menu);
		add(game);
		remove(game);
		/*
		this.main_frame = new JFrame("Tetris Game");
		this.game_frame = new JFrame("Game");
		this.setting_frame = new JFrame ("Setting");
		this.about_frame = new JFrame ("About");
		
		this.level = 0;
		main_frame.add(new Game());
		main_frame.setSize(500, 500);
		setting_frame.add(new JPanel());
		about_frame.add(new JPanel());
		*/
		this.init();
				
		
		
	}
	public void init()
	{
		this.stage = 0;
		
	}
	public void start(){}
	/*
	public void start ()
	{
		while (1==1)
		{
			switch (this.stage)
			{
				case 0:
					main_frame.setVisible(true);
					while (main_frame.isVisible());
					break;
				case 1:		
					setting_frame.setVisible(true);
					while (setting_frame.isVisible());
					break;
				case 2:
					about_frame.setVisible(true);
					while (about_frame.isVisible());
					break;
			}
		}
	}*/
	public void paint()
	{
		
	}
	public void stop ()
	{
		
	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Helloworld");
		Tetris frame = new Tetris("Tetris Game");
		
		
		frame.start();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520,480);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
