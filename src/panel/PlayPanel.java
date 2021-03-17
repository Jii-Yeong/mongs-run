package panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Field;
import img.Jelly;
import img.Person;
import main.MainFrame;
import panel.BackgroundPanel;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	int fieldX = 0;
	Field field;
	Jelly jelly;
	List<Field> fieldList = new ArrayList<Field>();
	
	static int black = new Color(0, 0, 0).getRGB();
	static int red = new Color(237, 28, 36).getRGB();
	
	public PlayPanel(MainFrame frame) {
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		Person person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 202, 200, 400);
		person.setBackground(new Color(0, 0, 0, 1));
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		add(background);
		JButton btn = new JButton("종료,랭킹화면");
		btn.setBounds(0, 0, 150, 50);
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
			getRed(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Thread t = new Thread(new fieldRunnable());
		t.start();
	}
	
	private class fieldRunnable implements Runnable { // 왜동작안함?
		@Override
		public void run() {
//			for (int i = 0; i < fieldList.size(); i++) {
//				System.out.println(fieldList.get(i));
//				fieldList.get(i).setLocation(fieldList.get(i).getX() + fieldX, fieldList.get(i).getY());
//			}
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				for(int i = 0; i < fieldList.size(); i++) {
					int X = fieldList.get(i).getX();
					fieldList.get(i).setBounds(X - 5, fieldList.get(i).getY(), 50, 50);
				}
			}
		}
	}
	
	public void getBlack(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == black) {
					System.out.println("w" + w);
					System.out.println("h" + h);
					field = new Field();
					field.setBounds(w * 50, h * 50, 50, 50);
					background.add(field);
					fieldList.add(field);
				}
			}
		}
	}
	
	public void getRed(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == red) {
					System.out.println("w" + w);
					System.out.println("h" + h);
					jelly = new Jelly();
					jelly.setBounds(w * 50, h * 50, 50, 50);
					background.add(jelly);
				}
			}
		}
	}
}
