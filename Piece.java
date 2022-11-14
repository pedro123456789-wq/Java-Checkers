import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.Icon;

public class Piece implements Icon {
	private Color color;
	private boolean isQueen;

	public Piece(int x, int y, Color color, boolean isQueen) {
		this.color = color;
		this.isQueen = isQueen;
	}

	public boolean isQueen() {
		return this.isQueen;
	}

	@Override
	public int getIconHeight() {
		return 40;
	}

	@Override
	public int getIconWidth() {
		return 40;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(this.color);

		if (this.isQueen) {
			g2.drawRect(x, y, this.getIconWidth(), this.getIconHeight());
			g2.fillRect(x, y, this.getIconWidth(), this.getIconHeight());
		} else {
			g2.drawOval(x, y, this.getIconWidth() - 1, this.getIconHeight() - 1);
			g2.fillOval(x, y, this.getIconWidth() - 1, this.getIconHeight() - 1);
		}
		
		g2.dispose();
	}

	public Color getColor() {
		return this.color;
	}
}
