package img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Jelly extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private Image jelly = new ImageIcon(".\\img\\jelly2.png").getImage();
	private AlphaComposite alphaComposite;
	private int alpha = 255;
	
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	public Jelly() {
		setBackground(new Color(255, 242, 0, 0));
	}
	
	public Image getJelly() {
		return jelly;
	}
	public void setJelly(Image jelly) {
		this.jelly = jelly;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlpha() /255);
		g2.setComposite(alphaComposite);
		g.drawImage(jelly, 0, 0, this);
	}
}