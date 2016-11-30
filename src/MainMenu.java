import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel{
	public final int BTN_WIDTH = 200;
	public final int BTN_HEIGHT = 40;
	public final int BTN_PADDING = 10;
	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = (Tetris.FRAME_HEIGHT/5)*2;

	public Color tetrisBlue = new Color (2, 44, 84);
	private CustomButton startBtn, instructionsBtn, highscoreBtn, aboutBtn;

	// CORRESPONDS TO TETRIS.JAVA's HANDLING OF STAGES
	public int returnState = 0;

	public MainMenu (){
		setLayout(null);
		setBackground(tetrisBlue);

		startBtn = new CustomButton ("Start");
		instructionsBtn = new CustomButton ("Instructions");
		highscoreBtn = new CustomButton ("Highscores");
		aboutBtn = new CustomButton ("About");

		startBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = 1;
			}
		});

		instructionsBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				//returnState = 2;
			}
		});

		highscoreBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				//returnState = 3;
			}
		});

		aboutBtn.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				//returnState = 4;
			}
		});

		startBtn.setBounds(menuBtnsX, menuBtnsY, BTN_WIDTH, BTN_HEIGHT); 
		instructionsBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT + BTN_PADDING, BTN_WIDTH, BTN_HEIGHT);
		highscoreBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT*2 + BTN_PADDING*2, BTN_WIDTH, BTN_HEIGHT);
		aboutBtn.setBounds(menuBtnsX, menuBtnsY + BTN_HEIGHT*3 + BTN_PADDING*3, BTN_WIDTH, BTN_HEIGHT);

		add(startBtn);
		add(instructionsBtn);
		add(highscoreBtn);
		add(aboutBtn);
	}

	public int main_loop()
	{
		return returnState;
	}
}
