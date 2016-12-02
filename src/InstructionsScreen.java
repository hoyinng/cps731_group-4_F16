import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InstructionsScreen extends JPanel{
	public final int BTN_WIDTH = 200;
	public final int BTN_HEIGHT = 40;
	public final int BTN_PADDING = 10;

	public int menuBtnsX = (Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2);
	public int menuBtnsY = Tetris.FRAME_HEIGHT - BTN_HEIGHT*2 - BTN_PADDING;

	private CustomButton menuBtn, nextBtn, backBtn;
	private JLabel titleLbl, infoLbl, pageLbl;

	private int maxPage = 2, minPage = 1, currentPage = 1;

	private String[] pageInfo = {"<html><center>The point to tetris is to try your best to fit the blocks together.<br><br>With 7 different shapes of tetris blocks, you have attempt to move and rotate the blocks as they fall down so they fit together and fill lines. Each horizontal line on the grid that is filled up, will be cleared for space for more blocks. You get points for every line cleared.</center></html>", "<html>Controls:<br><br>W = Rotate Tetris Block<br>A = Move Tetris Block Left<br>S = Move Tetris Block Down Faster<br>D = Move Tetris Block Right<br>SPACE BAR = Drop Tetris Block<br><br>ESC = Pause</html>"};

	private int returnState = Tetris.STAGE_INSTRUCTIONS;

	public InstructionsScreen(){
		setLayout(null);
		setBackground(MainMenu.TETRIS_BLUE);

		menuBtn = new CustomButton("Main Menu");
		menuBtn.setBounds(menuBtnsX, menuBtnsY, BTN_WIDTH, BTN_HEIGHT);
		add(menuBtn);

		backBtn = new CustomButton("<");
		backBtn.setBounds((Tetris.FRAME_WIDTH/4)-(BTN_WIDTH/2), menuBtnsY-50, BTN_WIDTH, (BTN_HEIGHT/3)*2);
		add(backBtn);

		nextBtn = new CustomButton(">");
		nextBtn.setBounds((Tetris.FRAME_WIDTH/4)*3-(BTN_WIDTH/2), menuBtnsY-50, BTN_WIDTH, (BTN_HEIGHT/3)*2);
		add(nextBtn);	

		pageLbl = new JLabel(currentPage + "/" + maxPage);
		pageLbl.setForeground(Color.WHITE);
		pageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pageLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH/2), menuBtnsY-50, BTN_WIDTH, BTN_HEIGHT); 
		pageLbl.setFont(new Font("Dialog", Font.BOLD, 10));	

		titleLbl = new JLabel("Instructions");
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds((Tetris.FRAME_WIDTH/2)-(BTN_WIDTH), 30, BTN_WIDTH*2, BTN_HEIGHT); 
		titleLbl.setFont(new Font("Dialog", Font.BOLD, 30));

		infoLbl = new JLabel(pageInfo[currentPage - 1]);
		infoLbl.setForeground(Color.WHITE);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		infoLbl.setBounds(0, 0 - 70, Tetris.FRAME_WIDTH, Tetris.FRAME_HEIGHT); 
		infoLbl.setFont(new Font("Dialog", Font.BOLD, 14));

		add(pageLbl);
		add(titleLbl);
		add(infoLbl);

		menuBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				currentPage = minPage;
				updatePage();
				returnState = Tetris.STAGE_MENU;
			}
		});

		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (currentPage + 1 <= maxPage) currentPage++;
				updatePage();
			}
		});

		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (currentPage - 1 >= minPage) currentPage--;
				updatePage();
			}
		});
	}

	public int main_loop(){
		int temp = returnState;
		returnState = Tetris.STAGE_INSTRUCTIONS;
		return temp;
	}

	public void updatePage(){
		infoLbl.setText(pageInfo[currentPage - 1]);
		pageLbl.setText(currentPage + "/" + maxPage);
	}
}
