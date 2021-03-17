
package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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

import img.Jelly;
import img.Field;
import img.Object;
import img.Person;
import main.MainFrame;
import score.ScorePanel;
import panel.BackgroundPanel;
import physical.Physical;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	int fieldX = 0;
	Field field;
	Object object;
	img.Jelly jelly;
	List<Field> fieldList = new ArrayList<Field>();
	List<Object> objectList = new ArrayList<Object>();
	List<img.Jelly> jellyList = new ArrayList<img.Jelly>();
	boolean b = false;
	int personY = 100;
	Person person;
	Thread t2;
	private Physical physical;
	static int black = new Color(0, 0, 0).getRGB();
	static int red = new Color(237, 28, 36).getRGB();
	static int yellow = new Color(255, 242, 0).getRGB();
	Thread t3;
	Thread t;
	BufferedImage image = null;
	
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
		
		// 추후에 체력물략 먹었을때 차는걸로 대체 해야함!************************************************
		background.add(lifeUp);
		
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
		person = new Person();
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBounds(100, 202, 200, 400);
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
		JButton btn = new JButton("종료,랭킹화면");
		btn.setBounds(0, 100, 150, 50);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeRankPanel();
			}
		});
		background.add(btn);
		
		
		try {
			image = ImageIO.read(new File(".\\img\\stage1.png"));
			getBlack(image);
			getRed(image);
			getYellow(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		t = new Thread(new fieldRunnable());
//		t.start();
		
		t2 = new Thread(new GravityRunnable());
//		t2.start();
		
		t3 = new Thread(new MoveRunnable());
	}
	
	private class fieldRunnable implements Runnable {
		@Override
		public void run() {
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
				for(int i = 0; i < objectList.size(); i++) {
					int X = objectList.get(i).getX();
					objectList.get(i).setBounds(X - 5, objectList.get(i).getY(), 50, 50);
				}
				for(int i = 0; i < jellyList.size(); i++) {
					int X = jellyList.get(i).getX();
					jellyList.get(i).setBounds(X - 5, jellyList.get(i).getY(), 50, 50);
				}
//				System.out.println(fieldList.size()); // 필드리스트 사이즈 = 168
//				System.out.println(fieldList.get(168).getX());
				if (fieldList.get(148).getX() == 0) {
					System.out.println("1스테이지의 끝");
					// 1스테이지가 끝남, 
					try {
						image = ImageIO.read(new File(".\\img\\stage2.png"));
						getBlack(image);
						getRed(image);
						getYellow(image);
					} catch (IOException e) {
						e.printStackTrace();
					}
					background.setBackImg1(new ImageIcon(".\\img\\bg2.png").getImage());
					// 2스테이지 필드리스트값 출력해보기
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
//					System.out.println("w" + w);
//					System.out.println("h" + h);
					field = new Field();
					field.setBounds(w * 50, h * 50, 50, 200);
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
//					System.out.println("w" + w);
//					System.out.println("h" + h);
					object = new Object();
					object.setBounds(w * 50, h * 50, 50, 50);
					background.add(object);
					objectList.add(object);
				}
			}
		}
	}
	
	public void getYellow(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == yellow) {
//					System.out.println("w" + w);
//					System.out.println("h" + h);
					jelly = new img.Jelly();
					jelly.setBounds(w * 50, h * 50, 50, 50);
					background.add(jelly);
					jellyList.add(jelly);
				}
			}
		}
	}
	
	private class GravityRunnable implements Runnable {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				startGravity();
			}
		}
	}
	
	private class MoveRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				b = getFieldY();
//				System.out.println("test:" + b);
				if (!b) {
					stopGravity();
				}
			}
		}
	}
	public boolean getFieldY() {
//		System.out.println("작동되는 중...");
		if (background.getComponentAt(new Point(200, person.getY() + 300)) != null) {
			if (background.getComponentAt(new Point(200, person.getY() + 300)).getClass().getName().equals("img.Field")) {
				return true;
			}
		}
		return false;
		
	}
	
	public synchronized void startGravity() {
		if (b) {
			try {
				wait();
			} catch (InterruptedException e) {}
		} 
		person.setLocation(0, personY);
		personY++;
	}
		
	public synchronized void stopGravity() {
		notifyAll();
	}

	public Thread getT2() {
		return t2;
	}

	public void setT2(Thread t2) {
		this.t2 = t2;
	}

	public Thread getT3() {
		return t3;
	}

	public void setT3(Thread t3) {
		this.t3 = t3;
	}

	public Thread getT() {
		return t;
	}

	public void setT(Thread t) {
		this.t = t;
	}
	
	
}
