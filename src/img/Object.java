package img;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Object extends JPanel{
	private int x;
	private int y;
	private int width;
	private int height;
	private Image object;
	
	public Object() {
		setBackground(new Color(237, 28, 36, 0));
		object = new ImageIcon(".\\img\\obstacle.png").getImage();
	}
	
	public Object(Image obstacle) {
		super();
		this.object = obstacle;
	}

	public Image getObject() {
		return object;
	}

	public void setObject(Image object) {
		this.object = object;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(object, 0, 0, this);
	}
}