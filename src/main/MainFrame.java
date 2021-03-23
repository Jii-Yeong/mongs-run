package main;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import panel.FrontPanel;
import panel.SelectPanel;
import panel.StartPanel;

public class MainFrame extends JFrame {
	// 레이아웃
	private CardLayout cards = new CardLayout();
	
	// 패널
	private StartPanel startPanel;
	private SelectPanel selectPanel;
	
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
		
		getContentPane().add("front", new FrontPanel(this));
		getContentPane().add("start", startPanel);
		getContentPane().add("select", selectPanel);
		
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changeFrontPanel();
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
	
	public void changeFrontPanel() {
		cards.show(this.getContentPane(), "front");
	}
	
	
	public StartPanel getStartPanel() {
		return startPanel;
	}

	public void setStartPanel(StartPanel startPanel) {
		this.startPanel = startPanel;
	}
}
