package physical;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Physical extends JLayeredPane implements Runnable {
	private ImageIcon lifeBar_image;
	private ImageIcon lifeMinus_image;
	private int life;

	public Physical() {
		setLayout(null);
		Thread thread = new Thread(this);
		thread.start();
		life = 515;
		lifeMinus_image = new ImageIcon(".\\img\\lifebar\\lifeMinus.png");
		lifeBar_image = new ImageIcon(".\\img\\lifebar\\lifeBar.png");
		
		// x = 540 체력 풀, x = 65 체력 없음
		
		JLabel lifebar = new JLabel();
		lifebar.setBounds(0, 0, 560, 80);
		lifebar.setIcon(lifeBar_image);
		add(lifebar, new Integer(0));
		setSize(560, 80);
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Physical physical = new Physical();
		physical.setBounds(60, 60, 560, 80);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(physical);
		frame.setSize(1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void lifeMinus(int x) {
		JLabel lifeMinus = new JLabel();
		lifeMinus.setBounds(x, 34, 25, 21);
		lifeMinus.setSize(25, 21);
		lifeMinus.setIcon(lifeMinus_image);
		add(lifeMinus, new Integer(1));
	}
	
//	private void lifePlus(int x) {
//		JLabel lifeMinus = new JLabel();
//		lifeMinus.setBounds(x, 34, 25, 21);
//		lifeMinus.setSize(25, 21);
//		lifeMinus.setIcon(lifeMinus_image);
//		add(lifeMinus, new Integer(1));
//	}

	@Override
	public void run() {
		try {
			while (life >= 65) {
				Thread.sleep(1000);
				lifeMinus(life);
				life -= 25;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
