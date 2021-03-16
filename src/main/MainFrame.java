package main;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import panel.TestGamePanel;
import panel.SelectPanel;
import panel.StartPanel;

public class MainFrame extends JFrame {
	// 레이아웃
	private CardLayout cards = new CardLayout();
	
	// 패널
	private StartPanel startPanel;
	private SelectPanel selectPanel;
	private TestGamePanel gamePanel;
	
	// 메인 메소드
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public MainFrame() {
		setTitle("Mongs Run");
		getContentPane().setLayout(cards);
		
		startPanel = new StartPanel(this);
		selectPanel = new SelectPanel(this);
		gamePanel = new TestGamePanel(this);
		
		getContentPane().add("start", startPanel);
		getContentPane().add("select", selectPanel);
		getContentPane().add("game", gamePanel);
		
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// 패널 전환 메소드
	public void changeStartPanel() {
		cards.show(this.getContentPane(), "start");
	}
	public void changeSelectPanel() {
		cards.show(this.getContentPane(), "select");
	}
	public void changeGamePanel() {
		cards.show(this.getContentPane(), "game");
	}
}


