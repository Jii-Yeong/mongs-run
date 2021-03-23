package panel;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel implements Runnable {
	private ImageIcon backIc1 = new ImageIcon(".\\img\\bg1.png");
	private ImageIcon backIc2 = new ImageIcon(".\\img\\bg2.png");
	private ImageIcon backIc3 = new ImageIcon(".\\img\\bg3.png");
	private Image backImg1 = new ImageIcon(".\\img\\bg1.png").getImage();
	private Image backImg2 = new ImageIcon(".\\img\\bg2.png").getImage();
	private Image backImg3 = new ImageIcon(".\\img\\bg3.png").getImage();
	private int back1X = 0;
	private int back2X = backImg1.getWidth(null);
	private AlphaComposite alphaComposite;
	private int alpha = 255;
	
	public BackgroundPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setBounds(0, 0, 1000, 700);
		setFocusable(true);
		Thread t = new Thread(this);
		t.start();
	}
	
	public void setBackImg1(Image backImg1) {
		this.backImg1 = backImg1;
	}
	
	public int getAlpha() {
		return alpha;
	}
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) getAlpha()/255);
		g2.setComposite(alphaComposite);
		g.drawImage(backImg1, back1X, 0, this);
		g.drawImage(backImg1, back2X, 0, this);
	}
	
	@Override
	public void run() {
		while (true) {
			back1X--;
			back2X--;
			// 바디에 -1씩 해줘야 배경이 끊어지지않음
			if (back1X < -(backImg1.getWidth(null))) {
				back1X = backImg1.getWidth(null) - 2;
			}
			if (back2X < -(backImg1.getWidth(null))) {
				back2X = backImg1.getWidth(null) - 2;
			}
			repaint();
			
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
