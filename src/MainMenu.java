import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel{
	public static final int BTN_WIDTH = 200;
	public static final int BTN_HEIGHT = 40;
	public static final int BTN_PADDING = 10;
	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = (Tetris.FRAME_HEIGHT/5)*2;

	public static final Color TETRIS_BLUE = new Color (2, 44, 84);
	private CustomButton startBtn, instructionsBtn, highscoreBtn, aboutBtn, quitBtn;
	private JLabel titleLbl;

	// CORRESPONDS TO TETRIS.JAVA's HANDLING OF STAGES
	public int returnState = Tetris.STAGE_MENU;

	public MainMenu (){
		setLayout(null);
		setBackground(TETRIS_BLUE);

		startBtn = new CustomButton ("Start");
		instructionsBtn = new CustomButton ("Instructions");
		highscoreBtn = new CustomButton ("Highscores");
		aboutBtn = new CustomButton ("About");
		quitBtn = new CustomButton ("Quit");

		titleLbl = new JLabel("TETRIS");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH), 30, BTN_WIDTH*2, BTN_HEIGHT*4); 
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 90));

		//START GAME
		startBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_GAME;
			}
		});

		//INSTRUCTIONS
		instructionsBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_INSTRUCTIONS;
			}
		});

		//HIGHSCORE
		highscoreBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_HIGHSCORES;
			}
		});

		//ABOUT
		aboutBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_ABOUT;
			}
		});

		//QUIT
		quitBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});

		startBtn.setBounds(menuBtnsX, menuBtnsY, BTN_WIDTH, BTN_HEIGHT); 
		instructionsBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT + BTN_PADDING, BTN_WIDTH, BTN_HEIGHT);
		highscoreBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT*2 + BTN_PADDING*2, BTN_WIDTH, BTN_HEIGHT);
		aboutBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT*3 + BTN_PADDING*3, BTN_WIDTH, BTN_HEIGHT);
		quitBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT*4 + BTN_PADDING*4, BTN_WIDTH, BTN_HEIGHT);

		add(titleLbl);
		add(startBtn);
		add(instructionsBtn);
		add(highscoreBtn);
		add(aboutBtn);
		add(quitBtn);
	}

	public int main_loop()
	{
		int temp = returnState;
		returnState = Tetris.STAGE_MENU;
		return temp;
	}
}
