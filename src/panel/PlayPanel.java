package panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import img.Jelly;
import img.Field;
import img.Object;
import img.Person;
import img.Potion;
import main.MainFrame;
import score.ScorePanel;
import panel.BackgroundPanel;
import physical.Physical;
import ranking.RankData;
import ranking.RankPanel;
import result.ResultPanel;

public class PlayPanel extends JPanel {
	private BackgroundPanel background = new BackgroundPanel();
	private int fieldX = 0;
	private Field field;
	private Object object;
	private img.Jelly jelly;
	private Potion potion;
	private List<Field> fieldList = new ArrayList<Field>();
	private List<Object> objectList = new ArrayList<Object>();
	private List<img.Jelly> jellyList = new ArrayList<img.Jelly>();
	private List<Potion> potionList = new ArrayList<Potion>();
	private static int black = new Color(0, 0, 0).getRGB();
	private static int red = new Color(237, 28, 36).getRGB();
	private static int yellow = new Color(255, 242, 0).getRGB();
	private static int green = new Color(34, 177, 76).getRGB();
	private boolean b = false;
	private int personY = 100;
	private Person person;
	private Physical physical;
	private RankPanel rankPanel;
	private ResultPanel resultPanel;
	private ScorePanel scorePanel;
	private MainFrame frame;
	private Thread fieldThread;
	private Thread MoveThread;
	private Thread GravityThread;
	private Thread gameOverState;
	private Thread doseNotDecreaseLifeThread;
	private Thread jellyThread;
	private Thread healingThread;
	private Thread slideBGMThread;
	private JLabel blackLabel;
	private BufferedImage image = null;
	private boolean isJump;
	private boolean isSlide = false;
	private boolean isSlideBGM = false;
	private static boolean stop;
	private Rectangle personHitR;
	private File skyBGM;
	private File redskyBGM;
	private File spaceBGM;
	private File jumpBGM;
	private File slideBGM;
	private Clip sound;
	private Clip tempSound;
	private int selectedNum;

	/**
	 * Create the panel.
	 */
	JPanel pnl;
	JPanel pnl2;
	JPanel pnl4; // 깜빡이용 히트박스표시패널
	
	public PlayPanel(MainFrame frame, int selectedNum) {
		this.selectedNum = selectedNum;
		System.out.println("현재 캐릭터 : "+ selectedNum);
		this.frame = frame;
		stop = false;
		skyBGM = new File(".\\sound\\sky_map1.wav");
		redskyBGM = new File(".\\sound\\redsky_map2.wav");
		spaceBGM = new File(".\\sound\\space_map3.wav");
		jumpBGM = new File(".\\sound\\jump.wav");
		slideBGM = new File(".\\sound\\slide.wav");
		field = new Field();
		scorePanel = new ScorePanel();
		
		setPreferredSize(new Dimension(1000, 700));
		setMaximumSize(new Dimension(1000, 700));
		setLayout(null);
 		person = new Person(selectedNum);
		person.setOpaque(false);
		background.setBounds(0, 0, 1000, 700);
		person.setBackground(new Color(0, 0, 0, 1));
		background.setLayout(null);
		background.add(person); // 패널에 person을 추가하는게 아니라, background에 person을 추가.
		scorePanel.setBounds(700, 0, 300, 100);
		scorePanel.setBackground(new Color(0, 0, 0, 0));
		background.add(scorePanel);
		physical = new Physical();
		physical.setBounds(0, 0, 560, 80);
		background.add(physical);
		add(background);
		
		try {
			image = ImageIO.read(new File(".\\img\\stage1.png"));
//			tempSound = soundStart(skyBGM);
			getBlack(image);
			getRed(image);
			getYellow(image);
			getGreen(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		fieldThread = new Thread(new fieldRunnable());
		fieldThread.start();
		
		GravityThread = new Thread(new GravityRunnable());
		GravityThread.start();
		
		MoveThread = new Thread(new MoveRunnable());
		MoveThread.start();
		
		gameOverState = new Thread(new GameOverRunnable());
		gameOverState.start();
		
		pnl = new JPanel();
		background.add(pnl);
		
		pnl2 = new JPanel();
		pnl2.setBackground(new Color(2, 233, 44));
		background.add(pnl2);
		
		pnl4 = new JPanel();
		pnl4.setBackground(new Color(255, 174, 201));
		background.add(pnl4);
		
		setFocusable(true);
		addKeyListener(new SlideKeyListener());
	}
	
	private class fieldRunnable implements Runnable {
		@Override
		public void run() {
			int stagestate = 595; // 스테이지.png파일의 길이
			ImageIcon obstacle = null;
			ImageIcon foothold = null;
			while (!stop) {
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
				
				for(int i = 0; i < potionList.size(); i++) {
					int X = potionList.get(i).getX();
					potionList.get(i).setBounds(X - 5, potionList.get(i).getY(), 50, 50);
				}
				
				
				if (fieldList.size() <= stagestate) { // 1스테이지에서 2스테이지로 넘어감 
//					System.out.println(fieldList.size());
////				***********************************************************깜빡
//					if (fieldList.get(400).getX() == 0) {
//						blackLabel = new JLabel();
//						blackLabel.setBounds(0, 0, 1000, 660);
//						blackLabel.setOpaque(true);
//						background.add(blackLabel);
//						blackDrawThread = new Thread(new backFade());
//						blackDrawThread.start();
//					}
////				***********************************************************깜빡
					if (fieldList.get(550).getX() == 0) { // 1스테이지의 필드리스트 사이즈-1만큼 get()에 입력
						try {
							image = ImageIO.read(new File(".\\img\\stage2.png"));
							obstacle = new ImageIcon(".\\img\\cloud.png");
							foothold = new ImageIcon(".\\img\\cloud_foothold.png");
							mapCreate(image, obstacle, foothold);
							tempSound.stop();
							tempSound= soundStart(redskyBGM);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NullPointerException e) {
							System.out.println("사운드 없음");
							mapCreate(image, obstacle, foothold);
						}
						background.setBackImg1(new ImageIcon(".\\img\\bg2.png").getImage());
					}
				}
				
				if (fieldList.size() > stagestate) { // 2스테이지에서 3스테이지로 넘어감
////				***********************************************************깜빡
//					if (fieldList.get(395).getX() == 0) {
//						blackLabel = new JLabel();
//						blackLabel.setBounds(0, 0, 1000, 660);
//						blackLabel.setOpaque(true);
//						background.add(blackLabel);
//						blackDrawThread = new Thread(new backFade());
//						blackDrawThread.start();
//					}
////				***********************************************************깜빡
					if (fieldList.get(1180).getX() == 0) {
						try {
							image = ImageIO.read(new File(".\\img\\stage3.png"));
							obstacle = new ImageIcon(".\\img\\meteor.png");
							foothold = new ImageIcon(".\\img\\steel.png");
							mapCreate(image, obstacle, foothold);
							tempSound.stop();
							tempSound = soundStart(spaceBGM);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NullPointerException e) {
							System.out.println("사운드 없음");
							mapCreate(image, obstacle, foothold);
						}
						background.setBackImg1(new ImageIcon(".\\img\\bg3.png").getImage());
					}
				}
			}
		}
	}
	
	private class JumpRunnable implements Runnable {
		private int gravity = 1;
		private int dy = -20;
		
// *********************************************************점프 이미지
		@Override
		public void run() {
			int y = person.getY();
			person.setIm(person.getJump());
			while (!stop) {
				try {
					isJump = true;
					y += dy;
					if (y > person.getY()) {
						isJump = false;
						person.setIm(person.getRun());
						break;
					}
					person.setLocation(person.getX(), y);
					dy += gravity;
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class GravityRunnable implements Runnable {
		@Override
		public void run() {
			while (!stop) {
				try {
					Thread.sleep(1);
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
			while (!stop) {
				try {
					Thread.sleep(20);
					person.switchSize(selectedNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				b = getFieldY();
				if (!b) {
					stopGravity();
				}
				characterHitbox();
			}
		}
	}
	
	// Y좌표가 900이상 or 체력이 40 이하가 되면 게임 종료 및 결과창 출력
	private class GameOverRunnable implements Runnable {
		@Override
		public void run() {
			while (!stop) {
				try {
					Thread.sleep(1000);
					if (person.getY() >= 900 || physical.getLife() <= 40) {
						gameOver();
						break;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class SlideBGMRunnable implements Runnable {
		@Override
		public void run() {
			try {
				if (isSlideBGM == false) {
					isSlideBGM = true;
					soundStart(slideBGM);
					Thread.sleep(500);
					isSlideBGM = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class HealingRunnable implements Runnable {
		@Override
		public void run() {
			if (physical.isHealing() == false) {
				try {
					physical.setHealing(true);
					Thread.sleep(1000);
					physical.setHealing(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class JellyRunnable implements Runnable {
		@Override
		public void run() {
			if (physical.isJellyEat() == false) {
				try {
					physical.setJellyEat(true);
					Thread.sleep(250);
					physical.setJellyEat(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class doseNotDecreaseLifeRunnable implements Runnable {
		@Override
		public void run() {
			if (physical.isDoseNotDecreaseLife() == false) {
				try {
					physical.setDoseNotDecreaseLife(true);
					System.out.println("3초간 무적");
					person.setAlpha(100);
					Thread.sleep(600);
					person.setAlpha(255);
					Thread.sleep(600);
					person.setAlpha(100);
					Thread.sleep(600);
					person.setAlpha(255);
					Thread.sleep(600);
					person.setAlpha(100);
					Thread.sleep(600);
					person.setAlpha(255);
					physical.setDoseNotDecreaseLife(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void getBlack(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == black) {
					field = new Field();
					field.setBounds(w * 50, h * 50, 50, 200);
					background.add(field);
					fieldList.add(field);
				}
			}
		}
	}
	
	private void getRed(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == red) {
					object = new Object();
					object.setBounds(w * 50, h * 50, 50, 50);
					background.add(object);
					objectList.add(object);
				}
			}
		}
	}
	
	private class backFade implements Runnable {
		@Override
		public void run() {
			backFadeOut();
			backFadeIn();
		}
	}
	
	private void getYellow(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == yellow) {
					jelly = new img.Jelly();
					jelly.setBounds(w * 50, h * 50, 50, 50);
					background.add(jelly);
					jellyList.add(jelly);
				}
			}
		}
	}
	
	public void getGreen(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				if (image.getRGB(w, h) == green) {
					potion = new Potion();
					potion.setBounds(w * 50, h * 50, 50, 50);
					background.add(potion);
					potionList.add(potion);
				}
			}
		}
	}
	
	private void characterHitbox() { // 히트박스 메소드, 장애물에 닿는거 처리할것, 젤리에 닿을때도 처리가능.
		Rectangle objectR = null;
		Rectangle jellyR = null;
		Rectangle potionR = null;
		if (!isSlide) { // down키 눌러질때 true
			personHitR = new Rectangle(new Point(0, person.getY())
					, new Dimension(person.getWidth(), person.getHeight() - 10));
		} else {
			personHitR = new Rectangle(new Point(0, person.getY() + 80)
					, new Dimension(person.getWidth(), (person.getHeight() - 10) / 2));
		}
		pnl4.setBounds(personHitR);
		for (int i = 0; i < objectList.size(); i++) {
			objectR = new Rectangle(new Point(objectList.get(i).getX(), objectList.get(i).getY()), new Dimension(10, 10));
			if (personHitR.intersects(objectR)) {
				if (physical.isDoseNotDecreaseLife() == false) {
					physical.lifeMinus(physical.getLife());
					physical.lifeMinus(physical.getLife());
					physical.lifeMinus(physical.getLife());
				}
				doseNotDecreaseLifeThread = new Thread(new doseNotDecreaseLifeRunnable());
				doseNotDecreaseLifeThread.start();
			}
		}
		
		for (int i = 0; i < jellyList.size(); i++) {
			jellyR = new Rectangle(new Point(jellyList.get(i).getX(), jellyList.get(i).getY()), new Dimension(10, 10));
			if (personHitR.intersects(jellyR) && physical.isJellyEat() == false) {
				jellyList.get(i).setJelly(new ImageIcon(".\\img\\effect.png").getImage());
				scoreUP();
				if (jellyList.get(i).getAlpha() > 20) {
					jellyList.get(i).setAlpha(jellyList.get(i).getAlpha() - 19);
				}
				jellyThread = new Thread(new JellyRunnable());
				jellyThread.start();
				
			}
		}
		
		for (int i = 0; i < potionList.size(); i++) {
			potionR = new Rectangle(new Point(potionList.get(i).getX(), potionList.get(i).getY()), new Dimension(10, 10));
			if (personHitR.intersects(potionR) && physical.isHealing() == false) {
				physical.lifePlus();
				healingThread = new Thread(new HealingRunnable());
				healingThread.start();
				potionList.get(i).setPotion(new ImageIcon(".\\img\\effect.png").getImage());
				if (potionList.get(i).getAlpha() > 20) {
					potionList.get(i).setAlpha(potionList.get(i).getAlpha() - 19);
				}
			}
		}
	}
	
//	image = ImageIO.read(new File(".\\img\\stage2.png"));
//	ImageIcon obstacle = new ImageIcon(".\\\\img\\\\cloud.png");
//	ImageIcon foothold = new ImageIcon(".\\img\\cloud_foothold.png");

	private void mapCreate(BufferedImage image, ImageIcon obstacle, ImageIcon foothold) {
		getBlack(image);
		getRed(image);
		getYellow(image);
		getGreen(image);
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).setObject(obstacle.getImage());
		}
		for (int i = 0; i < fieldList.size(); i++) {
			fieldList.get(i).setField(foothold.getImage());
		}
	}
	
	private boolean getFieldY() {
		Rectangle personR = new Rectangle(new Point(0, person.getY() + 150), new Dimension(100, 4));
		Rectangle fieldR = null;
		pnl.setBounds(personR);
		
		for (int i = 0; i < fieldList.size(); i++) {
			fieldR = new Rectangle(new Point(fieldList.get(i).getX(), fieldList.get(i).getY()), new Dimension(50, 10));
			pnl2.setBounds(fieldR);
			if (personR.intersects(fieldR)) {
				physical.setJumpStatus(0);
				return true;
			}
		}
		return false;
	}
	
	private void scoreUP() {
		int temp = scorePanel.getScore();
		temp += 370368; // 젤리 점수
		scorePanel.setScore(temp);
	}
	
	private synchronized void startGravity() {
		int gravity = 1;
		if (b || isJump) {
			try {
				wait();
			} catch (InterruptedException e) {}
		} 
		person.setLocation(0, person.getY() + gravity);
		gravity += 5;
	}
		
	private synchronized void stopGravity() {
		notifyAll();
	}

	private void gameOver() {
		try {
			resultPanel = new ResultPanel(frame.getStartPanel(), scorePanel, frame);
			frame.getContentPane().add("result", resultPanel);
			frame.changeResultPanel();
			rankPanel = new RankPanel(resultPanel, frame.getStartPanel(), frame);
			frame.getContentPane().add("rank", rankPanel);
			stop = true;
			tempSound.stop();
		} catch (NullPointerException e) {
			System.out.println("사운드 종료");
		}
	}
	
	private void backFadeOut() {
		for (int i = 0; i < 256; i += 5) {
			blackLabel.setBackground(new Color(0, 0, 0, i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Clip soundStart(File path) {
		try {
			sound = AudioSystem.getClip();
			sound.open(AudioSystem.getAudioInputStream(path));
			sound.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			sound = null;
//			e.printStackTrace();
		}
		return sound;
	}
	
	private void backFadeIn() {
		for (int i = 255; i >= 0; i -= 5) {
			blackLabel.setBackground(new Color(0, 0, 0, i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		blackLabel.setVisible(false);
	}
	
// *********************************************************슬라이딩 이미지
	private class SlideKeyListener extends KeyAdapter {
//		Thread t = new Thread();
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				person.setIm(person.getSlide());
				
				isSlide = true;
				person.setBounds(person.getX(), person.getY(), 150, 150);
				slideBGMThread = new Thread(new SlideBGMRunnable());
				slideBGMThread.start();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (physical.getJumpStatus() <= 1) {
					Thread t = new Thread(new JumpRunnable());
					t.start();
					int temp = physical.getJumpStatus();
					temp++;
					physical.setJumpStatus(temp);
					soundStart(jumpBGM);
				}
			}
		}
		
// *********************************************************슬라이드 멈췄을때 이미지
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				person.setIm(person.getRun());
				person.setBounds(person.getX(), person.getY(), 150, 150);
				isSlide = false;
			}
		}
	}
}
