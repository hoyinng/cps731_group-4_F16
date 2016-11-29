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
	Integer subStage=0;
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
		return 1;
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("Click me",10,20);
        
       
    }
}
