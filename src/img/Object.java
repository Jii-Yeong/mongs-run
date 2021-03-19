package img;

import java.awt.AlphaComposite;
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
	
	private Image obstacle = new ImageIcon(".\\img\\obstacle.png").getImage();
	
	public Object() {
		setBackground(new Color(237, 28, 36, 0));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(obstacle, 0, 0, this);
	}
}