import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CustomButton extends JLabel implements MouseListener{
	private Color textColor = new Color(255, 255, 255);
	private Color backgroundColor = new Color(3, 41, 78);
	private Color selectedColor = new Color(42, 129, 183);
	private Color borderColor = new Color(114, 249, 252);

	public CustomButton(String label) {
		setText(label);
		setHorizontalAlignment(SwingConstants.CENTER);

		setForeground(textColor);
		setOpaque(true);
		setBackground(backgroundColor);
		setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, borderColor));

		addMouseListener(this);
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e) {
		setBackground(selectedColor);
	}
	public void mouseExited(MouseEvent e) {
		setBackground(backgroundColor);
	}
}
