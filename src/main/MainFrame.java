package main;

import java.awt.Container;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import panal.PlayPanel;
import panal.BackgroundPanel;
import panel.TestGamePanel;
import panel.SelectPanel;
import panel.StartPanel;
import ranking.RankData;
import ranking.RankPanel;

public class MainFrame extends JFrame {
	// 레이아웃
	private CardLayout cards = new CardLayout();
	
	// 패널
	private StartPanel startPanel;
	private SelectPanel selectPanel;
	private PlayPanel playPanel;
	private RankPanel rankPanel;
	
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
		playPanel = new PlayPanel(this);
//		rankPanel = new RankPanel(new RankData("양종문", 1235));
//		add(rankPanel);
		
		getContentPane().add("start", startPanel);
		getContentPane().add("select", selectPanel);
		getContentPane().add("play", playPanel);
		
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
	public void changePlayPanel() {
		cards.show(this.getContentPane(), "play");
	}
}


