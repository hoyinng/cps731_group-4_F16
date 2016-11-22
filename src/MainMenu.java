import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
	JLabel my_label;
	Integer counter;
	
	public MainMenu (){
		my_label = new JLabel ("SOMETEXT");
		counter =0;
		addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                counter +=1;
                repaint();
            }
        });
	}
	/*	Main life cycle of MainMenu
	 * 
	 */
	public int main_loop()
	{
		while (1==1)
		{
			if (this.counter > 10)
			{
				break;
			}
		}
		return 0;
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("You are in main menu!!!! Click me 11 times to jump to game >"+(this.counter),10,20);
		my_label.setLocation(100, 200);
    }
}
