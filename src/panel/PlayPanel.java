package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Field;
import img.Person;
import main.MainFrame;
import score.ScorePanel;
import panel.BackgroundPanel;
import physical.Physical;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	int personY = 0;
	Person person;
	Component field;
	
	static int black = new Color(0, 0, 0).getRGB();
	private Physical physical;
	
	public PlayPanel(MainFrame frame) {
		field = new Field();
		JButton lifeUp = new JButton("Up");
		lifeUp.setBounds(0, 300, 100, 100);
		lifeUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				physical.lifePlus();
				System.out.println("체력 늘리기");
				System.out.println("x 좌표 : " + physical.getLife());
			}
		});
		background.add(lifeUp);
		
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 225, 200, 400);
		person.setBackground(new Color(0, 0, 0, 1));
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		ScorePanel scorePanel = new ScorePanel();
		scorePanel.setBounds(700, 0, 300, 100);
		scorePanel.setBackground(new Color(0, 0, 0, 0));
		background.add(scorePanel);
		physical = new Physical();
		physical.setBounds(0, 0, 560, 80);
		background.add(physical);
		add(background);
		JButton btn = new JButton("dkanrjsk");
		btn.setBounds(0, 0, 100, 100);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeRankPanel();
			}
		});
		background.add(btn);
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(".\\img\\black.png"));
			getBlack(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread t = new Thread(new GravityRunnable());
		t.start();
		
	}
	
	public void getBlack(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == black) {
//					System.out.println("w" + w);
//					System.out.println("h" + h);
					Field field = new Field();
					field.setBounds(w * 17, h * 25, 100, 100);
					background.add(field);
				}
			}
		}
	}
	
	private class GravityRunnable implements Runnable {
		@Override
		public void run() {
			while (!findPersonY()) {
				person.setBounds(0, personY, 200, 400);
				personY += 1;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public boolean findPersonY() {
		if (person.getY() + 300 == getFieldY()) {
			return true;
		}
		return false;
	}
	public int getFieldY() {
		for (int h = 0; h < background.getHeight(); h++) {
			if (background.getComponentAt(0, h) instanceof Field) {
				field = background.getComponentAt(0, h);
			} else if (background.getComponentAt(0, h) == null) {
				break;
			}
		}
		return field.getY();
		
	}
}
