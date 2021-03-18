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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import ranking.RankData;
import ranking.RankPanel;
import result.ResultPanel;

public class PlayPanel extends JPanel {
	BackgroundPanel background = new BackgroundPanel();
	private int fieldX = 0;
	private Field field;
	private Object object;
	private img.Jelly jelly;
	private List<Field> fieldList = new ArrayList<Field>();
	private List<Object> objectList = new ArrayList<Object>();
	private List<img.Jelly> jellyList = new ArrayList<img.Jelly>();
	private boolean b = false;
	private int personY = 100;
	private Person person;
	private Thread t2;
	private Physical physical;
	private RankPanel rankPanel;
	private ResultPanel resultPanel;
	private ScorePanel scorePanel;
	private MainFrame frame;
	private static int black = new Color(0, 0, 0).getRGB();
	private static int red = new Color(237, 28, 36).getRGB();
	private static int yellow = new Color(255, 242, 0).getRGB();
	private Thread t3;
	private Thread t;
	private Thread gameOverState;
	private JLabel blackLabel;
	private Thread blackDraw;
	BufferedImage image = null;
	boolean isJump;

	/**
	 * Create the panel.
	 */
	JPanel pnl;
	JPanel pnl2;
	JPanel pnl4; // 깜빡이용 히트박스표시패널
	
	public PlayPanel(MainFrame frame) {
		
		
		field = new Field();
		this.frame = frame;
		scorePanel = new ScorePanel();
		JButton lifeUp = new JButton("Up");
		lifeUp.setBounds(0, 100, 100, 100);
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
		person.setBounds(100, 202, 100, 165);
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
			getBlack(image);
			getRed(image);
			getYellow(image);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		t = new Thread(new fieldRunnable());
		t.start();
		
		t2 = new Thread(new GravityRunnable());
		t2.start();
		
		t3 = new Thread(new MoveRunnable());
		t3.start();
		
		
		
		pnl = new JPanel();
		background.add(pnl);
		
		
		
		pnl2 = new JPanel();
		pnl2.setBackground(new Color(2, 233, 44));
		background.add(pnl2);
		
		pnl4 = new JPanel();
		pnl4.setBackground(new Color(255, 174, 201));
		background.add(pnl4);
		
		Thread t4 = new Thread(new GameOverRunnable());
		t4.start();

		gameOverState = new Thread(new GameOverRunnable());
		gameOverState.start();
		
		setFocusable(true);
		addKeyListener(new SlideKeyListener());
	}
	
	private class fieldRunnable implements Runnable {
		@Override
		public void run() {
			int stagestate = 240; // 스테이지.png파일의 길이
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
				
//				System.out.println(fieldList.size()); //
//				System.out.println(fieldList.get(fieldList.size()-1).getX());
				
				
				if (fieldList.size() <= stagestate) { // 1스테이지에서 2스테이지로 넘어감 
//				***********************************************************깜빡
					if (fieldList.get(195).getX() == 0) {
						blackLabel = new JLabel();
						blackLabel.setBounds(0, 0, 1000, 660);
						blackLabel.setText("테스트용");
						blackLabel.setOpaque(true);
						background.add(blackLabel);
						blackDraw = new Thread(new backFade());
						blackDraw.start();
					}
//				***********************************************************깜빡
					if (fieldList.get(200).getX() == 0) { // 1스테이지의 필드리스트 사이즈-1만큼 get()에 입력
						try {
							image = ImageIO.read(new File(".\\img\\stage2.png"));
							getBlack(image);
							getRed(image);
							getYellow(image);
						} catch (IOException e) {
							e.printStackTrace();
						}
						background.setBackImg1(new ImageIcon(".\\img\\bg2.png").getImage());
					}
				}
				
				
				if (fieldList.size() > stagestate) { // 2스테이지에서 3스테이지로 넘어감
//				***********************************************************깜빡
					if (fieldList.get(395).getX() == 0) {
						blackLabel = new JLabel();
						blackLabel.setBounds(0, 0, 1000, 660);
						blackLabel.setText("테스트용");
						blackLabel.setOpaque(true);
						background.add(blackLabel);
						blackDraw = new Thread(new backFade());
						blackDraw.start();
					}
//				***********************************************************깜빡
					if (fieldList.get(400).getX() == 0) {
						try {
							image = ImageIO.read(new File(".\\img\\stage3.png"));
							getBlack(image);
							getRed(image);
							getYellow(image);
						} catch (IOException e) {
							e.printStackTrace();
						}
						background.setBackImg1(new ImageIcon(".\\img\\bg3.png").getImage());
					}
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
					System.out.println("w" + w);
					System.out.println("h" + h);
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
					System.out.println("w" + w);
					System.out.println("h" + h);
					jelly = new img.Jelly();
					jelly.setBounds(w * 50, h * 50, 50, 50);
					background.add(jelly);
					jellyList.add(jelly);
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
				characterHitbox();
			}
		}
	}
	
	public void characterHitbox() { // 히트박스 메소드, 장애물에 닿는거 처리할것, 젤리에 닿을때도 처리가능.
		Rectangle personHitR = new Rectangle(new Point(0, person.getY())
				, new Dimension(person.getWidth(), person.getHeight() - 10));
		Rectangle objectR = null;
		Rectangle jellyR = null;
		pnl4.setBounds(personHitR);
		for (int i = 0; i < objectList.size(); i++) {
			objectR = new Rectangle(new Point(objectList.get(i).getX(), objectList.get(i).getY()), new Dimension(10, 10));
			if (personHitR.intersects(objectR)) {
				System.out.println("오브젝트 닿았다!");
			}
		}
		
		for (int i = 0; i < jellyList.size(); i++) {
			jellyR = new Rectangle(new Point(jellyList.get(i).getX(), jellyList.get(i).getY()), new Dimension(10, 10));
			if (personHitR.intersects(jellyR)) {
				System.out.println("젤리 닿았다!");
			}
		}
		// if문의 조건 = 캐릭터의 히트박스가 오브젝트나 젤리와 겹칠때
		// physical.lifeminus 
	}
	
	// Y좌표가 900이상 or 체력이 40 이하가 되면 게임 종료 및 결과창 출력
	private class GameOverRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("게임종료 확인용");
			while (true) {
				try {
					Thread.sleep(500);
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
	public boolean getFieldY() {
		Rectangle personR = new Rectangle(new Point(0, person.getY() + 150), new Dimension(100, 10));
		Rectangle fieldR = null;
		pnl.setBounds(personR);
		pnl.setBackground(new Color(2, 233, 44));
		
		for (int i = 0; i < fieldList.size(); i++) {
			fieldR = new Rectangle(new Point(fieldList.get(i).getX(), fieldList.get(i).getY()), new Dimension(50, 10));
			pnl2.setBounds(fieldR);
			if (personR.intersects(fieldR)) {
				return true;
			}
		}
		
		return false;
	}
	
	public synchronized void startGravity() {
		if (b || isJump) {
			try {
				wait();
			} catch (InterruptedException e) {}
		} 
		person.setLocation(0, person.getY() + 1);
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
	
	private void gameOver() {
		resultPanel = new ResultPanel(frame.getStartPanel(), scorePanel, frame);
		frame.getContentPane().add("result", resultPanel);
		rankPanel = new RankPanel(frame.getStartPanel() , scorePanel, frame);
		frame.getContentPane().add("rank", rankPanel);
		frame.changeResultPanel();
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

	private void backFadeIn() {
		for (int i = 255; i >= 0; i -= 5) {
			blackLabel.setBackground(new Color(0, 0, 0, i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// ********************************************************** setVisible 말고 대체 방법 생각해야함
		blackLabel.setVisible(false);
	}
	
	private class SlideKeyListener extends KeyAdapter {
		Thread t = new Thread();
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("시작");
				person.setIm(new ImageIcon(".\\img\\jelly1.png").getImage());
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				System.out.println("스페이스 입력");
				Thread t = new Thread(new JumpRunnable());
				t.start();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("뗌");
				person.setIm(new ImageIcon(".\\img\\Person.gif").getImage());
			}
		}

	}
	
	private class JumpRunnable implements Runnable {
		private int gravity = 1;
		private int dy = -20;
		@Override
		public void run() {
			int y = person.getY();
			while (true) {
				isJump = true;
				y += dy;
				if (y > person.getY()) {
					isJump = false;
					break;
				}
				person.setLocation(person.getX(), y);
				dy += gravity;
				
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
}
