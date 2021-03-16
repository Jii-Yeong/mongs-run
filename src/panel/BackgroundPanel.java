package panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel implements Runnable {
	ImageIcon backIc = new ImageIcon(".\\img\\bg1.png");
	Image backImg = backIc.getImage();
	
	int back1X = 0;
	int back2X = backImg.getWidth(null);
	public BackgroundPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setBounds(0, 0, 1000, 700);
		setFocusable(true);
		Thread t = new Thread(this);
		t.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backImg, back1X, 0, this);
		g.drawImage(backImg, back2X, 0, this);
	}
	@Override
	public void run() {
		while (true) {
			back1X--;
			back2X--;
			
			if (back1X < -(backImg.getWidth(null))) {
				back1X = backImg.getWidth(null);
			}
			if (back2X < -(backImg.getWidth(null))) {
				back2X = backImg.getWidth(null);
			}
			repaint();
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
