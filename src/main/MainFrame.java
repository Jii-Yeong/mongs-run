package main;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import panel.SelectPanel;
import panel.StartPanel;
import ranking.RankPanel;
import score.ScorePanel;

public class MainFrame extends JFrame {
	// 레이아웃
	private CardLayout cards = new CardLayout();
	
	// 패널
	private StartPanel startPanel;
	private SelectPanel selectPanel;
	private RankPanel rankPanel;
	private ScorePanel currentScore;
	
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
		
		getContentPane().add("start", startPanel);
		getContentPane().add("select", selectPanel);
		
		
		setSize(1000, 700);
//		setResizable(false); 다 만들고 주석 풀것임
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
	public void changePlayPanel() {
		cards.show(this.getContentPane(), "play");
		
	}
	
	public void changeRankPanel() {
		cards.show(this.getContentPane(), "rank");
	}
	
	public void changeResultPanel() {
		cards.show(this.getContentPane(), "result");
	}
	
	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}
}
