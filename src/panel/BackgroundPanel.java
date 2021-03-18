package panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel implements Runnable {
	ImageIcon backIc1 = new ImageIcon(".\\img\\bg1.png");
	ImageIcon backIc2 = new ImageIcon(".\\img\\bg2.png");
	ImageIcon backIc3 = new ImageIcon(".\\img\\bg3.png");
	Image backImg1 = new ImageIcon(".\\img\\bg1.png").getImage();
	Image backImg2 = new ImageIcon(".\\img\\bg2.png").getImage();
	Image backImg3 = new ImageIcon(".\\img\\bg3.png").getImage();
	
	public void setBackImg1(Image backImg1) {
		this.backImg1 = backImg1;
	}
	
	int back1X = 0;
	int back2X = backImg1.getWidth(null);
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
		
		g.drawImage(backImg1, back1X, 0, this);
		g.drawImage(backImg1, back2X, 0, this);
	}
	@Override
	public void run() {
//		System.out.println("1스테이지 가로값: " + backImg1.getWidth(null));
		while (true) {
			back1X--;
			back2X--;
			// 바디에 -1씩 해줘야 배경이 끊어지지않음
			if (back1X < -(backImg1.getWidth(null))) {
				back1X = backImg1.getWidth(null) - 1;
			}
			if (back2X < -(backImg1.getWidth(null))) {
				back2X = backImg1.getWidth(null) - 1;
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
