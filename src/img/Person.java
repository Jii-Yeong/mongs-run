package img;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Person extends JPanel {
	private Image im;
	ImageIcon img;
	public Person() {
		img = new ImageIcon(".\\img\\Person.gif");
		im = img.getImage();
//		setBounds(0, 0, 200, 400);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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
