import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PauseScreen extends JPanel{
	public final int BTN_WIDTH = 200;
	public final int BTN_HEIGHT = 40;
	public final int BTN_PADDING = 10;

	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = Tetris.FRAME_HEIGHT - BTN_HEIGHT*2 - BTN_PADDING;

	private CustomButton backBtn;
	private JLabel titleLbl, infoLbl;

	private int returnState = Tetris.STAGE_PAUSE;

	public PauseScreen(){
		setLayout(null);
		setBackground(MainMenu.TETRIS_BLUE);

		backBtn = new CustomButton("Return to Game");
		backBtn.setBounds(menuBtnsX, menuBtnsY - 150, BTN_WIDTH, BTN_HEIGHT);
		add(backBtn);

		titleLbl = new JLabel("PAUSED");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH), Tetris.FRAME_HEIGHT/2-BTN_HEIGHT-60, BTN_WIDTH*2, BTN_HEIGHT+60); 
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 60));

		add(titleLbl);

		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_GAME;
			}
		});
	}

	public int main_loop(){
		int temp = returnState;
		returnState = Tetris.STAGE_PAUSE;
		return temp;
	}
}
