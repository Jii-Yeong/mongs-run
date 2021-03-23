package img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Potion extends JPanel {
	private int x;
	private int y;
	private int width;
	private int height;
	private AlphaComposite alphaComposite;
	private int alpha;
	private Image potion;

	public Potion() {
		setBackground(new Color(34, 177, 76, 0));
		potion = new ImageIcon(".\\img\\potion.png").getImage();
		alpha = 255;
	}
	
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	public Image getPotion() {
		return potion;
	}
	public void setPotion(Image potion) {
		this.potion = potion;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlpha() /255);
		g2.setComposite(alphaComposite);
		g.drawImage(potion, 0, 0, this);
	}
}
