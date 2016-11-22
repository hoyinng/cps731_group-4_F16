import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainMenu extends JPanel{
	public MainMenu (){
		repaint();
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is my custom Panel!",10,20);
        g.setColor(Color.RED);
        g.fillRect(0,0,200,200);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,200,200);
    }
}
