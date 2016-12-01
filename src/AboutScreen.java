import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutScreen extends JPanel{
	public final int BTN_WIDTH = 200;
	public final int BTN_HEIGHT = 40;
	public final int BTN_PADDING = 10;

	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = Tetris.FRAME_HEIGHT - BTN_HEIGHT*2 - BTN_PADDING;

	private CustomButton backBtn;
	private JLabel titleLbl, infoLbl;

	private int returnState = Tetris.STAGE_ABOUT;

	public AboutScreen(){
		setLayout(null);
		setBackground(MainMenu.TETRIS_BLUE);

		backBtn = new CustomButton("Back");
		backBtn.setBounds(menuBtnsX, menuBtnsY, BTN_WIDTH, BTN_HEIGHT);
		add(backBtn);

		titleLbl = new JLabel("About");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH), 30, BTN_WIDTH*2, BTN_HEIGHT); 
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 30));

		infoLbl = new JLabel("<html><center>A Tetris game developed for CPS731 F2016.<br><br>This game was created by group 4:<br>Jeff Chen<br>Ho Yin Ng<br>Alvin Ho<br>Steven Yan<br>Janson Chung<br>John Perez</center></html>");
		infoLbl.setForeground(Color.WHITE);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setBounds(0, 0 - 70, Tetris.FRAME_WIDTH, Tetris.FRAME_HEIGHT); 
		infoLbl.setFont(new Font("Dialog", Font.BOLD, 14));

		add(titleLbl);
		add(infoLbl);

		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				returnState = Tetris.STAGE_MENU;
			}
		});
	}

	public int main_loop(){
		int temp = returnState;
		returnState = Tetris.STAGE_ABOUT;
		return temp;
	}
}
