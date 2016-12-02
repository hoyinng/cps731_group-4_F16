import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class HighscoresScreen extends JPanel{
	public final int BTN_WIDTH = 200;
	public final int BTN_HEIGHT = 40;
	public final int BTN_PADDING = 10;

	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = Tetris.FRAME_HEIGHT - BTN_HEIGHT*2 - BTN_PADDING;

	private CustomButton backBtn;
	private JLabel titleLbl, namesLbl, scoresLbl;

	private String names, scores;

	private int returnState = Tetris.STAGE_HIGHSCORES;

	public boolean onScreen = false;

	public HighscoresScreen(){
		setLayout(null);
		setBackground(MainMenu.TETRIS_BLUE);

		backBtn = new CustomButton("Back");
		backBtn.setBounds(menuBtnsX, menuBtnsY, BTN_WIDTH, BTN_HEIGHT);
		add(backBtn);

		titleLbl = new JLabel("Highscores");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH), 30, BTN_WIDTH*2, BTN_HEIGHT); 
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 30));

		namesLbl = new JLabel("");
		namesLbl.setForeground(Color.WHITE);
		namesLbl.setBounds((Tetris.FRAME_WIDTH/4)-(BTN_WIDTH/4), 0-20, BTN_WIDTH, Tetris.FRAME_HEIGHT);
		namesLbl.setFont(new Font("Dialog", Font.BOLD, 20));

		scoresLbl = new JLabel("");
		scoresLbl.setForeground(Color.WHITE);
		scoresLbl.setBounds((Tetris.FRAME_WIDTH/4)*3-(BTN_WIDTH/4), 0-20, BTN_WIDTH, Tetris.FRAME_HEIGHT);
		scoresLbl.setFont(new Font("Dialog", Font.BOLD, 20));

		add(titleLbl);
		add(namesLbl);
		add(scoresLbl);

		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				onScreen = false;
				returnState = Tetris.STAGE_MENU;
			}
		});

		updateEntries();
	}

	public int main_loop(){
		if (!onScreen) updateEntries();
		onScreen = true;
		int temp = returnState;
		returnState = Tetris.STAGE_HIGHSCORES;
		return temp;
	}

	public void updateEntries(){
		try{
			int count = 1;
			File f = new File("scoreboard.txt");
			Scanner s = new Scanner(f);
			names = "";
			names += "<html>";
			scores = "";
			scores += "<html>";
			while (s.hasNextLine()){
				names += count + ". ";
				names += s.next();
				names += "<br>";
				scores += s.next();
				scores += "<br>";
				s.nextLine();
				count++;
			}
			names += "</html>";
			scores += "</html>";
			namesLbl.setText(names);
			scoresLbl.setText(scores);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
