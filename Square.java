import java.awt.Color;

import javax.swing.JButton;

public class Square extends JButton{
	private static final long serialVersionUID = 1L;
	
	private Color color;
	private int x;
	private int y;
	private JButton button;
	
	
	public Square(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.setButton();
	}
	
	private void setButton() {
		JButton button = new JButton();
		button.setBackground(this.color);
		this.button = button;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public JButton getJButton() {
		return this.button;
	}
}
