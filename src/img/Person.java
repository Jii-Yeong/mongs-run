package img;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Person extends JPanel {
	private Image im;
	ImageIcon img;
	int alpha = 255;
	private AlphaComposite alphaComposite;
	
	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public Person() {
		img = new ImageIcon(".\\img\\Person.gif");
		im = img.getImage();
//		setBounds(0, 0, 200, 400);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlpha()/255);
		g2.setComposite(alphaComposite);
		g.drawImage(im, 0, 0, this);
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}
	
	
	
}
