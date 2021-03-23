package img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Field extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private Image field;
	/**
	 * Create the panel.
	 */
	public Field() {
		setBackground(new Color(0, 0, 0, 0));
		field = new ImageIcon(".\\img\\field.png").getImage();
	}
	
	public Image getField() {
		return field;
	}
	
	public void setField(Image field) {
		this.field = field;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(field, 0, 0, this);
		
	}
}
